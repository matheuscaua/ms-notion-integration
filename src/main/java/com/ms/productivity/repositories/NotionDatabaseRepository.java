package com.ms.productivity.repositories;

import com.ms.productivity.models.notion.NotionDatabaseIntegration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NotionDatabaseRepository extends JpaRepository<NotionDatabaseIntegration, UUID> {
}
