package org.kata.repository;

import org.kata.entity.individual.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address,String> {
    Address getAddressByUuid(String uuid);
}
