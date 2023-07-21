package com.ms.productivity.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "TBPRODUCTIVITY")
@AllArgsConstructor
@NoArgsConstructor
public class Productivity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "YSAVEDATE",nullable = false)
    private LocalDateTime saveDate;
    @Column(name = "XVALPRODUCTIVITY")
    private Integer productivity;
    @Column(name = "TOTALPOINTS")
    private Integer total;
    @Column(name = "TOTALITEMS")
    private Integer totalItems;
    @Column(name = "COMPLETEDITEMS")
    private Integer completedItems;
}
