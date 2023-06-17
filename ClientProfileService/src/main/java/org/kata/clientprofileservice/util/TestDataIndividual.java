package org.kata.clientprofileservice.util;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Date;


@Getter
@Setter
@Component
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestDataIndividual {

    private String icp;
    private String name;
    private String surname;
    private Date birthDate;
    private String patronymic;
    private String countryOfBirth;
    private String fullName;
    private String gender;
    private String placeOfBirth;


    public static class TestDataIndividualBuilder {
        public TestDataIndividualBuilder icp(String icp) {
            if (!validateIndividual(icp)) {
                throw new IllegalArgumentException("Sorry! TestDataIndividual objects can't be build without required details.");
            }
            this.icp = icp;
            return this;
        }

        private boolean validateIndividual(String icp) {
            return (icp != null && !icp.trim().isEmpty());
        }
    }
}




