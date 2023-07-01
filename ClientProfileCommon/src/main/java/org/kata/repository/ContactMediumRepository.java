package org.kata.repository;

import org.kata.entity.contactmedium.ContactMedium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactMediumRepository extends JpaRepository<ContactMedium,String> {
    ContactMedium getContactMediumByUuid (String uuid);
}
