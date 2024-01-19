package com.ms.productivity.repositories;

import com.ms.productivity.models.notion.NotionDatabaseIntegration;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface NotionDatabaseRepository extends JpaRepository<NotionDatabaseIntegration, UUID> {
}
