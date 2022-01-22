package com.example.AirlineManagementSystem.repository;

import com.example.AirlineManagementSystem.dto.AirlineNameAndCurrentBalance;
import com.example.AirlineManagementSystem.model.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirlineRepo extends JpaRepository<Airline, Long> {

    @Query(value = "SELECT Airline.name ,Airline.current_balance FROM Airline", nativeQuery = true)
    List<AirlineNameAndCurrentBalance> finAllSortByCurrentBalanceDesc();
}
