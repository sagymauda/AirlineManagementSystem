package com.example.AirlineManagementSystem.dto;

import com.example.AirlineManagementSystem.model.AirCraft;

import java.util.Comparator;

public class SortByMaxDistance implements Comparator<AirCraft> {


    public int compare(AirCraft p1, AirCraft p2) {
        return p1.getMaxDistance()-p2.getMaxDistance();
    }
}

