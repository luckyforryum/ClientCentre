package com.kata.clientprofileauthentication.repository;

import com.kata.clientprofileauthentication.models.ProfileToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProfileTokenRepository extends CrudRepository<ProfileToken, String> {
    ProfileToken getProfileTokenByBearerToken(String bearerToken);
    ProfileToken getProfileTokensByJwtBearerToken(String jwtBearerToken);
    ProfileToken getProfileTokensByTokenId(String tokenId);
    Boolean existsByBearerToken(String BearerToken);
    Boolean existsByJwtBearerToken(String JwtBearerToken);
}