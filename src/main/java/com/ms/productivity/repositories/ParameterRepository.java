package com.ms.productivity.repositories;

import com.ms.productivity.models.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ParameterRepository extends JpaRepository<Parameter, UUID> {

    @Query(value = "SELECT * FROM TBPARAMETER WHERE DESCRIPTION = :description", nativeQuery = true)
    Optional<Parameter> findParameterByDescription(@Param("description") String descripition);

}
