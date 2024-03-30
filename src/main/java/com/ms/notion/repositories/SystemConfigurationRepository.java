package com.ms.notion.repositories;

import com.ms.notion.dtos.configuration.SystemConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SystemConfigurationRepository extends JpaRepository<SystemConfiguration, UUID> {

    SystemConfiguration findByAttributeName(String attributeName);
}
