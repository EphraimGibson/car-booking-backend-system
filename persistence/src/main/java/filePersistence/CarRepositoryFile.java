package filePersistence;

import entity.Car;
import repository.ICarRepository;

import java.util.List;

public class CarRepositoryFile implements ICarRepository {
    @Override
    public Car saveCar(Car car) {
        return null;
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
    public Car findById(String id) {
        return null;
    }
}