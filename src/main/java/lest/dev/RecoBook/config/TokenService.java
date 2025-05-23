package lest.dev.RecoBook.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lest.dev.RecoBook.dto.JWTUser;
import lest.dev.RecoBook.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
public class TokenService {

    @Value("${security.secret.value}")
    private String secret;

    public String generateToken(User user) {

        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
                .withSubject(user.getEmail())
                .withClaim("userId", user.getId())
                .withExpiresAt(Instant.now().plusSeconds(86400))
                .withIssuedAt(Instant.now())
                .withIssuer("API RecoBook")
                .sign(algorithm);
    }

    public Optional<JWTUser> validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            DecodedJWT decodedJWT = JWT.require(algorithm)
                                                .build()
                                                .verify(token);

            return Optional.of(JWTUser.builder()
                    .id(decodedJWT.getClaim("userId").asLong())
                    .email(decodedJWT.getSubject())
                    .build());

        } catch (JWTVerificationException ex) {
            return Optional.empty();
        }
    }
}
