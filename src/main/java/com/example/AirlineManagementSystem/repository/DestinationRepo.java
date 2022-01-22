package com.example.AirlineManagementSystem.repository;


import com.example.AirlineManagementSystem.model.Destination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DestinationRepo extends JpaRepository<Destination, Long> {
}
