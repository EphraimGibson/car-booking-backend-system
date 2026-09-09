package memory;

import entity.Car;
import repository.ICarRepository;

import java.util.ArrayList;
import java.util.List;

public class CarRepositoryMem implements ICarRepository {

    List<Car> allCars = new ArrayList<>();

    @Override
    public Car saveCar(Car car) {

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