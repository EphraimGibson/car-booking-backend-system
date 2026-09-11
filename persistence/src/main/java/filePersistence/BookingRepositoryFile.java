package filePersistence;

import entity.Booking;
import entity.User;
import repository.IBookingRepository;

import java.util.List;

public class BookingRepositoryFile implements IBookingRepository {
    @Override
    public Booking createBooking(Booking booking) {
        return null;
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

    @Override
    public Booking findById(String id) {
        return null;
    }
}