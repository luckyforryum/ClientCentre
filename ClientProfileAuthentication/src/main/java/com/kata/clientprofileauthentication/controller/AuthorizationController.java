package com.kata.clientprofileauthentication.controller;

import com.kata.clientprofileauthentication.models.BearerOrJwtBearerToken;
import com.kata.clientprofileauthentication.models.ProfileToken;
import com.kata.clientprofileauthentication.service.ProfileTokenServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Slf4j
public class AuthorizationController {

    private final ProfileTokenServiceImpl profileTokenServiceImpl;

    @GetMapping("/jwtbearer")
    public ResponseEntity<String> testJwtBearer() {
        String jwtbearerid = profileTokenServiceImpl
                .generateNewAllTokenServiceImpl();
        String jwtbearer = profileTokenServiceImpl.findJwtBearerTokenById(jwtbearerid);

        log.info("BearerJwt токен: "+ jwtbearer);
        return ResponseEntity.ok().header("jwtBearerToken", jwtbearer).build();
    }

    @GetMapping("/bearer")
    public ResponseEntity<String> testBearer() {
        String bearerid = profileTokenServiceImpl
                .generateNewAllTokenServiceImpl();
        String bearer = profileTokenServiceImpl.findBearerTokenById(bearerid);
        log.info("Bearer токен: " + bearer);
        return ResponseEntity.ok().header("BearerToken", bearer).build();
    }
    @GetMapping("/newTokens")
    public ResponseEntity<ProfileToken> newTestTokens() {
        String tokenId = profileTokenServiceImpl
                .generateNewAllTokenServiceImpl();
        ProfileToken tokens = profileTokenServiceImpl.getProfileTokenByTokenId(tokenId);
        return ResponseEntity.ok()
                .header("BearerToken", tokens.getBearerToken())
                .header("JwtBearerToken",tokens.getJwtBearerToken())
                .build();
    }
    @PostMapping("/authenticationFromFacade")
    public ResponseEntity<String> ClientProfileAuthenticationFromFacade(
            @RequestBody BearerOrJwtBearerToken bearerOrJwtBearerToken) {
        // Проверка того, что токен начинается с "Bearer " и токен есть в бд
        if (bearerOrJwtBearerToken.getBearerOrJwtBearerToken().startsWith("Bearer ") &&
                profileTokenServiceImpl.findBearerTokenBool(
                bearerOrJwtBearerToken.getBearerOrJwtBearerToken())) {
            return ResponseEntity.ok().body("Bearer токен найден");
            // Проверка того, что токен начинается с "JwtBearer " и токен есть в бд
            } else if (bearerOrJwtBearerToken.getBearerOrJwtBearerToken().startsWith("JwtBearer ") &&
                profileTokenServiceImpl.findJwtBearerTokenBool(
                        bearerOrJwtBearerToken.getBearerOrJwtBearerToken())) {
            return ResponseEntity.ok().body("JwtBearer токен найден");
        }
        return ResponseEntity.badRequest().body("Token Invalid");
    }
    @PostMapping("/authenticationFromFacadeValidation")
    public ResponseEntity<String> ClientProfileAuthenticationFromFacadeValidation(
            @RequestBody BearerOrJwtBearerToken bearerOrJwtBearerToken) {
        // Проверка того, что токен начинается с "Bearer " и токен есть в бд + докручена валидация

        if (bearerOrJwtBearerToken.getBearerOrJwtBearerToken().startsWith("Bearer ") &&
                profileTokenServiceImpl.findBearerTokenBool(
                        bearerOrJwtBearerToken.getBearerOrJwtBearerToken()) &&
                profileTokenServiceImpl.serviceValidateBearerOrJwtBearerToken(
                        bearerOrJwtBearerToken.getBearerOrJwtBearerToken()
                )) {
            return ResponseEntity.ok().body("Bearer токен найден, token valid");
        } else if (bearerOrJwtBearerToken.getBearerOrJwtBearerToken().startsWith("Bearer ") &&
                profileTokenServiceImpl.findBearerTokenBool(
                        bearerOrJwtBearerToken.getBearerOrJwtBearerToken()) &&
                !profileTokenServiceImpl.serviceValidateBearerOrJwtBearerToken(
                        bearerOrJwtBearerToken.getBearerOrJwtBearerToken()
                )) {
            return ResponseEntity.ok().body("Bearer токен найден, token Invalid");
            // Проверка того, что токен начинается с "JwtBearer " и токен есть в бд + докручена валидация
        } else if (bearerOrJwtBearerToken.getBearerOrJwtBearerToken().startsWith("JwtBearer ") &&
                profileTokenServiceImpl.findJwtBearerTokenBool(
                        bearerOrJwtBearerToken.getBearerOrJwtBearerToken()) &&
                profileTokenServiceImpl.serviceValidateBearerOrJwtBearerToken(
                        bearerOrJwtBearerToken.getBearerOrJwtBearerToken()
                )) {
            return ResponseEntity.ok().body("JwtBearer токен найден, token valid");
        } else if (bearerOrJwtBearerToken.getBearerOrJwtBearerToken().startsWith("JwtBearer ") &&
                profileTokenServiceImpl.findJwtBearerTokenBool(
                        bearerOrJwtBearerToken.getBearerOrJwtBearerToken()) &&
                !profileTokenServiceImpl.serviceValidateBearerOrJwtBearerToken(
                        bearerOrJwtBearerToken.getBearerOrJwtBearerToken()
                )) {
            return ResponseEntity.ok().body("JwtBearer токен найден, token Invalid");
            // Проверка того, что префикс токена верен
        } else if (!bearerOrJwtBearerToken.getBearerOrJwtBearerToken().startsWith("JwtBearer ") &&
                (!bearerOrJwtBearerToken.getBearerOrJwtBearerToken().startsWith("Bearer "))) {
            return ResponseEntity.badRequest().body("Incorrect token");
        }
        return ResponseEntity.badRequest().body("Token Invalid");
    }
}


