package com.ms.notion.models;

import com.ms.notion.enums.ParameterDescriptionEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "TBPARAMETER")
public class Parameter implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false,name = "DESCRIPTION")
    @Enumerated(EnumType.STRING)
    private ParameterDescriptionEnum description;

    @Column(nullable = false)
    private String value;
}
