package service;

import entity.Car;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import repository.ICarRepository;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CarService {
    private ICarRepository carRepository;

    public Car createCar(Car car) {
        return carRepository.saveCar(car);
    }

    public List<Car> getAllCars() {
        return carRepository.getAllCars();
    }

    public List<Car> getAllAvailableCars() {
        return carRepository.getAllAvailableCars();
    }

    public List<Car> getAllElectricCars() {
        return carRepository.allElectricCars();
    }

    public Car getCarById(String id) {
        return carRepository.findById(id);
    }
}