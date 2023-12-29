package com.ms.productivity.models.notion;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class NotionIntegration implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private URI uri;
    private LocalDateTime saveDate;
    private LocalDateTime lastUpdate;

    @OneToOne
    private NotionParametersIntegration notionParametersIntegration;
    
    @OneToMany
    private List<NotionDatabaseIntegration> notionDatabaseIntegration;
}
