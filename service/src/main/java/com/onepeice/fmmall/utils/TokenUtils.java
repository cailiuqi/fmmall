package com.onepeice.fmmall.utils;

import com.onepeice.fmmall.entity.Users;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;

public class TokenUtils {

    public static String createTokenforJwt(Users user){
        JwtBuilder builder = Jwts.builder();
        String token = builder.setSubject(user.getUsername())//主题，就是token中携带的数据
                .setId(user.getUserId() + "")//设置token的id为用户id
                .setIssuedAt(new Date())//设置token生成时间
                .setClaims(new HashMap<String, Object>()) //map中可以存放用户的角色权限信息
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 64 * 1000))//设置过期时间
                .signWith(SignatureAlgorithm.HS256, "QIANfeng6666")     //设置加密方式和加密密码
                .compact();
        return token;

    }
}
