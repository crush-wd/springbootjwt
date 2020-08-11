package com.baizhi;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.HashMap;

@SpringBootTest
class SpringbootjwtApplicationTests {

    @Test
    //获取令牌的方式
    void contextLoads() {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND,120);
        // 头 可以不设置 就用默认的 也可以
        HashMap<String, Object> map = new HashMap<>();
        String token = JWT.create()
                .withHeader(map) // head
                .withClaim("id", 21)   // payload
                .withClaim("name", "魏铎")
                .withExpiresAt(instance.getTime())//令牌过期时间
                .sign(Algorithm.HMAC256("qwe"));// Signature

        System.out.println(token);

    }
    @Test
    //验证令牌
    void test(){
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("qwe")).build();
        DecodedJWT verify = jwtVerifier.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoi6a2P6ZOOIiwiaWQiOjIxLCJleHAiOjE1OTY5NDkxNjV9.tgWbvsaQRvW5QbPFtxaeqGv1DdnUFszRIq6_2-xIWc4");
        System.out.println(verify.getClaim("id").asInt());
        System.out.println(verify.getClaim("name").asString());
    }

}
