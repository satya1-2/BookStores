package com.example.bookstores.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import org.springframework.stereotype.Component;

@Component

public class JWTToken {
    private static final String SECRET = "Satya";
    public static String createToken(long id) {
        String token;
        token = JWT.create()
                .withClaim("id", id)
                .sign(Algorithm.HMAC256(SECRET));
        return token;
    }
    public static long decodeToken(String token) {
        long id = 0;
        if (token != null) {
            id = JWT.require(Algorithm.HMAC256(SECRET)).
                    build().verify(token).
                    getClaim("id").asLong();
        }
        return id;
    }

    public boolean verifyToken(String token) {
        try {
            JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}




