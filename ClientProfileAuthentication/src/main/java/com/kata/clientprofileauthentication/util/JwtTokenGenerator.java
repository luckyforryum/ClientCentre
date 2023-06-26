package com.kata.clientprofileauthentication.util;

import com.kata.clientprofileauthentication.models.ProfileToken;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Component;
import org.testcontainers.shaded.org.apache.commons.lang3.RandomStringUtils;
import java.util.Date;

@Component
public class JwtTokenGenerator {
    protected static final String ACCESS_SECRET_KEY = "12345678910123456789101234567891012345678910123456789101234567891012345678910";
    protected static final String REFRESH_SECRET_KEY = "12345678910123456789101234567891012345678910123456789101234567891012345678910";
    private static final String ROLE = "CREDIT"; //пользователи определнных групп
    protected SecretKey secretKeyFabric(String key) {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(key));
    }

    public ProfileToken generateAllTokens() {
        Date now = new Date();
        Date accessDate = new Date(now.getTime() + 300000);// 5 min expiration
        Date refreshDate = new Date(now.getTime() + 86400000); // 1 day expiration
        String JwtBearerToken = "JwtBearer "+ Jwts.builder()
                .claim("role", ROLE)
                .setIssuedAt(now)
                .setExpiration(accessDate)
                .signWith(secretKeyFabric(ACCESS_SECRET_KEY))
                .compact();
        String JwtRefreshToken = Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(refreshDate)
                .signWith(secretKeyFabric(REFRESH_SECRET_KEY))
                .compact();
        return ProfileToken.builder()
                .jwtBearerToken(JwtBearerToken)
                .refreshToken(JwtRefreshToken)
                .bearerToken("Bearer "+ RandomStringUtils.randomAlphanumeric(64))
                .build();
    }
}

