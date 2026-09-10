package service;

import dtos.CreateCarDto;
import entity.Brand;
import entity.Car;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.ICarRepository;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {

    @Mock
    ICarRepository carRepository;

    @InjectMocks
    CarService carService;

    @Test
    void testShouldCreateCarSuccessfully() {
        // Given

        CreateCarDto testCar = CreateCarDto.builder()
                .model("C300")
                .regNumber("A15DFT")
                .pricePerDay(new BigDecimal("55.32"))
                .brand(Brand.MERCEDES)
                .isElectric(true)
                .build();

        when(carRepository.saveCar(any(Car.class))).thenReturn(new Car("C300",
                "A15DFT", new BigDecimal("55.32"), Brand.MERCEDES, true));
        // When
        Car result = carService.createCar(testCar);

        // Then
        Assertions.assertNotNull(result);

        assertEquals(testCar.getBrand(), result.getBrand(), "Brand does not match");
        assertEquals(testCar.getPricePerDay(), result.getPricePerDay(), "Price per day does not match");
        assertEquals(testCar.getRegNumber(), result.getRegistrationNumber(), "Registration number does not match");
        assertEquals(testCar.isElectric(), result.isElectric(), "Electric status does not match");
        assertEquals(testCar.getModel(), result.getModel(), "Model does not match");

        verify(carRepository, times(1)).saveCar(any(Car.class));
    }

    @Test
    void testNewCarShouldBeAvailable() {
        // Given
        CreateCarDto testCar = new CreateCarDto("C300", "A15DFT", new BigDecimal(55), Brand.MERCEDES, true);

        when(carRepository.saveCar(any(Car.class))).thenReturn(new Car("C300",
                "A15DFT", new BigDecimal(55), Brand.MERCEDES, true));

        // When
        Car result = carService.createCar(testCar);

        // Then
        Assertions.assertTrue(result.isAvailable());
    }

    @Test
    void testShouldThrowErrorWhenCarInputIsInvalid() {
        // Given
        CreateCarDto testCar = CreateCarDto.builder()
                .model("C300")
                .regNumber(null)
                .pricePerDay(new BigDecimal(55))
                .brand(Brand.MERCEDES)
                .isElectric(true)
                .build();

        CreateCarDto testCar2 = CreateCarDto.builder()
                .model("C300")
                .regNumber("")
                .pricePerDay(new BigDecimal(55))
                .brand(Brand.MERCEDES)
                .isElectric(true)
                .build();

        CreateCarDto testCar3 = CreateCarDto.builder()
                .model("C300")
                .regNumber("A15DFT")
                .pricePerDay(new BigDecimal(-55))
                .brand(Brand.MERCEDES)
                .isElectric(true)
                .build();

        CreateCarDto testCar4 = CreateCarDto.builder()
                .model("C300")
                .regNumber("A15DFT")
                .pricePerDay(new BigDecimal(0))
                .brand(Brand.MERCEDES)
                .isElectric(true)
                .build();

        // When & Then
        IllegalArgumentException registrationNumberCannotBeNull = Assertions.assertThrows(IllegalArgumentException.class, () -> carService.createCar(testCar), "Registration number cannot be null");
        assertEquals("Registration number cannot be empty", registrationNumberCannotBeNull.getMessage());

        IllegalArgumentException registrationNumberCannotBeEmpty = Assertions.assertThrows(IllegalArgumentException.class, () -> carService.createCar(testCar2), "Registration number cannot be empty");
        assertEquals("Registration number cannot be empty", registrationNumberCannotBeEmpty.getMessage());

        IllegalArgumentException pricePerDayCannotBeNegative = Assertions.assertThrows(IllegalArgumentException.class, () -> carService.createCar(testCar3), "Price per day cannot be negative");
        assertEquals("Price must be positive", pricePerDayCannotBeNegative.getMessage());

        IllegalArgumentException pricePerDayCannotBeZero = Assertions.assertThrows(IllegalArgumentException.class, () -> carService.createCar(testCar4), "Price per day cannot be zero");
        assertEquals("Price must be positive", pricePerDayCannotBeZero.getMessage());
    }

    @Test
    void testShouldReturnAllCars() {
        // Given
        Car car = new Car("C300", "A15DFT", new BigDecimal(55), Brand.MERCEDES, true);

        when(carRepository.getAllCars()).thenReturn(List.of(car));

        // When
        List<Car> result = carService.getAllCars();

        // Then
        Assertions.assertEquals(1, result.size(), "Expected 1 car");
        Assertions.assertEquals(car, result.getFirst(), "Expected car not found");

        verify(carRepository, times(1)).getAllCars();
    }

    @Test
    void testShouldReturnAllAvailableCars() {
        // Given
        Car car = new Car("C300", "A15DFT", new BigDecimal(55), Brand.MERCEDES, true);
        Car car2 = new Car("C300", "BAPEFT", new BigDecimal("30.1"), Brand.MERCEDES, true);

        when(carRepository.getAllAvailableCars()).thenReturn(List.of(car, car2));

        // When
        List<Car> result = carService.getAllAvailableCars();

        // Then
        Assertions.assertEquals(2, result.size(), "Expected 2 cars");
        Assertions.assertEquals(car, result.get(0), "Expected car not found");
        Assertions.assertEquals(car2, result.get(1), "Expected car not found");

        verify(carRepository, times(1)).getAllAvailableCars();
    }

    @Test
    void testShouldReturnAllElectricCars() {
        // Given
        Car car = new Car("C300", "A15DFT", new BigDecimal("55.32"), Brand.MERCEDES, true);
        Car car2 = new Car("C300", "BAPEFT", new BigDecimal("30.1"), Brand.MERCEDES, true);

        when(carRepository.allElectricCars()).thenReturn(List.of(car, car2));

        // When
        List<Car> result = carService.getAllElectricCars();

        // Then
        Assertions.assertEquals(2, result.size(), "Expected 2 electric cars");
        Assertions.assertEquals(car, result.get(0), "Expected electric car not found");
        Assertions.assertEquals(car2, result.get(1), "Expected electric car not found");

        verify(carRepository, times(1)).allElectricCars();
    }

    @Test
    void testShouldGetCarById() {
        // Given
        Car car = new Car("C300", "A15DFT", new BigDecimal("55.32"), Brand.MERCEDES, true);

        when(carRepository.getCarById("1")).thenReturn(car);

        // When
        Car result = carService.getCarById("1");

        // Then
        Assertions.assertEquals(car, result, "Expected car not found");

        verify(carRepository, times(1)).getCarById("1");
    }

}