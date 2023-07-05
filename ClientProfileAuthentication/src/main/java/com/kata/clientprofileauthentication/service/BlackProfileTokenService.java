package com.kata.clientprofileauthentication.service;

import com.kata.clientprofileauthentication.models.BearerOrJwtBearerToken;

public interface BlackProfileTokenService {
    boolean findBlackTokens(BearerOrJwtBearerToken bearerOrJwtBearerToken);
}
