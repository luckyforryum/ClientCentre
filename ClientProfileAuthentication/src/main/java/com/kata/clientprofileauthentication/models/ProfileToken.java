package com.kata.clientprofileauthentication.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash("tokenKey")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data

public class ProfileToken {
    @Id
    @Indexed
    private String tokenId;
    @Indexed
    private String jwtBearerToken;
    @Indexed
    private String refreshToken;
    @Indexed
    private String bearerToken;
    @Override
    public String toString() {
        return "ProfileToken{" +
                "tokenId='" + tokenId + '\'' +
                ", jwtBearerToken='" + jwtBearerToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", bearerToken='" + bearerToken + '\'' +
                '}';
    }

}
