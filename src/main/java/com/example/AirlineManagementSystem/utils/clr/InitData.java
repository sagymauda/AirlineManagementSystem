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
        Airline sellingAirline = Airline.builder().name("sellingAirline")
                .initialBudget(1000)
                .currentBalance(1000)
                .homeBase(Destination.builder().name("SellingHOmeBase").
                        location(Location.builder().latitude(22.1325).longitude(123.6324).build()).build()).build();

        Airline buyingAirline = Airline.builder().name("buyingAirline")
                .initialBudget(2000)
                .currentBalance(2000)
                .homeBase(Destination.builder().name("SellingHOmeBase").
                        location(Location.builder().latitude(123.4575).longitude(23.3216).build()).build()).build();

        List<Airline> airLines = new ArrayList<>();
        airLines.add(sellingAirline);
        airLines.add(buyingAirline);
        airlineRepo.saveAll(airLines);


        AirCraft airCraft1 = new AirCraft();
        airCraft1.setAirline(sellingAirline);
        airCraft1.setOriginalPrice(100);
        airCraft1.setMaxDistance(6000);

        AirCraft airCraft2 = new AirCraft();
        airCraft2.setAirline(sellingAirline);
        airCraft2.setOriginalPrice(50);
        airCraft2.setMaxDistance(4000);

        AirCraft airCraft3 = new AirCraft();
        airCraft3.setAirline(sellingAirline);
        airCraft3.setOriginalPrice(50);
        airCraft3.setMaxDistance(20000);

        List<AirCraft> airCrafts = new ArrayList<>();
        airCrafts.add(airCraft1);
        airCrafts.add(airCraft2);
        airCrafts.add(airCraft3);
        airCraftRepo.saveAll(airCrafts);


        Destination hawaii = Destination.builder().name("Hawaii")
                .location(Location.builder().latitude(19.8968).longitude(155.5828).build()).build();

        Destination thailand = Destination.builder().name("Thailand")
                .location(Location.builder().latitude(15.8700).longitude(100.9925).build()).build();

        List<Destination> destinations = new ArrayList<>();
        destinations.add(hawaii);
        destinations.add(thailand);
        destinationRepo.saveAll(destinations);



        airCraftRepo.findAll().forEach(i -> {
            System.out.println(i.getId() + " " + i.getOriginalPrice());
        });


        airlineRepo.findAll().forEach(i -> {
            System.out.println(i.getName() + " " + i.getCurrentBalance());
        });

        List.of(1, 2, 3, 4);

        Airline airline = airlineRepo.getById(1L);
        System.out.println(airline);
    }
}