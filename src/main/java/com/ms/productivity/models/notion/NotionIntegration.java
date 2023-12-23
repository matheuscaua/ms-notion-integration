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

    @Column(name = "NAME")
    private String name;
    @Column(name = "URI")
    private URI uri;
    private String token;
    @Column(name = "SAVE_DATE")
    private LocalDateTime saveDate;
    @Column(name = "LAST_UPDATE")
    private LocalDateTime lastUpdate;

    @OneToOne
    private NotionParametersIntegration authorizationIntegration;

    @OneToMany
    private List<NotionDatabaseIntegration> notionDatabaseIntegration;
}
