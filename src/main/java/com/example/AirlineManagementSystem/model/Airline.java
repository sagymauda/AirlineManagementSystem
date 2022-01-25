package com.example.AirlineManagementSystem.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity

@Table(name = "Airline")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Airline {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;


    @Column
    private Integer initialBudget;

    @Column(name = "current_balance")
    private Integer currentBalance;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "home_base", referencedColumnName = "id")
    private Destination homeBase;

    @OneToMany(
            mappedBy = "airline",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<AirCraft> airCrafts;


}
