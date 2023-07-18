package com.kata.clientprofilefacade.service;

import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IPRangeService {
    Optional<String> findCityByIpAddress(String ipAddress);
}
