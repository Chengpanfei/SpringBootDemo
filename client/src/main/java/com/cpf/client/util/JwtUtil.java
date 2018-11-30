package com.cpf.client.util;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * ClassName      JwtUtil
 * Description    Jwt相关工具类
 *
 * @author CPF
 * @version 1.0
 * @date 2018/11/29 12:14
 */
public class JwtUtil {
    private static final long EXPIRED_TIME = 432_000_000;
    private static final String SECRET = "Hello World";
    private static final String TOKEN_PREFIX = "Bearer ";

    /**
     * 根据用户名生成token
     *
     * @param username 用户名
     */
    public static String createToken(String username) {
        // 生成JWT
        String token = Jwts.builder()
                /// 保存权限（角色）
//                .claim("role", "ADMIN")
                // 用户名写入标题
                .setSubject(username)
                // 有效期设置
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRED_TIME))
                // 签名设置
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

        return TOKEN_PREFIX + token;
    }

    /**
     * 解析传入的token得到用户名
     * 若token不合法则返回null
     *
     * @param token 需要被验证的token
     * @return 解析得到的用户名，或者null
     */
    public static String verifyToken(String token) {
        if (token == null || !token.startsWith(TOKEN_PREFIX)) {
            return null;
        }
        String username = null;

        try {

            username = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();

        } catch (JwtException e) {
            System.out.println("json不合法： " + token);
        }
        return username;
    }


}
