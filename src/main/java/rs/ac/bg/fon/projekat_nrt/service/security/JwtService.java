package rs.ac.bg.fon.projekat_nrt.service.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.projekat_nrt.domain.AppUser;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
public class JwtService {

    @Value("${jwt.expiration-minutes:120}")
    private long expirationMinutes;

    private KeyPair keyPair;

    @PostConstruct
    void init() {
        this.keyPair = loadKeyPairFromKeystore();
    }

    public String generateAccessToken(AppUser user) {
        Instant now = Instant.now();
        Instant exp = now.plusSeconds(expirationMinutes * 60);

        return Jwts.builder()
                .id(UUID.randomUUID().toString())
                .subject(user.getUsername())
                .claim("role", user.getRole().name())
                .issuedAt(Date.from(now))
                .expiration(Date.from(exp))
                .signWith(keyPair.getPrivate(), Jwts.SIG.RS256)
                .compact();
    }

    public String extractUsername(String token) {
        return parseClaims(token)
                .getSubject();
    }
    public String extractJti(String token) {
        return parseClaims(token).getId();
    }

    public Instant extractExpiration(String token) {
        return parseClaims(token).getExpiration().toInstant();
    }

    private Claims parseClaims(String token) {
        return Jwts.parser()
                .verifyWith(keyPair.getPublic())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean isTokenValid(String token, AppUser user) {
        var claims = parseClaims(token);

        return user.getUsername().equals(claims.getSubject())
                && claims.getExpiration().after(new Date());
    }

    private KeyPair loadKeyPairFromKeystore() {
        try {

            System.out.println("JWT_KEYSTORE_PATH=" + System.getenv("JWT_KEYSTORE_PATH"));
            System.out.println("JWT_KEY_ALIAS=" + System.getenv("JWT_KEY_ALIAS"));
            String path = System.getenv("JWT_KEYSTORE_PATH");
            String pass = System.getenv("JWT_KEYSTORE_PASSWORD");
            String alias = System.getenv().getOrDefault("JWT_KEY_ALIAS", "jwt");

            if (path == null || pass == null) {
                throw new IllegalStateException("Fale env varijable JWT_KEYSTORE_PATH ili JWT_KEYSTORE_PASSWORD.");
            }

            KeyStore ks = KeyStore.getInstance("PKCS12");

            try (InputStream is = Files.newInputStream(Path.of(path))) {
                ks.load(is, pass.toCharArray());
            }

            PrivateKey privateKey = (PrivateKey) ks.getKey(alias, pass.toCharArray());
            PublicKey publicKey = ks.getCertificate(alias).getPublicKey();

            return new KeyPair(publicKey, privateKey);
        } catch (Exception e) {
            throw new RuntimeException("Ne mogu da ucitam RSA keypair iz keystore-a.", e);
        }
    }
}
