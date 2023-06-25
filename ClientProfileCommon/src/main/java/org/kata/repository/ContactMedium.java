package org.kata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactMedium extends JpaRepository<ContactMedium,String> {
    ContactMedium getContactMediumByUuid (String uuid);
}
