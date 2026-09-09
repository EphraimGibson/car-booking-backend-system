package service;

import dtos.CreateCarDto;
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

    public Car createCar(CreateCarDto createCarDto) {
        Car car = new Car();
        car.setModel(createCarDto.getModel());
        car.setRegistrationNumber(createCarDto.getRegNumber());
        car.setPricePerDay(createCarDto.getPricePerDay());
        car.setBrand(createCarDto.getBrand());
        car.setElectric(createCarDto.isElectric());

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
        return carRepository.getCarById(id);
    }
}