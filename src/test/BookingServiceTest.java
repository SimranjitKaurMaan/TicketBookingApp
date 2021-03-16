package test;

import com.simran.BookingService;
import com.simran.PaymentService;
import com.simran.ShowService;
import com.simran.exceptions.UnAvailableSeatException;
import com.simran.models.Booking;
import com.simran.models.Screen;
import com.simran.models.SeatStatus;
import com.simran.models.Show;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.util.*;

public class BookingServiceTest
{
    Map<String, Show> showMap = new HashMap<>();
    Map<String, SeatStatus> seatsMap = new HashMap<>();
    private ShowService showService;
    private BookingService bookingService;
    private PaymentService paymentService;

    @Before
    public void setUp() throws Exception {
        seatsMap.put("A1",SeatStatus.AVAILABLE);
        seatsMap.put("A2",SeatStatus.AVAILABLE);
        seatsMap.put("A3",SeatStatus.AVAILABLE);
        seatsMap.put("A4",SeatStatus.AVAILABLE);
        seatsMap.put("A5",SeatStatus.AVAILABLE);
        Screen screen1 = new Screen(seatsMap);
        showMap.put("S1",new Show("S1","K", LocalDateTime.now(),2,screen1));
        showService = new ShowService(showMap);
        bookingService = new BookingService(showService);
        paymentService = new PaymentService(bookingService);
    }

    @Test
    public void DefaultTest()
    {
        PrintAvailableSeats(showService);
        Booking booking = bookingService.bookSeats("U1","S1", Arrays.asList("A1","A2"));
        System.out.println(booking);
        PrintAvailableSeats(showService);
        paymentService.paymentInitiated(booking.getId(),"U1");
        System.out.println("Payment Pending: Booking " + booking);
        PrintAvailableSeats(showService);
        Booking booking1 = bookingService.bookSeats("U2","S1",Arrays.asList("A3","A4"));
        System.out.println(booking1);
        PrintAvailableSeats(showService);
        paymentService.paymentInitiated(booking1.getId(),"U2");
        paymentService.paymentSuccess(booking1.getId(),"U2");
        System.out.println("Payment Success: Booking "+ booking1);
        PrintAvailableSeats(showService);
        paymentService.paymentFailed(booking.getId(),"U1");
        System.out.println("Payment Failed: Booking  " + booking);
        PrintAvailableSeats(showService);
    }

    @Test(expected = UnAvailableSeatException.class)
    public void UseCase3Test()
    {
        //use case 3:
        PrintAvailableSeats(showService);
        Booking booking2 = bookingService.bookSeats("U3","S1", Arrays.asList("A1","A2"));
        System.out.println(booking2);
        Booking booking3 = bookingService.bookSeats("U3","S1", Arrays.asList("A1","A2"));
        System.out.println(booking3);

    }

    private void PrintAvailableSeats(ShowService showService) {
        List<String> seatsList = showService.getSeats("S1");
        System.out.println("Available Seats Now:"+seatsList);
    }
}
