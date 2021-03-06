package com.example.AirlineManagementSystem.utils;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;

@Component
public class CalculateAirCraftDate {
    public Integer calculateMountsInUse(Timestamp createdDate) {

        LocalDateTime manufactureDate = createdDate.toLocalDateTime();
        LocalDateTime currentDate = LocalDateTime.now();
        long monthsBetween = ChronoUnit.MONTHS.between(
                YearMonth.from(manufactureDate),
                YearMonth.from(currentDate));

        return Math.toIntExact(monthsBetween);
    }
}
