package com.example.AirlineManagementSystem.utils;

import org.springframework.stereotype.Component;

import java.util.Date;


//original price * (1 - num of months aircraft in use*0.02).
@Component
public class CalculateAirCraftPrice{


    public double calculatePrice(Integer airCraftOriginalPrice, Integer numOfMonthsInUse) {


        double v = airCraftOriginalPrice * (1 - numOfMonthsInUse * 0.02);
        return v;
    }
}
