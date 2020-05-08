package com.example.cars.repository;

import com.example.cars.model.Car;
import com.example.cars.model.OtherInformation;
import com.example.cars.model.Price;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CarRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository carRepository;

    @Test
    public void whenFindAll_thenReturnCar() {
        // given
        Car car1 = new Car("Audi", "A4", 10000L, 24L);
        car1.addPrice(new Price(4562F));
        entityManager.persist(car1);
        entityManager.flush();

        // when
        List<Car> found = carRepository.findAll();

        // then
        assertThat(found, is(notNullValue()));
        assertThat(found.size(), is(1));
        assertThat(found.get(0).getMake(), is("Audi"));
        assertThat(found.get(0).getModel(), is("A4"));
        assertThat(found.get(0).getMileage(), is(10000L));
        assertThat(found.get(0).getTerm(), is(24L));
    }

    @Test
    public void whenCarWithSamePropertiesSaved_thenAddPriceToSameCar() {
        // given
        Car car1 = new Car("Audi", "A4", 10000L, 24L);
        car1.addPrice(new Price(4562F));
        entityManager.persist(car1);
        entityManager.flush();

        Car car2 = new Car("Audi", "A4", 10000L, 24L);
        car2.addPrice(new Price(488F));

        // when
        carRepository.save(car2);

        // then
        List<Car> found = carRepository.findAll();
        assertThat(found, is(notNullValue()));
        assertThat(found.size(), is(1));
        assertThat(found.get(0).getPrices().size(), is(2));
    }

    @Test
    public void whenCarWithSamePropertiesSaved_thenAddOtherInfoToSameCar() {
        // given
        Car car1 = new Car("Audi", "A4", 10000L, 24L);
        car1.addPrice(new Price(4562F));
        car1.addOtherInformation(new OtherInformation("color", "red"));
        entityManager.persist(car1);
        entityManager.flush();

        Car car2 = new Car("Audi", "A4", 10000L, 24L);
        car2.addOtherInformation(new OtherInformation("driver", "female"));


        // when
        carRepository.save(car2);

        // then
        List<Car> found = carRepository.findAll();
        assertThat(found, is(notNullValue()));
        assertThat(found.size(), is(1));
        assertThat(found.get(0).getOtherInformation().size(), is(2));
    }

    @Test
    public void whenCarWithSamePropertiesSaved_thenAddOtherInfoAndPriceToSameCar() {
        // given
        Car car1 = new Car("Audi", "A4", 10000L, 24L);
        car1.addPrice(new Price(4562F));
        car1.addOtherInformation(new OtherInformation("color", "red"));
        entityManager.persist(car1);
        entityManager.flush();

        Car car2 = new Car("Audi", "A4", 10000L, 24L);
        car2.addPrice(new Price(488F));
        car2.addOtherInformation(new OtherInformation("driver", "female"));


        // when
        carRepository.save(car2);

        // then
        List<Car> found = carRepository.findAll();
        assertThat(found, is(notNullValue()));
        assertThat(found.size(), is(1));
        assertThat(found.get(0).getOtherInformation().size(), is(2));
        assertThat(found.get(0).getPrices().size(), is(2));
    }

    @Test
    public void whenCarWithDifferentPropertiesSaved_thenSaveTwoCars() {
        // given
        Car car1 = new Car("Audi", "A4", 10000L, 24L);
        car1.addPrice(new Price(4562F));
        entityManager.persist(car1);
        entityManager.flush();

        Car car2 = new Car("Audi", "A5", 10000L, 24L);
        car2.addPrice(new Price(488F));

        // when
        carRepository.save(car2);

        // then
        List<Car> found = carRepository.findAll();
        assertThat(found, is(notNullValue()));
        assertThat(found.size(), is(2));
    }
}