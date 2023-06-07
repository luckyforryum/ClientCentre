package com.kata.clientprofilefacade.service.impl;

import com.kata.clientprofilefacade.dao.IndividualMaskDao;
import com.kata.clientprofilefacade.service.IndividualMaskService;
import lombok.AllArgsConstructor;
import org.kata.entity.individual.Individual;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class IndividualMaskServiceImpl implements IndividualMaskService {

    private final IndividualMaskDao individualMaskDao;

    @Override
    public Individual maskName(Individual individual) {
        return individualMaskDao.maskName(individual);
    }
}
