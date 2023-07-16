package com.ms.productivity.repositories;

import com.ms.productivity.models.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParameterRepositoty extends JpaRepository {

    Optional<Parameter> findParameterByDescription(String descripition);

}
