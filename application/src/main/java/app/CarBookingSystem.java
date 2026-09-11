package app;

import entity.Car;
import entity.User;

import java.util.List;

public class CarBookingSystem {


    public static void main(String[] args) {
        ApplicationContext app = ApplicationContext.getContext();

        List<User> allUsers = app.getUserService().getAllUsers();

        allUsers.forEach(System.out::println);
        System.out.println(allUsers.size());

        List<Car> allCars = app.getCarService().getAllCars();

        allCars.forEach(System.out::println);
        System.out.println(allCars.size());


    }
}