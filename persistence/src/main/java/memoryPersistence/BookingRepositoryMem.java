package memoryPersistence;

import entity.Booking;
import entity.User;
import repository.IBookingRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
        return List.of(bookings.values().toArray(new Booking[0]));
    }

    @Override
    public Booking findById(String id) {
        return bookings.get(id);
    }
}