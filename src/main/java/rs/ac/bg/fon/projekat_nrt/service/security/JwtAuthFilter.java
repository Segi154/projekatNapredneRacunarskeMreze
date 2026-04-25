package rs.ac.bg.fon.projekat_nrt.service.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import rs.ac.bg.fon.projekat_nrt.domain.AppUser;
import rs.ac.bg.fon.projekat_nrt.service.AppUserDetailsService;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final AppUserDetailsService userDetailsService;
    private final AccessTokenBlacklistService blacklistService;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String auth = request.getHeader("Authorization");
        if (auth == null || !auth.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = auth.substring(7);
        String username;
        String jti;
        try {
            username = jwtService.extractUsername(token);
            jti = jwtService.extractJti(token);
        } catch (Exception e) {
            filterChain.doFilter(request, response);
            return;
        }

        if (jti != null && blacklistService.isBlacklisted(jti)) {
            filterChain.doFilter(request, response);
            return;
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            AppUser user = (AppUser) userDetailsService.loadUserByUsername(username);

            if (jwtService.isTokenValid(token, user)) {
                var authToken = new UsernamePasswordAuthenticationToken(
                        user, null, user.getAuthorities()
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
