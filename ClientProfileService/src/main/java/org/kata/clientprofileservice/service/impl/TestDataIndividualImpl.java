package org.kata.clientprofileservice.service.impl;

import lombok.AllArgsConstructor;
import org.kata.clientprofileservice.repository.IndividualRepo;

import org.kata.clientprofileservice.service.TestDataIndividual;
import org.kata.clientprofileservice.util.GenerateUtil;
import org.kata.entity.individual.Individual;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class TestDataIndividualImpl implements TestDataIndividual {

    private final KafkaTemplate<String, List<Individual>> kafkaTemplate;
    private final IndividualRepo individualRepo;
    private final GenerateUtil generateUtil;
    private static final String TOPIC = "TestUsers" ;


    @Override
    public void createIndividuals(int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("The number of objects created must be greater than zero.");
        }
        List<Individual> individualList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Individual individual = individualRepo.save(generateUtil.generateRandomIndividual());
            individualList.add(individual);
        }
        kafkaTemplate.send(TOPIC, individualList);
    }
}
