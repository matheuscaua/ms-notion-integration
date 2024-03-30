package com.ms.notion.models.notion;

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

    private String name;

    @Column
    private String idNotionDatabase;

    @ManyToOne
    private NotionIntegration notionIntegration;
}
