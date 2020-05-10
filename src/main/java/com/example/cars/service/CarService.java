package com.example.cars.service;

import com.example.cars.model.Car;

import java.util.List;

public interface CarService {

    List<Car> findAllVehicles();

    List<Car> findVehicles(String make, String model, Long mileage, Long term);

    Car save(Car car);
}
