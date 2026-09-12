package app;

import entity.Brand;
import entity.Car;
import entity.User;
import filePersistence.UserRepositoryFile;
import lombok.Getter;
import memoryPersistence.BookingRepositoryMem;
import memoryPersistence.CarRepositoryMem;
import repository.IBookingRepository;
import repository.ICarRepository;
import repository.IUserRepository;
import service.BookingService;
import service.CarService;
import service.UserService;

import java.math.BigDecimal;

@Getter
public class ApplicationContext {

    private final IBookingRepository bookingRepository = new BookingRepositoryMem();
    private final IUserRepository userRepository = new UserRepositoryFile();
    private final ICarRepository carRepository = new CarRepositoryMem();


    private final BookingService bookingService = new BookingService(bookingRepository, carRepository, userRepository);
    private final UserService userService = new UserService(userRepository);
    private final CarService carService = new CarService(carRepository);


    public static ApplicationContext getContext() {
        ApplicationContext applicationContext = new ApplicationContext();
        applicationContext.start();
        return applicationContext;
    }

    private void start() {
        init();
    }

    private void init() {
        for (int i = 1; i < 6; i++) {
            userService.createUser(new User("User " + i));
            carService.createCar(new Car("Benz " + i, "A1527" + i,
                    new BigDecimal("63.5" + i), Brand.MERCEDES, true));
        }
    }


}