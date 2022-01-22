package com.example.AirlineManagementSystem.service;

import com.example.AirlineManagementSystem.model.AirCraft;
import com.example.AirlineManagementSystem.model.Airline;
import com.example.AirlineManagementSystem.repository.AirCraftRepo;
import com.example.AirlineManagementSystem.repository.AirlineRepo;
import com.example.AirlineManagementSystem.utils.CalculateAirCraftDate;
import com.example.AirlineManagementSystem.utils.CalculateAirCraftPrice;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
@Slf4j
public class AirCraftService {

    @Autowired
    AirCraftRepo airCraftRepo;

    @Autowired
    AirlineRepo airlineRepo;

    @Autowired
    CalculateAirCraftPrice calculateAirCraftPrice;

    @Autowired
    CalculateAirCraftDate calculateAirCraftDate;


    @SneakyThrows
    public void saveAirCraft(AirCraft airCraft, Long id) {
        log.info("A new Request Has Been Made To Save Airline With Details : {}, ", airCraft);
        Airline airline = airlineRepo.getById(id);
        airCraft.setAirline(airline);

        airCraftRepo.save(airCraft);


    }


    public void trade(Long airCraftId, Long sellingAirlineId, Long buyingAirlineId) {

        double sellingPrice;
        log.info("A new Request Has Been Made To Trade Aircraft With id" + " " + airCraftId + "From company id" + " " + sellingAirlineId +
                " " + "To Company id" + "  " + buyingAirlineId);


        AirCraft airCraftToSell = airCraftRepo.getById(airCraftId);
        /*check if airCraft is owned by selling company*/
        if (Objects.equals(airCraftToSell.getAirline().getId(), sellingAirlineId)) {
            /*then calculate the num of moths in use*/
          //  Integer numOfMonthsInUse = calculateAirCraftDate.calculateMountsInUse(airCraftToSell.getCreatedDate());
            sellingPrice = calculateAirCraftPrice.calculatePrice(airCraftToSell.getOriginalPrice(), 6);

            /*now I need to see if the other company have that amount of money*/
            Airline buyingAirline = airlineRepo.getById(buyingAirlineId);
            Airline sellingAirline = airlineRepo.getById(sellingAirlineId);
            //if true means I can sell the plain
            if (buyingAirline.getCurrentBalance() > sellingPrice) {

                //i un assign the air plain from the selling company
                sellingAirline.getAirCrafts().remove(airCraftToSell);

                //then I assign it to the buying company
                airCraftToSell.setAirline(buyingAirline);

                //then i lowe the money of the buying company and add it to the selling company
                buyingAirline.setCurrentBalance((int) (sellingAirline.getCurrentBalance() - sellingPrice));

                // I am adding the price to the selling Air Line
                sellingAirline.setCurrentBalance((int) (sellingAirline.getCurrentBalance() + sellingPrice));
                airCraftRepo.save(airCraftToSell);
                airlineRepo.save(sellingAirline);
                airlineRepo.save(buyingAirline);

            } else {
                log.error("The Buying Company Does Not Have Enough Money To Buy The Air Craft ");
            }
        }

    }

}
