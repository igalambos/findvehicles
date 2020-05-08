package com.example.cars.repository;

import com.example.cars.model.Car;
import com.example.cars.model.OtherInformation;
import com.example.cars.model.Price;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class CarRepositoryImpl implements CarRepositoryOverride {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Car customSave(Car car) {
        String make = car.getMake();
        String model = car.getModel();
        Long mileage = car.getMileage();
        Long term = car.getTerm();

        TypedQuery<Car> query = em.createQuery(
                "SELECT c FROM Car c WHERE c.make = :make AND c.model = :model " +
                        "AND c.mileage = :mileage AND c.term = :term", Car.class);
        List<Car> existingCars = query
                .setParameter("make", make)
                .setParameter("model", model)
                .setParameter("mileage", mileage)
                .setParameter("term", term)
                .getResultList();

        if (!existingCars.isEmpty()) {
            Car existingCar = existingCars.get(0);
            //update the price of the existing car
            for (Price price : car.getPrices()) {
                existingCar.addPrice(price);
            }
            // update other information too
            for (OtherInformation info : car.getOtherInformation()) {
                existingCar.addOtherInformation(info);
            }
            em.persist(existingCar);
            return existingCar;
        } else {
            //we save the car as it is
            em.persist(car);
            return car;
        }

    }
}
