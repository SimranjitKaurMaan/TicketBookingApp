package com.simran;

import com.simran.models.SeatStatus;
import com.simran.models.Show;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ShowService
{
    Map<String,Show> ShowMap;

    public ShowService(Map<String, Show> showMap) {
        this.ShowMap = showMap;
    }

    public List<String> getSeats(String showId)
    {
        Show show = ShowMap.get(showId);
        Map<String,SeatStatus> seatsMap = show.getScreen().getSeatsMap();
        List<String> availableSeats = new ArrayList<>();
        Iterator it = seatsMap.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry element = (Map.Entry)it.next();
            SeatStatus seatStatus = (SeatStatus) element.getValue();
            if(seatStatus == SeatStatus.AVAILABLE)
            {
                availableSeats.add((String)element.getKey());
            }
        }

        return availableSeats;
    }


    public void UpdateSeatStatus(String showId, List<String> selectedSeats, SeatStatus seatStatus)
    {
        Show show = this.ShowMap.get(showId);
        Map<String,SeatStatus> seatStatusMap = show.getScreen().getSeatsMap();
        for(String seatId : selectedSeats)
        {
            seatStatusMap.put(seatId,seatStatus);
        }

    }
}
