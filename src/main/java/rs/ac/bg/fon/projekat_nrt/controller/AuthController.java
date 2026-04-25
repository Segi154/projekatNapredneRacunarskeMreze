package rs.ac.bg.fon.projekat_nrt.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.bg.fon.projekat_nrt.requestDTO.login.LoginRequest;
import rs.ac.bg.fon.projekat_nrt.requestDTO.logout.LogoutRequest;
import rs.ac.bg.fon.projekat_nrt.requestDTO.refresh.RefreshRequest;
import rs.ac.bg.fon.projekat_nrt.requestDTO.register.RegisterRequest;
import rs.ac.bg.fon.projekat_nrt.responseDTO.auth.AuthResponse;
import rs.ac.bg.fon.projekat_nrt.service.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest req) {
        return ResponseEntity.ok(authService.register(req));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest req) {
        return ResponseEntity.ok(authService.login(req));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@Valid @RequestBody RefreshRequest req) {
        return ResponseEntity.ok(authService.refresh(req));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@Valid @RequestBody LogoutRequest req) {
        authService.logout(req);
        return ResponseEntity.ok("Logout uspešan.");
    }
}
