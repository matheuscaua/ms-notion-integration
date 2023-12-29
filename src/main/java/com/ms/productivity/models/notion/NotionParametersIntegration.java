package com.ms.productivity.models.notion;

import com.ms.productivity.enums.NotionHeadersEnum;
import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
public class NotionParametersIntegration implements Serializable {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private UUID id;

    private String token;

    private String notionVersion;

    @OneToOne
    private NotionIntegration notionIntegration;
}
