package com.example.AirlineManagementSystem.service;

import com.example.AirlineManagementSystem.dto.DestinationsAndDistanceFromAirlineHomeBase;
import com.example.AirlineManagementSystem.model.Airline;
import com.example.AirlineManagementSystem.model.Destination;
import com.example.AirlineManagementSystem.repository.AirlineRepo;
import com.example.AirlineManagementSystem.repository.DestinationRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class DestinationService {

    @Autowired
    DestinationRepo destinationRepo;

    @Autowired
    AirlineRepo airlineRepo;

    public void saveDestination(Destination destination) {
        destinationRepo.save(destination);
    }

    public List<DestinationsAndDistanceFromAirlineHomeBase> GetAllDistancesFromAirlineHomeBase(Long airlineId) {

        Airline airline = airlineRepo.getById(airlineId);

        // lets say just for now that it return me the latitude and longitude
        airline.getHomeBase();
        double latitude = 12.000;
        double longitude = 32.7777;
       List<Destination> destinations =  destinationRepo.findAll();

        return null;


    }
}
