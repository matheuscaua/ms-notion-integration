package com.ms.productivity.dtos.configuration;

import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;

@Data
@Entity
public class SystemConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column
    private String attributeName;
    @Column
    private String attributeDescription;
    @Column
    private String attributeValue;

}
