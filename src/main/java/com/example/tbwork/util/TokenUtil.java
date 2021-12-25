package com.example.tbwork.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.tbwork.pojo.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtil {
    private static final long EXPIRE_TIME= 1000 * 60 * 60 * 24 * 3;
    private static final String TOKEN_SECRET="kurogaki";//私钥
    //生成token
    public static String sign(User user){
        String token = null;
        try {
            Date expiresAt = new Date(System.currentTimeMillis() + EXPIRE_TIME);//过期时间
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            Map<String, Object> header = new HashMap<>(2);
            header.put("Type", "Jwt");
            header.put("alg", "HS256");
            token = JWT.create()
                    .withHeader(header)
                    .withClaim("account", user.getAccount())
                    .withExpiresAt(expiresAt)
                    .sign(algorithm);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return token;
    }
    //验证token
    public static boolean verify(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);//未验证通过会抛出异常
            return true;
        } catch (Exception e){
            return false;
        }
    }
    public static String getUserAccount(String token){//获取token中信息
        try{
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("account").asString();
        }catch(JWTDecodeException e){
            e.printStackTrace();
        }
        return null;
    }
}

