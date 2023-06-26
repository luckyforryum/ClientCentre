package com.kata.clientprofileauthentication.service;


import com.kata.clientprofileauthentication.models.ProfileToken;

public interface ProfileTokenService {

    boolean serviceValidateALlTokens(String bearerToken);
    boolean serviceValidateBearerToken(String bearerToken);

    boolean serviceValidateRefreshToken(String bearerToken);

    String generateNewAllTokenServiceImpl();

    String findJwtBearerTokenById(String jwtbearerid);

    String findBearerTokenById(String bearerid);

    boolean findBearerTokenBool(String bearerToken);

    boolean findJwtBearerTokenBool(String jwtBearerToken);
    boolean serviceValidateBearerOrJwtBearerToken(String token);
    ProfileToken getProfileTokenByTokenId(String tokenId);
}
