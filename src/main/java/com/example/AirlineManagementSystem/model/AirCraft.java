package com.example.AirlineManagementSystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="aircraft")
@Data
@Builder
public class AirCraft {

    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;


    private Integer originalPrice;

    private Integer maxDistance;


    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airline_id")
    private Airline airline;
}
