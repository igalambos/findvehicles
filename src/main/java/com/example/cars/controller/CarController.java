package com.example.cars.controller;

import com.example.cars.model.Car;
import com.example.cars.service.CarService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/")
    public List<Car> findAllVehicles() {
        return carService.findAllVehicles();
    }

    @GetMapping("/findvehicles")
    public List<Car> findVehicles(
            @RequestParam(value = "make", required = false) String make,
            @RequestParam(value = "model", required = false) String model,
            @RequestParam(value = "mileage", required = false) Long mileage,
            @RequestParam(value = "term", required = false) Long term) {

        return carService.findVehicles(make, model, mileage, term);
    }

}
