package com.kata.clientprofileauthentication.service;

import com.kata.clientprofileauthentication.models.BearerOrJwtBearerToken;
import com.kata.clientprofileauthentication.models.ProfileToken;

public interface ProfileTokenManager {
    ProfileToken updateProfileToken(BearerOrJwtBearerToken bearerOrJwtBearerToken);
    String getTokenType(String bearerOrJwtBearerToken);
}
