package com.kata.clientprofileauthentication.service;

import com.kata.clientprofileauthentication.models.ProfileToken;
import com.kata.clientprofileauthentication.repository.ProfileTokenRepository;
import com.kata.clientprofileauthentication.util.JwtTokenGenerator;
import com.kata.clientprofileauthentication.util.SecureUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ProfileTokenServiceImpl implements ProfileTokenService {
    private final ProfileTokenRepository profileTokenRepository;
    private final JwtTokenGenerator jwtTokenGenerator;
    private final SecureUtils secureUtils;

    @Override
    public boolean serviceValidateALlTokens(String bearerToken) {
        return secureUtils.validateTokens(profileTokenRepository.getProfileTokenByBearerToken(bearerToken));
    }
    @Override
    public boolean serviceValidateBearerToken(String bearerToken) {
        return secureUtils.validateJwtBearerToken(bearerToken);
    }

    @Override
    public boolean serviceValidateRefreshToken(String bearerToken) {
        return secureUtils.validateRefreshToken(bearerToken);
    }

    @Override
    public String generateNewAllTokenServiceImpl() {
        return profileTokenRepository.save(jwtTokenGenerator.generateAllTokens()).getTokenId();

    }

    @Override
    public String findJwtBearerTokenById(String jwtbearerid) {
        return profileTokenRepository.getProfileTokensByTokenId(jwtbearerid).getJwtBearerToken();
    }

    @Override
    public String findBearerTokenById(String bearerid) {
        return profileTokenRepository.getProfileTokensByTokenId(bearerid).getBearerToken();
    }

    @Override
    public boolean findBearerTokenBool(String bearerToken) {
        return profileTokenRepository.existsByBearerToken(bearerToken);

    }

    @Override
    public boolean serviceValidateBearerOrJwtBearerToken(String token) {
        if (token.startsWith("Bearer ")) {
            return secureUtils.validateRefreshTokenByJwtBearerOrBearerToken
                    (profileTokenRepository.getProfileTokenByBearerToken(token));
        } else {
            return secureUtils.validateRefreshTokenByJwtBearerOrBearerToken
                    (profileTokenRepository.getProfileTokensByJwtBearerToken(token));
        }
    }

    @Override
    public ProfileToken getProfileTokenByTokenId(String tokenId) {
        return profileTokenRepository.getProfileTokensByTokenId(tokenId);
    }

    @Override
    public boolean findJwtBearerTokenBool(String jwtBearerToken) {
        return profileTokenRepository.existsByJwtBearerToken(jwtBearerToken);
    }
}
