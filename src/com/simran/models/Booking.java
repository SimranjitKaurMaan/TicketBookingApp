package com.simran.models;

import com.simran.exceptions.InvalidStateException;

import java.util.List;
import java.util.UUID;

public class Booking
{
    private String id;
    private String userId;
    private String showId;
    private List<String> seatsBooked;
    private BookingStatus bookingStatus;

    public Booking(String userId, String showId, List<String> seatsBooked) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.showId = showId;
        this.seatsBooked = seatsBooked;
        this.bookingStatus = BookingStatus.Created;
    }

    public String getShowId() {
        return showId;
    }

    public List<String> getSeatsBooked() {
        return seatsBooked;
    }

    public String getUserId() {
        return userId;
    }

    public String getId() {
        return id;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void confirmBooking()
    {
        if (this.bookingStatus != BookingStatus.Pending) {
            throw new InvalidStateException();
        }
        this.bookingStatus = BookingStatus.Confirmed;
    }

    public void pendingBooking()
    {
        if (this.bookingStatus != BookingStatus.Created) {
            throw new InvalidStateException();
        }
        this.bookingStatus = BookingStatus.Pending;
    }

    public void failBooking()
    {
        if (this.bookingStatus != BookingStatus.Pending) {
            throw new InvalidStateException();
        }
        this.bookingStatus = BookingStatus.Failed;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", showId='" + showId + '\'' +
                ", seatsBooked=" + seatsBooked +
                ", bookingStatus=" + bookingStatus +
                '}';
    }

}
