package com.ms.productivity.models.notion;

import com.ms.productivity.dtos.integrations.NotionParametersDatabaseDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
public class NotionDatabaseIntegration implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "NAME_DATABASE")
    private String name;

    @OneToOne
    private NotionParametersIntegration parameters;

    @ManyToOne
    private NotionIntegration notionIntegration;
}
