package odwsi.bank.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtProvider {

    private static final SecretKey KEY = Keys.hmacShaKeyFor(Decoders.BASE64.decode("Y29zWmVieVpuYWtpU2llWmdhZHphbHlhYWFhYWFhYWE="));

    public String generateToken(Authentication authentication) {
        String clientname = authentication.getName();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 100000);

        return Jwts.builder()
                .subject(clientname)
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(KEY)
                .compact();
    }

    public String getUsernameFromJwt(String token) {
        return Jwts.parser()
                .verifyWith(KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(KEY)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
