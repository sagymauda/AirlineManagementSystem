package com.example.AirlineManagementSystem.service;


import com.example.AirlineManagementSystem.dto.DestinationsAndDistances;
import com.example.AirlineManagementSystem.model.AirCraft;
import com.example.AirlineManagementSystem.model.Airline;
import com.example.AirlineManagementSystem.model.Destination;
import com.example.AirlineManagementSystem.repository.AirlineRepo;
import com.example.AirlineManagementSystem.repository.DestinationRepo;
import com.example.AirlineManagementSystem.utils.HaversineFormula;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@Log4j2
public class DestinationService {

    @Autowired
    DestinationRepo destinationRepo;

    @Autowired
    AirlineRepo airlineRepo;

    @Autowired
    HaversineFormula haversineFormula;

    @Transactional
    public void saveDestination(Destination destination) {
        destinationRepo.save(destination);
    }

    @Transactional
    public List<DestinationsAndDistances> GetAllDistancesFromAirlineHomeBase(Long airlineId) {

        Airline airline = airlineRepo.getById(airlineId);
        List<DestinationsAndDistances> destinationsAndDistancesList = new ArrayList<>();

        // lets say just for now that it return me the latitude and longitude
        airline.getHomeBase().getLocation().getLatitude();
        double latitude = airline.getHomeBase().getLocation().getLatitude();
        double longitude = airline.getHomeBase().getLocation().getLongitude();
        List<Destination> destinations = destinationRepo.findAll();

        for (Destination dest : destinations) {
            if (airline.getHomeBase().getName() != dest.getName()) {
                dest.getLocation().getLatitude();
                dest.getLocation().getLongitude();
                Double distanceFromHomeBase = haversineFormula.haversine(latitude, longitude, dest.getLocation().getLatitude(), dest.getLocation().getLongitude());
                DestinationsAndDistances destinationsAndDistances = new DestinationsAndDistances();
                destinationsAndDistances.setDestinationName(dest.getName());
                destinationsAndDistances.setDistanceFromHomeBse(distanceFromHomeBase);
                destinationsAndDistancesList.add(destinationsAndDistances);
            }
        }
        return destinationsAndDistancesList;
    }

    @Transactional
    public List<Destination> getAllDestinationsOfAirlineByHomeBaseAndMaxDistanceOfPlain(Long airlineId) {

        //object inside lambda needs to be final
        Airline airline = airlineRepo.getById(airlineId);
        List<AirCraft> airCrafts = airline.getAirCrafts();
        List<Destination> destinationList = destinationRepo.findAll();


        Integer maxDistance = airCrafts.stream()
                .max(Comparator.comparing(AirCraft::getMaxDistance))
                .map(i -> i.getMaxDistance()).orElse(0);

        final Double lan = airline.getHomeBase().getLocation().getLatitude();
        final Double longitude = airline.getHomeBase().getLocation().getLongitude();

        return destinationList.stream().filter(p -> maxDistance > haversineFormula.haversine(
                        lan,
                        longitude,
                        p.getLocation().getLatitude(),
                        p.getLocation().getLongitude()))
                .collect(Collectors.toList());

    }

}







