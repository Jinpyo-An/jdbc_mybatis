package exercise;

import exercise.jwt.JWTPayload;
import exercise.jwt.JWTUtils;
import io.jsonwebtoken.Claims;

public class AppInitJWT {

    public static void main(String[] args) throws Exception {
        JWTPayload payload = new JWTPayload();
        payload.setUserId("2011010");
        payload.setUserName("홍길동");

        String token = JWTUtils.createToken(payload);
        System.out.println("token = " + token);

        final Claims claims = JWTUtils.verifyToken(token);
        System.out.println(claims);
    }
}
