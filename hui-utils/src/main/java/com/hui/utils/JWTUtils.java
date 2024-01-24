package com.hui.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

/*
*
* 生成JWT令牌
*
* */
public class JWTUtils {
    public static String generateJWT(Map<String,Object> map){
        String token = Jwts.builder()
                .setClaims(map)
                .signWith(SignatureAlgorithm.HS256,"huiSecret")
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))
                .compact();
        return token;
    }

    public static Claims parseJWT(String token){
        Claims claims = Jwts.parser().setSigningKey("huiSecret")
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }
}
