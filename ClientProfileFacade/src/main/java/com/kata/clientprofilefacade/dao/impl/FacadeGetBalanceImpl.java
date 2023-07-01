package com.kata.clientprofilefacade.dao.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kata.clientprofilefacade.dao.FacadeGetBalanceDao;
import com.kata.clientprofilefacade.dto.BalanceAccountDTO;
import com.kata.clientprofilefacade.entity.Currency;
import com.kata.clientprofilefacade.entity.CurrencyData;
import com.kata.clientprofilefacade.entity.Meta;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@AllArgsConstructor
@Repository
public class FacadeGetBalanceImpl implements FacadeGetBalanceDao {

    private final RestTemplate restTemplate;


    public <T> ResponseEntity <T> getBalance(String icp, String baseCurrency, String currencies) {
        String url = "http://api.currencyapi.com/v3/latest";
        HttpHeaders headers = new HttpHeaders();
        headers.add("apikey", "gITSxQ0tO3fzYg98dFPeAaI1aCvLyfVHI4zwFddB");
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("currencies", currencies)
                .queryParam("base_currency", baseCurrency);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<?> responseEntity = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                String.class
        );

        String responseBody = (String) responseEntity.getBody();

        ObjectMapper objectMapper = new ObjectMapper();
        CurrencyData currencyData;
        try {
            currencyData = objectMapper.readValue(responseBody, CurrencyData.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        Meta meta = currencyData.getMeta();
        Map<String , Currency> data = currencyData.getData();
        Currency currencyEur = data.get("EUR");
        String eurCode = currencyEur.getCode();
        double eurValue = currencyEur.getValue();

        Currency currencyUsd = data.get("USD");
        String usdCode = currencyUsd.getCode();
        double usdValue = currencyUsd.getValue();


        System.out.println("Meta " + meta);


        System.out.println("EUR Code: " + eurCode);
        System.out.println("EUR Value: " + eurValue);


        System.out.println("USD Code: " + usdCode);
        System.out.println("USD Value: " + usdValue);

        BalanceAccountDTO balance = new BalanceAccountDTO();
        balance.setIcp(icp);
        balance.setBalanceRub(150000);
        balance.setBalanceUsd(balance.getBalanceRub() * Double.valueOf(usdValue));
        balance.setBalanceEur(balance.getBalanceRub() * Double.valueOf(eurValue));

        return ResponseEntity.ok((T) balance);
    }
}
