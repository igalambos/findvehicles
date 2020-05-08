package com.example.cars.controller;

import com.example.cars.model.Car;
import com.example.cars.service.CarService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/")
    public List<Car> findAllvehicles() {
        return carService.findAllVehicles();
    }

}
