package memoryPersistence;

import entity.Car;
import repository.ICarRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
        return List.of(allCars.values().toArray(new Car[0]));
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
    public Car findById(String id) {
        return allCars.get(id);
    }
}