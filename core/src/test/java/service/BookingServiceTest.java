package service;

import entity.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.IBookingRepository;
import repository.ICarRepository;
import repository.IUserRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    @Mock
    IBookingRepository bookingRepository;

    @Mock
    IUserRepository userRepository;

    @Mock
    ICarRepository carRepository;

    @InjectMocks
    BookingService bookingService;

    @Test
    void testMakeBookingSuccessful() {
        // Given
        User testUser = new User("John");
        testUser.setId("1");

        Car testCar = new Car("Benz", "A183jdn", BigDecimal.valueOf(59.23), Brand.MERCEDES, false);
        testCar.setId("2");

        Booking booking = new Booking(testUser, testCar, LocalDate.of(2026, 1, 8), LocalDate.of(2026, 1, 15));

        when(userRepository.findById(any())).thenReturn(testUser);
        when(carRepository.findById(any())).thenReturn(testCar);
        when(bookingRepository.createBooking(any())).thenReturn(booking);

        // When
        Booking result = bookingService.makeBooking(booking);

        // Then
        Assertions.assertEquals(booking, result);
        Assertions.assertNotNull(booking.getStatus());
        Assertions.assertEquals(BookingStatus.ACTIVE, result.getStatus());


        verify(bookingRepository, times(1)).createBooking(booking);
        verify(userRepository, times(1)).findById(testUser.getId());
        verify(carRepository, times(1)).findById(testCar.getId());
    }

    @Test
    void testMakeBookingUserWithoutIdThrowsError() {
        //Given
        User testUser = new User("John");

        Car testCar = new Car("Benz", "A183jdn", BigDecimal.valueOf(59.23), Brand.MERCEDES, false);

        Booking booking = new Booking(testUser, testCar, LocalDate.of(2026, 1, 8), LocalDate.of(2026, 1, 15));

        //When and then
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> bookingService.makeBooking(booking));
        Assertions.assertEquals("Invalid user for booking, user has no ID", exception.getMessage());
    }

    @Test
    void testMakeBookingCarWithoutIdThrowsError() {
        //Given
        User testUser = new User("John");
        testUser.setId("1");

        Car testCar = new Car("Benz", "A183jdn", BigDecimal.valueOf(59.23), Brand.MERCEDES, false);

        when(userRepository.findById(any())).thenReturn(testUser);

        Booking booking = new Booking(testUser, testCar, LocalDate.of(2026, 1, 8), LocalDate.of(2026, 1, 15));

        //When and then
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> bookingService.makeBooking(booking));
        Assertions.assertEquals("Invalid car for booking, car has no ID", exception.getMessage());

        verify(userRepository, times(1)).findById(testUser.getId());
    }


    @Test
    void testGetAllBookingsSuccessfully() {
        //Given
        List<Booking> testBookings = List.of(new Booking(), new Booking());
        when(bookingRepository.getAllBookings()).thenReturn(testBookings);

        //When
        List<Booking> result = bookingService.getAllBookings();

        //Then
        Assertions.assertEquals(testBookings.size(), result.size(), "Expected number of bookings does not match");
        Assertions.assertSame(testBookings, result);

        verify(bookingRepository, times(1)).getAllBookings();

    }
}