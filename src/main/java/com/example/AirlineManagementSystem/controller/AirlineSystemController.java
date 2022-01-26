package com.example.AirlineManagementSystem.controller;


import com.example.AirlineManagementSystem.dto.AirlineNameAndCurrentBalance;
import com.example.AirlineManagementSystem.dto.DestinationsAndDistances;
import com.example.AirlineManagementSystem.model.AirCraft;
import com.example.AirlineManagementSystem.model.Airline;
import com.example.AirlineManagementSystem.model.Destination;
import com.example.AirlineManagementSystem.service.AirCraftService;
import com.example.AirlineManagementSystem.service.AirlineService;
import com.example.AirlineManagementSystem.service.DestinationService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@Log4j2
public class AirlineSystemController {

    @Autowired
    AirlineService airlineService;

    @Autowired
    AirCraftService airCraftService;

    @Autowired
    DestinationService destinationService;


    @ApiOperation(value = "Create An Airline", tags = "addAirline")
    @PostMapping("/airline")
    @ResponseStatus(HttpStatus.CREATED)
    public void addAirline(@RequestBody @Valid Airline airline) {

        airlineService.saveAirline(airline);
    }

    @ApiOperation(value = "Get Distances List From A given Airline HomeBae ", response = DestinationsAndDistances.class, tags = "getDistancesFromAirlineHomeBase")
    @GetMapping("airline/distance")
    @ResponseStatus(HttpStatus.OK)
    public List<DestinationsAndDistances> distancesFromAirlineHomeBase(@RequestParam Long airlineId) {

        return destinationService.GetAllDistancesFromAirlineHomeBase(airlineId);
    }


    @ApiOperation(value = "Create A destination ", tags = "addDestination")
    @PostMapping("/destination")
    @ResponseStatus(HttpStatus.OK)
    public void addDestination(@RequestBody @Valid Destination destination) {

        destinationService.saveDestination(destination);
    }

    @ApiOperation(value = "Return A list of Destinations that an Airline can Fly to ", tags = "getAllDestinationsOfAnAirlineByHomeBaseAndMaxDistanceOfPlain")
    @GetMapping("destination/available")
    @ResponseStatus(HttpStatus.OK)
    public List<Destination> getAllDestinationsOfAnAirlineByHomeBaseAndMaxDistanceOfPlain(@RequestParam Long airlineId) {
        return destinationService.getAllDestinationsOfAirlineByHomeBaseAndMaxDistanceOfPlain(airlineId);
    }


    @ApiOperation(value = "Return A list of Airlines Names And their Current Balance", tags = "retrieveListOfAirlinesAndTheirCurrentBalance")
    @GetMapping("/airlinesAndCurrentBalance")
    @ResponseStatus(HttpStatus.OK)
    public List<AirlineNameAndCurrentBalance> retrieveListOfAirlinesAndTheirCurrentBalance() {
        return airlineService.getAllAirlinesWithCurrentBalance();
    }

    @ApiOperation(value = "Add AirCraft To An Airline  ", tags = "addAirCraft")
    //An option to add an aircraft to a specific airline
    @PostMapping("/aircraft/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addAirCraft(@RequestBody @Valid AirCraft aircraft, @PathVariable Long id) {
        airCraftService.saveAirCraft(aircraft, id);
    }


    @ApiOperation(value = "create  A transaction Between 2 Airlines tTo Trade An AirCraft ", tags = "tradeAirCraft")
    @PostMapping("/aircraft/sell")
    @ResponseStatus(HttpStatus.OK)
    public void tradeAirCraft(@RequestParam @Valid Long airCraftId,
                              @RequestParam Long sellingAirlineId,
                              @RequestParam Long buyingAirlineId) {
        airCraftService.trade(airCraftId, sellingAirlineId, buyingAirlineId);


    }
}
