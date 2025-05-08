package devmikael.app_saude.security;

import java.util.Date;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtUtil {

    private static final String SECRET = System.getenv("SECRET_KEY"); 
    private static final long EXPIRATION = 1000 * 60 * 60 * 24;

    public static String generateToken(String email, int id) {
        return JWT.create()
                .withSubject(email)
                .withClaim("id", id)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION))
                .sign(Algorithm.HMAC256(SECRET));
    }

    public static String validateToken(String token) throws JWTVerificationException {
        DecodedJWT decoded = JWT.require(Algorithm.HMAC256(SECRET))
                .build()
                .verify(token);
        return decoded.getSubject();
    }

    public static Integer extractUserId(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET))
                .build()
                .verify(token)
                .getClaim("id")
                .asInt();
    }
}
