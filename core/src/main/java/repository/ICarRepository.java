package repository;

import entity.Car;

import java.util.List;

public interface ICarRepository {

    Car saveCar(Car car);

    List<Car> getAllCars();

    List<Car> getAllAvailableCars();

    List<Car> allElectricCars();

    Car getCarById(String id);
}