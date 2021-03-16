package com.simran;

public class PaymentService
{
    private BookingService bookingService;

    public PaymentService(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public void paymentInitiated(String bookingId,String userId)
    {
       bookingService.pendingBooking(bookingId,userId);
    }

    public void paymentSuccess(String bookingId, String userId)
    {
        bookingService.confirmBooking(bookingId, userId);
    }

    public void paymentFailed(String bookingId,String  userId)
    {
        bookingService.failBooking(bookingId,userId);
    }
}
