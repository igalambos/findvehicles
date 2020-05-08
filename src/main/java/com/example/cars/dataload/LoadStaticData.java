package com.example.cars.dataload;

import com.example.cars.model.Car;
import com.example.cars.model.Price;
import com.example.cars.repository.CarRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Slf4j
@Component
public class LoadStaticData implements CommandLineRunner {

    private final CarRepository repository;

    public LoadStaticData(CarRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        Car car1 = new Car("Audi", "A4", 10000L, 24L);
        car1.addPrice(new Price(4562F));
        log.info("Preloading " + repository.save(car1));

        Car car2 = new Car("Audi", "A5", 10000L, 24L);
        car2.addPrice(new Price(4562F));
        log.info("Preloading " + repository.save(car2));
        Car car3 = new Car("Audi", "A5", 10000L, 24L);
        car3.addPrice(new Price(4588F));
        log.info("Preloading " + repository.save(car3));
    }
}
