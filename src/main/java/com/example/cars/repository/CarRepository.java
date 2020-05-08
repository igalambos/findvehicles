package com.example.cars.repository;

import com.example.cars.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long>, CarRepositoryOverride {
    @Override
    @SuppressWarnings("unchecked")
    default Car save(Car entity) {
        return customSave(entity);
    }
}
