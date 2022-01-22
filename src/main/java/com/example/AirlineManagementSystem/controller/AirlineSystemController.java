package com.example.AirlineManagementSystem.controller;


import com.example.AirlineManagementSystem.dto.AirlineNameAndCurrentBalance;
import com.example.AirlineManagementSystem.dto.DestinationsAndDistanceFromAirlineHomeBase;
import com.example.AirlineManagementSystem.model.AirCraft;
import com.example.AirlineManagementSystem.model.Airline;
import com.example.AirlineManagementSystem.model.Destination;
import com.example.AirlineManagementSystem.service.AirCraftService;
import com.example.AirlineManagementSystem.service.AirlineService;
import com.example.AirlineManagementSystem.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
public class AirlineSystemController {

    @Autowired
    AirlineService airlineService;

    @Autowired
    AirCraftService airCraftService;

    @Autowired
    DestinationService destinationService;

    @PostMapping("/airline")
    @ResponseStatus(HttpStatus.CREATED)
    public void addAirline(@RequestBody @Valid Airline airline) {

        airlineService.saveAirline(airline);
    }

    @GetMapping("airline/distance")
    @ResponseStatus(HttpStatus.OK)
    public List<DestinationsAndDistanceFromAirlineHomeBase> retrieveListOfDistancesFromAirlineHomeBase(@RequestParam Long airlineId) {

        return destinationService.GetAllDistancesFromAirlineHomeBase(airlineId);
    }

    @PostMapping("/destination")
    @ResponseStatus(HttpStatus.OK)
    public void addDestination(@RequestBody @Valid Destination destination) {

        destinationService.saveDestination(destination);
    }

    @GetMapping("/airlinesAndCurrentBalance")
    @ResponseStatus(HttpStatus.OK)
    public List<AirlineNameAndCurrentBalance> retrieveListOfAirlinesAndTheirCurrentBalance() {
        return airlineService.getAllAirlinesWithCurrentBalance();
    }

    //An option to add an aircraft to a specific airline
    @PostMapping("/aircraft/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addAirCraft(@RequestBody @Valid AirCraft aircraft, @PathVariable Long id) {
        airCraftService.saveAirCraft(aircraft, id);
    }

    @PostMapping("/aircraft/sell")
    @ResponseStatus(HttpStatus.OK)
    public void tradeAirCraft(@RequestParam @Valid Long airCraftId,
                              @RequestParam Long sellingAirlineId,
                              @RequestParam Long buyingAirlineId) {
        airCraftService.trade(airCraftId, sellingAirlineId, buyingAirlineId);


    }
}
