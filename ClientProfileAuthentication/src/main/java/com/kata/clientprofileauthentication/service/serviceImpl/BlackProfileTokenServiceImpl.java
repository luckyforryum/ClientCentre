package com.kata.clientprofileauthentication.service.serviceImpl;

import com.kata.clientprofileauthentication.models.BearerOrJwtBearerToken;
import com.kata.clientprofileauthentication.repository.BlackListOfProfileTokensRepository;
import com.kata.clientprofileauthentication.service.BlackProfileTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * сервис для работы с blacklist токенами
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class BlackProfileTokenServiceImpl implements BlackProfileTokenService {
    private final BlackListOfProfileTokensRepository blackListOfProfileTokensRepository;

    @Override
    public boolean findBlackTokens(BearerOrJwtBearerToken bearerOrJwtBearerToken) {
        if (bearerOrJwtBearerToken.getBearerOrJwtBearerToken().startsWith("Bearer ")) {
            return blackListOfProfileTokensRepository
                    .existsByBRefreshOrJwtOrBearer(bearerOrJwtBearerToken.getBearerOrJwtBearerToken());
        } else if (bearerOrJwtBearerToken.getBearerOrJwtBearerToken().startsWith("JwtBearer ")) {
            return blackListOfProfileTokensRepository
                    .existsByBRefreshOrJwtOrBearer(bearerOrJwtBearerToken.getBearerOrJwtBearerToken());
        }
        return false;
    }
}
