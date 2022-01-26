package com.example.AirlineManagementSystem.utils;

import org.springframework.stereotype.Component;


@Component
public class CalculateAirCraftPrice{


    public double calculatePrice(Integer airCraftOriginalPrice, Integer numOfMonthsInUse) {
        return airCraftOriginalPrice * (1 - numOfMonthsInUse * 0.02);
    }
}
