package org.kata.clientprofileservice.repository;

import org.kata.entity.document.Documents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentsRepo extends JpaRepository<Documents, String> {
    @Query("SELECT d FROM Documents d WHERE d.individual.icp = :icp")
    Documents findByUserIcp(@Param("icp") String icp);
}
