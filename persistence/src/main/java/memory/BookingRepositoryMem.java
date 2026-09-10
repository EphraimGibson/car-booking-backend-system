package memory;

import entity.Booking;
import entity.User;
import repository.IBookingRepository;

import java.util.*;

public class BookingRepositoryMem implements IBookingRepository {

    Map<String, Booking> bookings = new HashMap<>();
    @Override
    public Booking createBooking(Booking booking) {
        String id = UUID.randomUUID().toString();
        booking.setId(id);
        bookings.put(id, booking);

        return booking;
    }

    @Override
    public void deleteBooking(String id) {

    }

    @Override
    public List<Booking> getUserBookings(User user) {
        return List.of();
    }

    @Override
    public List<Booking> getAllBookings() {
        return List.of();
    }
}