package de.telran.marketapp.beans;

import de.telran.marketapp.entities.Role;
import de.telran.marketapp.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil {
    private static final long millisecondsInTheMinute = 60_000L;
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration.minutes:120}")
    private long minutesToExpire;

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        var roles = user.getRoles().stream()
                .map(Role::getName)
                .map(SimpleGrantedAuthority::new)
                .map(GrantedAuthority::getAuthority)
                .toList();
        claims.put("roles", roles);
        claims.put("user-id", user.getId());
        claims.put("test-claim", "claim");
        claims.put("test-list", List.of("one", "two", "three"));
//        claims.put("full_name", getFullNameFromUser(user));
//        claims.put("user_id", user.getId());
//        claims.put("login", user.getLogin());

        Date issuedDate = new Date();
        Date expired = new Date(issuedDate.getTime() + minutesToExpire * millisecondsInTheMinute);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getLogin())
                .setIssuedAt(issuedDate)
                .setExpiration(expired)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public List<String> getRolesFromToken(String token) {
        return getClaimFromToken(token, (Function<Claims, List<String>>) claims -> claims.get("roles", List.class));
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsTFunction) {
        Claims claims = getAllClaimsFromToken(token);
        return claimsTFunction.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
}
