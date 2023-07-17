package com.ms.productivity.repositories;

import com.ms.productivity.models.Productivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductivityRepository extends JpaRepository<Productivity, UUID> {

}
