package com.kata.clientprofileauthentication.repository;

import com.kata.clientprofileauthentication.models.BlackListOfProfileTokens;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlackListOfProfileTokensRepository extends CrudRepository<BlackListOfProfileTokens, String> {
    boolean existsByBRefreshOrJwtOrBearer(String bRefreshOrJwtOrBearer);
}
