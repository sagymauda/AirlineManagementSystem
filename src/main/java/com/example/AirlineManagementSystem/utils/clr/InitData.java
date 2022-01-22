package com.example.AirlineManagementSystem.utils.clr;

import com.example.AirlineManagementSystem.model.AirCraft;
import com.example.AirlineManagementSystem.model.Airline;
import com.example.AirlineManagementSystem.model.Destination;
import com.example.AirlineManagementSystem.model.Location;
import com.example.AirlineManagementSystem.repository.AirCraftRepo;
import com.example.AirlineManagementSystem.repository.AirlineRepo;
import com.example.AirlineManagementSystem.repository.DestinationRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Order(1)
public class InitData implements CommandLineRunner {

    private final AirlineRepo airlineRepo;
    private final AirCraftRepo airCraftRepo;
    private final DestinationRepo destinationRepo;


    @Override
    public void run(String... args) throws Exception {

        Airline sellingAirline = Airline.builder().name("sellingAirline").initialBudget(1000).currentBalance(1000).build();
       Airline buyingAirline = Airline.builder().name("buyingAirline").initialBudget(2000).currentBalance(2000).build();
        List<Airline> airLines = new ArrayList<>();
        airLines.add(sellingAirline);
        airLines.add(buyingAirline);
        airlineRepo.saveAll(airLines);


        AirCraft airCraft1 = AirCraft.builder().airline(sellingAirline).originalPrice(100).maxDistance(60000)
                .airline(sellingAirline).createdDate(Timestamp.valueOf(LocalDateTime.now())).build();
        AirCraft airCraft2 = AirCraft.builder().airline(sellingAirline).originalPrice(50).maxDistance(40000)
                .airline(sellingAirline).createdDate(Timestamp.valueOf(LocalDateTime.now())).build();

        List<AirCraft> airCrafts = new ArrayList<>();
        airCrafts.add(airCraft1);
        airCrafts.add(airCraft2);
        airCraftRepo.saveAll(airCrafts);



        Destination hawaii = Destination.builder().name("Hawaii")
                .location(Location.builder().latitude(19.8968).longitude(155.5828).build()).build();

        Destination thailand = Destination.builder().name("Thailand")
                .location(Location.builder().latitude(15.8700).longitude(100.9925).build()).build();

        List<Destination> destinations = new ArrayList<>();
        destinations.add(hawaii);
        destinations.add(thailand);
        destinationRepo.saveAll(destinations);

    }

}