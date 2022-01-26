package com.example.AirlineManagementSystem.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "aircraft")
@Data
@Builder
public class AirCraft {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @ApiModelProperty(notes = "Original price of The Aircraft", name = "originalPrice", required = true, value = "10000", dataType = "Integer")
    @NotNull
    private Integer originalPrice;


    @ApiModelProperty(notes = "Max Distance An AirCraft can Fly", name = "maxDistance", required = true, value = "5000", dataType = "Integer")
    @NotNull
    private Integer maxDistance;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate;

    @ToString.Exclude
    @ManyToOne()
    @ApiModelProperty(notes = "The Airline Who Owns The AirCraft", name = "airline", required = true, value = "SagiZZAirline", dataType = "Object")
    @NotNull
    private Airline airline;

}
