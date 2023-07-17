package com.ms.productivity.models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "TBPRODUCTIVITY")
public class Productivity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "YSAVEDATE",nullable = false)
    private LocalDateTime saveDate;
    @Column(name = "XVALPRODUCTIVITY")
    private Integer productivity;
}
