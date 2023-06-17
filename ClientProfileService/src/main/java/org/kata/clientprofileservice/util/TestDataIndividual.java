package org.kata.clientprofileservice.util;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
public class TestDataIndividual {

    private String icp;
    private String name;
    private String surname;

    public TestDataIndividual() {
        super();
    }

    public TestDataIndividual(TestDataIndividualBuilder testDataIndividualBuilder) {
        if (testDataIndividualBuilder == null) {
            throw new IllegalArgumentException("Please provide TestDataIndividual builder to build TestDataIndividual object.");
        }
        if  (testDataIndividualBuilder.icp == null) {
            throw new IllegalArgumentException("Please provide valid individual icp.");
        }
        this.icp = testDataIndividualBuilder.icp;
        this.name = testDataIndividualBuilder.name;
        this.surname = testDataIndividualBuilder.surname;
    }

    @Component
    @NoArgsConstructor
    public static class TestDataIndividualBuilder {
        private String icp;
        private String name;
        private String surname;

        public TestDataIndividualBuilder icp(String icp) {
            this.icp = icp;
            return this;
        }

        public TestDataIndividualBuilder name(String name) {
            this.name = name;
            return this;
        }

        public TestDataIndividualBuilder surname(String surname) {
            this.surname = surname;
            return this;
        }

        public TestDataIndividual build() {
            TestDataIndividual testData = null;
            if (validateIndividual()) {
                testData = new TestDataIndividual(this);
            } else {
                System.out.println("Sorry! TestDataIndividual objects can't be build without required details.");
            }
            return testData;
        }

        private boolean validateIndividual() {
            return (icp != null && !icp.trim().isEmpty());
        }

    }
}
