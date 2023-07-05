package com.kata.clientprofileauthentication.service;

import com.kata.clientprofileauthentication.models.ProfileToken;

public interface ProfileTokenService {
    ProfileToken generateNewAllTokenServiceImpl();
    boolean findBearerTokenBool(String bearerToken);
    boolean findJwtBearerTokenBool(String jwtBearerToken);
    boolean serviceValidateBearerOrJwtBearerToken(String token);
    boolean findTokenBool(String tokenType, String bearerOrJwtBearerToken);
}
