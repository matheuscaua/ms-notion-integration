package com.ms.productivity.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Integration implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private URI uri;
    private LocalDateTime saveDate;
    private LocalDateTime lastUpdate;
}
