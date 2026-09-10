package memory;

import entity.Car;
import repository.ICarRepository;

import java.util.*;

public class CarRepositoryMem implements ICarRepository {

    Map<String, Car> allCars = new HashMap<>();

    @Override
    public Car saveCar(Car car) {
        String id = UUID.randomUUID().toString();
        car.setId(id);
        allCars.put(id, car);
        
        return car;
    }

    @Override
    public List<Car> getAllCars() {
        return List.of();
    }

    @Override
    public List<Car> getAllAvailableCars() {
        return List.of();
    }

    @Override
    public List<Car> allElectricCars() {
        return List.of();
    }

    @Override
    public Car getCarById(String id) {
        //TODO don't return null, maybe throw exception

        return null;
    }
}