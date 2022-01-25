package com.example.AirlineManagementSystem.model;

import lombok.*;
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

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private Airline airline;
}
