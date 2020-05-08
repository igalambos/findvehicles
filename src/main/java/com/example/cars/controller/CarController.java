package com.example.cars.controller;

import com.example.cars.model.Car;
import com.example.cars.repository.CarRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CarController {
    private final CarRepository repository;

    CarController(CarRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/findvehicles")
    public List<Car> findvehicles() {
        return repository.findAll();
    }

}
