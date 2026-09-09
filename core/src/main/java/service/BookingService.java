package service;

import dtos.CreateBookingDto;
import entity.Booking;
import entity.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import repository.IBookingRepository;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookingService {

    private IBookingRepository bookingRepository;

    public Booking makeBooking(CreateBookingDto createBookingDto) {
        Booking booking = new Booking();
        booking.setCar(createBookingDto.getCar());
        booking.setUser(createBookingDto.getUser());
        booking.setStartDate(createBookingDto.getStartDate());
        booking.setEndDate(createBookingDto.getEndDate());
        booking.setTotalPrice(createBookingDto.getTotalPrice());
        booking.setStatus(BookingStatus.ACTIVE);
        return bookingRepository.createBooking(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.getAllBookings();
    }

}