package com.ms.notion.repositories;

import com.ms.notion.models.notion.NotionDatabaseIntegration;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NotionDatabaseRepository extends JpaRepository<NotionDatabaseIntegration, UUID> {

    @Override
    List<NotionDatabaseIntegration> findAll();
}
