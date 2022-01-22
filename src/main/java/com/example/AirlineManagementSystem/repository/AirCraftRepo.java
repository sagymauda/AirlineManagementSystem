package com.example.AirlineManagementSystem.repository;

import com.example.AirlineManagementSystem.model.AirCraft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirCraftRepo extends JpaRepository<AirCraft,Long> {
}
