package com.example.AirlineManagementSystem.dto;


public interface DestinationsAndDistanceFromAirlineHomeBase {

    String getName();
    Location getLocation();

    interface Location {
        String getAltitude();

        String getLongitude();
    }
}

