package com.simran.models;


import java.util.Map;


public class Screen
{
    Map<String,SeatStatus> seatsMap;

    public Screen(Map<String, SeatStatus> seatsMap) {
        this.seatsMap = seatsMap;
    }

    public Map<String, SeatStatus> getSeatsMap() {
        return seatsMap;
    }

}
