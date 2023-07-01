package com.kata.clientprofilefacade.controller;

import com.kata.clientprofilefacade.service.FacadeGetBalanceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class BalanceController {

    private final FacadeGetBalanceService facadeGetBalanceService;

    @GetMapping("/balance")
    public <T> ResponseEntity <T> getBalance(@RequestParam("icp") String icp, @RequestParam("base_currency") String baseCurrency, @RequestParam("currencies") String currencies)  {
        return facadeGetBalanceService.getBalance(icp, baseCurrency, currencies);
    }
}
