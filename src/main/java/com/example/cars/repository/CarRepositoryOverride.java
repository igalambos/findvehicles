package com.example.cars.repository;

import com.example.cars.model.Car;

public interface CarRepositoryOverride {
    Car customSave(Car car);
}
