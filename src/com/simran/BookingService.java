package com.simran;

import com.simran.exceptions.BadRequestException;
import com.simran.exceptions.UnAvailableSeatException;
import com.simran.models.Booking;
import com.simran.models.SeatStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingService
{
    Map<String, Booking> bookingMap;
    private ShowService showService;

    public BookingService(ShowService showService) {
        this.bookingMap = new HashMap<>();
        this.showService = showService;
    }

    public Booking bookSeats(String userId, String showId, List<String> selectedSeats)
    {
        List<String> availableSeats = showService.getSeats(showId);
        for(String seatId : selectedSeats)
        {
            if(!availableSeats.contains(seatId))
                throw new UnAvailableSeatException();
        }
        Booking booking = new Booking(userId,showId,selectedSeats);
        String bookingId = booking.getId();
        bookingMap.put(bookingId,booking);
        showService.UpdateSeatStatus(showId,selectedSeats, SeatStatus.TEMPORARILY_UNAVAILABLE);
        return bookingMap.get(bookingId);
    }

    public void confirmBooking(String bookingId, String userId)
    {
        Booking booking = bookingMap.get(bookingId);
        if (!booking.getUserId().equals(userId)) {
            throw new BadRequestException();
        }

        booking.confirmBooking();
        showService.UpdateSeatStatus(booking.getShowId(),booking.getSeatsBooked(),SeatStatus.PERMANENTLY_UNAVAILABLE);
    }

    public void failBooking(String bookingId, String userId)
    {
        Booking booking = bookingMap.get(bookingId);
        if (!booking.getUserId().equals(userId)) {
            throw new BadRequestException();
        }

        booking.failBooking();
        showService.UpdateSeatStatus(booking.getShowId(),booking.getSeatsBooked(),SeatStatus.AVAILABLE);
    }

    public void pendingBooking(String bookingId, String userId)
    {
        Booking booking = bookingMap.get(bookingId);
        if (!booking.getUserId().equals(userId)) {
            throw new BadRequestException();
        }
        booking.pendingBooking();
    }
}
