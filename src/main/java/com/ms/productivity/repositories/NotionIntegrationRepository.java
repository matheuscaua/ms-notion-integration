package com.ms.productivity.repositories;

import com.ms.productivity.models.notion.NotionIntegration;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public interface NotionIntegrationRepository extends JpaRepository<NotionIntegration, UUID> {

    Optional<NotionIntegration> findNotionIntegrationByName(String name);

}