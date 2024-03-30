package com.ms.notion.repositories;

import com.ms.notion.models.productivity.NotionDatabaseProductivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductivityRepository extends JpaRepository<NotionDatabaseProductivity, UUID> {

}
