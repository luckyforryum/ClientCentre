package org.kata.clientprofileservice.util;


import org.kata.clientprofileservice.repository.IndividualRepo;
import org.kata.clientprofileservice.util.enums.*;
import org.kata.entity.individual.Individual;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class GenerateUtil {
    private final ModelMapper modelMapper;
    private final IndividualRepo individualRepo;
    private final Random random;
    private final List<NameIndividual> nameIndividuals;
    private final List<SurnameIndividual> surnameIndividuals;
    private final List<PatronymicIndividual> patronymicIndividuals;
    private final List<CountryOfBirth> countryOfBirths;
    private final List<GenderIndividual> genderIndividuals;
    private final List<PlaceOfBirth> placeOfBirths;

    public GenerateUtil(ModelMapper modelMapper, IndividualRepo individualRepo, Random random) {
        this.modelMapper = modelMapper;
        this.individualRepo = individualRepo;
        this.random = random;
        this.nameIndividuals = List.of(NameIndividual.values());
        this.surnameIndividuals = List.of(SurnameIndividual.values());
        this.patronymicIndividuals = List.of(PatronymicIndividual.values());
        this.countryOfBirths = List.of(CountryOfBirth.values());
        this.genderIndividuals = List.of(GenderIndividual.values());
        this.placeOfBirths = List.of(PlaceOfBirth.values());
    }

    public Individual generateRandomIndividual() {

        int randomIndexPlaceOfBirth = random.nextInt(placeOfBirths.size());
        int randomIndexCountry = random.nextInt(countryOfBirths.size());
        int randomIndexPatronymic = random.nextInt(patronymicIndividuals.size());
        int randomIndexName = random.nextInt(nameIndividuals.size());
        int randomIndexSurname = random.nextInt(nameIndividuals.size());
        int randomIndexGender = random.nextInt(genderIndividuals.size());

        TestDataIndividual test = TestDataIndividual.builder()
                .icp(String.valueOf(random.nextInt(900000) + 100000))
                .name(nameIndividuals.get(randomIndexName).toStringName())
                .surname(surnameIndividuals.get(randomIndexSurname).toStringSurname())
                .birthDate(getRandomDate())
                .patronymic(patronymicIndividuals.get(randomIndexPatronymic).toStringPatronymic())
                .countryOfBirth(countryOfBirths.get(randomIndexCountry).toStringCountry())
                .fullName(nameIndividuals.get(randomIndexName).toStringName() + " " +
                          patronymicIndividuals.get(randomIndexPatronymic).toStringPatronymic() + " " +
                          surnameIndividuals.get(randomIndexSurname).toStringSurname())
                .gender(genderIndividuals.get(randomIndexGender).toStringGender())
                .placeOfBirth(placeOfBirths.get(randomIndexPlaceOfBirth).toStringPlace())
                .build();

        Individual individual = modelMapper.map(test, Individual.class);
        return individual;
    }



    protected Date getRandomDate() {
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.YEAR, random.nextInt(50) + 1965);
        calendar.set(Calendar.MONTH, random.nextInt(12));
        calendar.set(Calendar.DAY_OF_MONTH, random.nextInt(calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) + 1);
        return calendar.getTime();
    }



}
