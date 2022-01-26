package com.example.AirlineManagementSystem.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    @ApiModelProperty(notes = "The Airline Company Name", name = "airline", required = true, value = "SagiZZAirline", dataType = "Object")
    @NotBlank
    private String name;

    @Column
    @ApiModelProperty(notes = "The Airline initial Budget", name = "initialBudget", required = true, value = "1000", dataType = "Integer")
    @NotNull
    private Integer initialBudget;

    @Column(name = "current_balance")
    @ApiModelProperty(notes = "The Airline Current Balance", name = "currentBalance", required = true, value = "15000", dataType = "Integer")
    @NotNull
    private Integer currentBalance;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "home_base", referencedColumnName = "id")
    @ApiModelProperty(notes = "The Airline Home Base ", name = "homeBase", required = true, value = "Tel Aviv", dataType = "Destination")
    @NotNull
    private Destination homeBase;

    @OneToMany(
            mappedBy = "airline",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @ApiModelProperty(notes = "The Airline fleet ", name = "airCrafts", required = true, value = "someValue", dataType = "List<AirCraft>")
    private List<AirCraft> airCrafts;


}
