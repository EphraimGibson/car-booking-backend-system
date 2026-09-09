package service;

import dtos.CreateBookingDto;
import entity.Booking;
import entity.Brand;
import entity.Car;
import entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.IBookingRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    @Mock
    IBookingRepository bookingRepository;

    @InjectMocks
    BookingService bookingService;

    @Test
    void testMakeBookingSuccessful() {
        // Given
        User testUser = new User("John");
        Car testCar = new Car("Benz", "A183jdn", BigDecimal.valueOf(59.23), Brand.MERCEDES, false);
        CreateBookingDto createBookingDto = CreateBookingDto.builder()
                .user(testUser)
                .car(testCar)
                .startDate(LocalDate.of(2026, 1, 8))
                .endDate(LocalDate.of(2026, 1, 15))
                .build();

        Booking booking = new Booking();

        when(bookingRepository.createBooking(any())).thenReturn(booking);

        // When
        Booking result = bookingService.makeBooking(createBookingDto);

        // Then
        verify(bookingRepository, times(1)).createBooking(any());
        Assertions.assertEquals(booking, result);
    }

    @Test
    void testMakeBookingWithInvalidStartDateUnsuccessful() {
        //Given
        User user = new User("John");
        Car car = new Car("Benz", "A183jdn", BigDecimal.valueOf(59.23), Brand.MERCEDES, false);

        final CreateBookingDto createBookingDto = CreateBookingDto.builder()
                .user(user)
                .car(car)
                .startDate(null)
                .endDate(LocalDate.of(2026, 1, 15))
                .build();

        //When and then
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> bookingService.makeBooking(createBookingDto));
        Assertions.assertEquals("Start date cannot be null", exception.getMessage());
    }

    @Test
    void testMakeBookingWithInvalidEndDateUnsuccessful() {
        //Given
        User user = new User("John");
        Car car = new Car("Benz", "A183jdn", BigDecimal.valueOf(59.23), Brand.MERCEDES, false);


        final CreateBookingDto createBookingDto = CreateBookingDto.builder()
                .user(user)
                .car(car)
                .startDate(LocalDate.of(2026, 1, 8))
                .endDate(null)
                .build();

        //When
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> bookingService.makeBooking(createBookingDto));
        Assertions.assertEquals("End date cannot be null", exception.getMessage());
    }


    @Test
    void testMakeBookingWithInvalidUserThrowsError() {
        //Given
        User user = null;
        Car car = new Car("Benz", "A183jdn", BigDecimal.valueOf(59.23), Brand.MERCEDES, false);

        final CreateBookingDto createBookingDto = CreateBookingDto.builder()
                .user(user)
                .car(car)
                .startDate(LocalDate.of(2026, 1, 8))
                .endDate(LocalDate.of(2026, 1, 15))
                .build();

        //When and then
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> bookingService.makeBooking(createBookingDto));
        Assertions.assertEquals("User cannot be null", exception.getMessage());
    }

    @Test
    void testMakeBookingWithInvalidCarThrowsError() {
        //Given
        User user = new User("John");
        Car car = null;
        Car car2 = new Car("Benz", "A183jdn", BigDecimal.valueOf(59.23), Brand.MERCEDES, false);

        final CreateBookingDto createBookingDto = CreateBookingDto.builder()
                .user(user)
                .car(car)
                .startDate(LocalDate.of(2026, 1, 8))
                .endDate(LocalDate.of(2026, 1, 15))
                .build();

        car2.setAvailable(false);

        final CreateBookingDto createBookingDto2 = CreateBookingDto.builder()
                .user(user)
                .car(car2)
                .startDate(LocalDate.of(2026, 1, 8))
                .endDate(LocalDate.of(2026, 1, 15))
                .build();

        //When and Then
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> bookingService.makeBooking(createBookingDto));
        Assertions.assertEquals("Car cannot be null", exception.getMessage());

        exception = Assertions.assertThrows(IllegalArgumentException.class, () -> bookingService.makeBooking(createBookingDto2));
        Assertions.assertEquals("Car is not available to be booked", exception.getMessage());
    }


    @Test
    void testMakeBookingWithNullTotalPriceThrowsError() {
        assertInvalidPriceForBooking(null);
    }

    @Test
    void testMakeBookingWithTotalPriceZeroThrowsError() {
        assertInvalidPriceForBooking(BigDecimal.ZERO);
    }

    @Test
    void testMakeBookingWithNegativeTotalPriceThrowsError() {
        assertInvalidPriceForBooking(BigDecimal.valueOf(-1));
    }

    private void assertInvalidPriceForBooking(BigDecimal price) {
        //Given
        User user = new User("John");
        Car car = new Car("Benz", "A183jdn", BigDecimal.valueOf(59.23), Brand.MERCEDES, false);

        CreateBookingDto dtoMock = mock(CreateBookingDto.class);

        when(dtoMock.getUser()).thenReturn(user);
        when(dtoMock.getCar()).thenReturn(car);
        when(dtoMock.getStartDate()).thenReturn(LocalDate.of(2026, 1, 8));
        when(dtoMock.getEndDate()).thenReturn(LocalDate.of(2026, 1, 15));
        when(dtoMock.getTotalPrice()).thenReturn(price);

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> bookingService.makeBooking(dtoMock));
        Assertions.assertEquals("Price must be positive", exception.getMessage());
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