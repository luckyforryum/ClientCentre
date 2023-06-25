package org.kata.clientprofileloader;

import org.kata.entity.contactmedium.ContactMedium;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class ClientProfileLoaderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientProfileLoaderApplication.class, args);
    }

}
