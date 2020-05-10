package com.example.cars.service;

import com.example.cars.model.Car;
import com.example.cars.repository.CarRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> findAllVehicles() {
        return carRepository.findAll();
    }

    @Override
    @Transactional
    public List<Car> findVehicles(String make, String model, Long mileage, Long term) {
        ExampleMatcher caseInsensitiveExampleMatcher = ExampleMatcher.matchingAll().withIgnoreCase();
        Example<Car> example = Example.of(new Car(make, model, mileage, term), caseInsensitiveExampleMatcher);

        return carRepository.findAll(example);
    }

    @Override
    @Transactional
    public Car save(Car car) {
        return carRepository.save(car);
    }

}
