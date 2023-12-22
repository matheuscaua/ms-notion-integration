package com.ms.productivity.models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
public class AuthorizationIntegration implements Serializable {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private UUID id;

    @OneToOne
    private Integration integration;
}
