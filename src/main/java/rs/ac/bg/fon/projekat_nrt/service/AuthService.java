package rs.ac.bg.fon.projekat_nrt.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.bg.fon.projekat_nrt.domain.*;
import rs.ac.bg.fon.projekat_nrt.repository.AppUserRepository;
import rs.ac.bg.fon.projekat_nrt.repository.RefreshTokenRepository;
import rs.ac.bg.fon.projekat_nrt.requestDTO.logout.LogoutRequest;
import rs.ac.bg.fon.projekat_nrt.requestDTO.refresh.RefreshRequest;
import rs.ac.bg.fon.projekat_nrt.requestDTO.login.LoginRequest;
import rs.ac.bg.fon.projekat_nrt.requestDTO.register.RegisterRequest;
import rs.ac.bg.fon.projekat_nrt.responseDTO.auth.AuthResponse;
import rs.ac.bg.fon.projekat_nrt.service.security.AccessTokenBlacklistService;
import rs.ac.bg.fon.projekat_nrt.service.security.JwtService;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AppUserRepository appUserRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final AccessTokenBlacklistService blacklistService;

    private static final long REFRESH_DAYS = 14;

    @Transactional
    public AuthResponse register(RegisterRequest req) {
        if (appUserRepository.existsByUsername(req.getUsername())) {
            throw new IllegalArgumentException("Username već postoji: " + req.getUsername());
        }

        AppUser user = AppUser.builder()
                .username(req.getUsername())
                .password(passwordEncoder.encode(req.getPassword()))
                .role(Role.USER)
                .enabled(true)
                .build();

        AppUser saved = appUserRepository.save(user);

        String access = jwtService.generateAccessToken(saved);
        String refresh = issueRefreshToken(saved);

        return AuthResponse.builder()
                .accessToken(access)
                .refreshToken(refresh)
                .role(saved.getRole().name())
                .build();
    }

    @Transactional
    public AuthResponse login(LoginRequest req) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
        );

        AppUser user = appUserRepository.findByUsername(req.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User ne postoji: " + req.getUsername()));

        String access = jwtService.generateAccessToken(user);
        String refresh = issueRefreshToken(user);

        return AuthResponse.builder()
                .accessToken(access)
                .refreshToken(refresh)
                .role(user.getRole().name())
                .build();
    }

    @Transactional
    public AuthResponse refresh(RefreshRequest req) {
        String refreshTokenPlain = req.getRefreshToken();
        String storedHash = sha256Hex(refreshTokenPlain);

        RefreshToken stored = refreshTokenRepository.findByTokenHashAndRevokedFalse(storedHash)
                .orElseThrow(() -> new IllegalArgumentException("Refresh token nije validan."));

        if (stored.getExpiresAt().isBefore(Instant.now())) {
            stored.setRevoked(true);
            throw new IllegalArgumentException("Refresh token je istekao.");
        }

        stored.setRevoked(true);

        AppUser user = stored.getUser();
        String newAccess = jwtService.generateAccessToken(user);
        String newRefresh = issueRefreshToken(user);

        return AuthResponse.builder()
                .accessToken(newAccess)
                .refreshToken(newRefresh)
                .role(user.getRole().name())
                .build();
    }

    @Transactional
    public void logout(LogoutRequest req) {
        String hash = sha256Hex(req.getRefreshToken());

        refreshTokenRepository.findByTokenHashAndRevokedFalse(hash)
                .ifPresent(rt -> rt.setRevoked(true));

        String accessToken = req.getAccessToken();
        if (accessToken != null && !accessToken.isBlank()) {
            String jti = jwtService.extractJti(accessToken);
            Instant exp = jwtService.extractExpiration(accessToken);
            blacklistService.blacklist(jti, exp);
        }
    }

    private String issueRefreshToken(AppUser user) {
        String refreshTokenClient = sha256Hex(generateRandomToken());

        String storedHash = sha256Hex(refreshTokenClient);

        Instant now = Instant.now();
        RefreshToken rt = RefreshToken.builder()
                .user(user)
                .tokenHash(storedHash)
                .createdAt(now)
                .expiresAt(now.plus(REFRESH_DAYS, ChronoUnit.DAYS))
                .revoked(false)
                .build();

        refreshTokenRepository.save(rt);
        return refreshTokenClient;
    }

    private String generateRandomToken() {
        byte[] bytes = new byte[64];
        new SecureRandom().nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

    private String sha256Hex(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("SHA-256 error", e);
        }
    }
}
