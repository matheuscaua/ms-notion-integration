package com.ms.productivity.models;

import com.ms.productivity.enums.ParameterDescriptionEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "PARAMETER")
public class Parameter implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ParameterDescriptionEnum description;

    @Column(nullable = false)
    private String value;
}
