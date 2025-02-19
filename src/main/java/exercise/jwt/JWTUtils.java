package exercise.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtils {

    private static SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static String createToken(JWTPayload payload) {

        Date iat = new Date();
        Date exp = new Date(iat.getTime() + 10 * 60 * 1000);

        Map<String, Object> headerMap = new HashMap<>();

        headerMap.put("typ", "JWT");
        headerMap.put("alg", "HS256");

        JwtBuilder builder = Jwts.builder().setHeader(headerMap)
                .setId(payload.getUserId())
                .setIssuer(payload.getUserName())
                .setIssuedAt(iat)
                .setExpiration(exp)
                .signWith(SECRET_KEY);

        return builder.compact();
    }

    public static Claims verifyToken(String token) throws Exception {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new Exception("토큰이 만료되었습니다.");
        } catch (UnsupportedJwtException e) {
            throw new Exception("지원되지 않는 토큰 형식입니다.");
        } catch (MalformedJwtException e) {
            throw new Exception("잘못된 형식의 토큰입니다.");
        } catch (SignatureException e) {
            throw new Exception("토큰 서명이 유효하지 않습니다.");
        } catch (IllegalArgumentException e) {
            throw new Exception("토큰이 null이거나 비어 있습니다.");
        }
    }
}
