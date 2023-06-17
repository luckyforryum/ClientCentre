package org.kata.clientprofileservice.util;


import lombok.AllArgsConstructor;
import org.kata.clientprofileservice.repository.IndividualRepo;
import org.kata.entity.individual.Individual;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;



@Component
@AllArgsConstructor
public class GenerateUtil {
    private final ModelMapper modelMapper;
    private final IndividualRepo individualRepo;

    public void generateIndividual1() {
        TestDataIndividual test1 = new TestDataIndividual.TestDataIndividualBuilder()
                .icp("123456")
                .name("Oleg")
                .surname("Dude")
                .build();

        Individual individual1 = modelMapper.map(test1, Individual.class);
        individualRepo.save(individual1);
    }










}
