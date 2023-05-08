package de.telran.marketapp.beans;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AuthRequestFilter extends OncePerRequestFilter {
    JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String username = null;
        String jwt = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7);
            try {
                username = jwtTokenUtil.getUsernameFromToken(jwt);
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username,
                        null,
                        jwtTokenUtil.getRolesFromToken(jwt).stream().map(SimpleGrantedAuthority::new).toList());
                SecurityContextHolder.getContext().setAuthentication(token);
            } catch (ExpiredJwtException | MalformedJwtException e) {
                StringBuilder sb = new StringBuilder();
                sb.append("{ ");
                sb.append("\"error\": \"Unauthorized\", ");
                sb.append("\"message\": \"Token expired or invalid\", ");
                sb.append("\"path\": \"")
                        .append(request.getRequestURL())
                        .append("\"");
                sb.append("} ");

                response.setContentType("application/json");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write(sb.toString());
            }
        }
        filterChain.doFilter(request, response);
    }
}
