package service;

import entity.Booking;
import entity.BookingStatus;
import entity.Car;
import entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import repository.IBookingRepository;
import repository.ICarRepository;
import repository.IUserRepository;
import utils.StringUtility;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookingService {

    private IBookingRepository bookingRepository;
    private ICarRepository carRepository;
    private IUserRepository userRepository;

    public Booking makeBooking(Booking booking) {
        if (booking.getUser() != null && !StringUtility.isStringNullOrBlank(booking.getUser().getId())) {
            User existingUser = userRepository.findById(booking.getUser().getId());
            if (existingUser == null) {
                throw new IllegalArgumentException("User not found");
            }
        } else {
            throw new IllegalArgumentException("Invalid user for booking, user has no ID");
        }

        if (booking.getCar() != null && !StringUtility.isStringNullOrBlank(booking.getCar().getId())) {
            Car car = carRepository.findById(booking.getCar().getId());
            if (car == null) {
                throw new IllegalArgumentException("Car not found");
            }
        } else {
            throw new IllegalArgumentException("Invalid car for booking, car has no ID");
        }

        booking.setStatus(BookingStatus.ACTIVE);
        return bookingRepository.createBooking(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.getAllBookings();
    }

}