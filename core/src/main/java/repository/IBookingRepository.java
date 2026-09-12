package repository;

import entity.Booking;
import entity.User;

import java.util.List;

public interface IBookingRepository {

    Booking createBooking(Booking booking);

    void deleteBooking(String id);

    List<Booking> getUserBookings(User user);

    List<Booking> getAllBookings();

    Booking findById(String id);
}