package com.luospace.blog.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Date;

public class JwtUtil {
    private static final int EXPIRE_TIME = 1; //hour

    private static final String SECRET = "hdougdte4687fhf";


    /**
     * 签名
     * @param userId
     * @param userName
     * @return
     */
    public static String createToken(String userId,String userName) {
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.HOUR,EXPIRE_TIME);
        Date expiresDate = nowTime.getTime();
        return JWT.create().withAudience(userId).
                withExpiresAt(expiresDate).
                withClaim("userName",userName).
                sign(Algorithm.HMAC256(SECRET));
    }

    /**
     * 验证签名
     * @param token
     * @throws Exception
     */
    public static void verifyToken(String token) throws Exception{
       try {
           JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
           DecodedJWT jwt = verifier.verify(token);
       } catch (JWTVerificationException exception){
           throw new RuntimeException("token验证失败");
       }
    }

    public static String getUserId(String token) throws Exception{
        try {
            return JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException exception) {
            throw new RuntimeException("找不到用户");
        }
    }
}
