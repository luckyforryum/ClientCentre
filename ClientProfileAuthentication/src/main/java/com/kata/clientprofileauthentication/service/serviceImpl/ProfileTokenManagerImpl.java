package com.kata.clientprofileauthentication.service.serviceImpl;

import com.kata.clientprofileauthentication.models.BearerOrJwtBearerToken;
import com.kata.clientprofileauthentication.models.BlackListOfProfileTokens;
import com.kata.clientprofileauthentication.models.ProfileToken;
import com.kata.clientprofileauthentication.repository.BlackListOfProfileTokensRepository;
import com.kata.clientprofileauthentication.repository.ProfileTokenRepository;
import com.kata.clientprofileauthentication.service.ProfileTokenManager;
import com.kata.clientprofileauthentication.util.SecureUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * сервис для работы со всеми токенами (рабочими и токенами из blacklist)
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ProfileTokenManagerImpl implements ProfileTokenManager {
    private final BlackListOfProfileTokensRepository blackListOfProfileTokensRepository;
    private final ProfileTokenRepository profileTokenRepository;
    private final SecureUtils secureUtils;

    @Override
    public ProfileToken updateProfileToken(BearerOrJwtBearerToken bearerOrJwtBearerToken) {
        String anyToken = bearerOrJwtBearerToken.getBearerOrJwtBearerToken();
        if (anyToken.startsWith("Bearer ")) {
            ProfileToken updateProfileTokenByBearer = profileTokenRepository
                    .getProfileTokenByBearerToken(anyToken);
            blackListOfProfileTokensRepository
                    .save(new BlackListOfProfileTokens(updateProfileTokenByBearer.getBearerToken()));
            updateProfileTokenByBearer
                    .setBearerToken(secureUtils.refreshTokens(anyToken));
            return updateProfileTokenByBearer;
        } else {
            ProfileToken updateProfileTokenByJwtBearer = profileTokenRepository
                    .getProfileTokensByJwtBearerToken(anyToken);
            blackListOfProfileTokensRepository
                    .save(new BlackListOfProfileTokens(updateProfileTokenByJwtBearer.getBearerToken()));
            updateProfileTokenByJwtBearer
                    .setJwtBearerToken(secureUtils.refreshTokens(anyToken));
            return updateProfileTokenByJwtBearer;
        }
    }
    @Override
    public String getTokenType(String bearerOrJwtBearerToken) {
        if (bearerOrJwtBearerToken.startsWith("Bearer ")) {
            return "Bearer";
        } else if (bearerOrJwtBearerToken.startsWith("JwtBearer ")) {
            return "JwtBearer";
        } else {
            return null;
        }
    }
}
