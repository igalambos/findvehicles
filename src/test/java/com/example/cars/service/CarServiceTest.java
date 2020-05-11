package com.example.cars.service;

import com.example.cars.model.Car;
import com.example.cars.model.Price;
import com.example.cars.repository.CarRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


@SpringBootTest
@RunWith(SpringRunner.class)
public class CarServiceTest {

    @Autowired
    private CarService carService;

    @MockBean
    private CarRepository carRepository;

    private List<Car> cars = new ArrayList<>();

    @Before
    public void setUp() {
        Car car1 = new Car("Audi", "A4", 10000L, 24L);
        car1.addPrice(new Price(4562F));
        cars.add(car1);

        Car car2 = new Car("Audi", "A5", 10000L, 24L);
        car2.addPrice(new Price(458F));
        cars.add(car2);
    }

    @Test
    public void shouldFindAllCars() {
        //given
        given(this.carRepository.findAll()).willReturn(cars);

        //when
        Collection<Car> cars = this.carService.findAllVehicles();

        //then
        assertThat(cars, is(notNullValue()));
        assertThat(cars.size(), is(equalTo(2)));
    }

    @Test
    public void shouldFindCars() {
        //given
        given(this.carRepository.findAll(any(Example.class))).willReturn(cars);

        //when
        Collection<Car> cars = this.carService.findVehicles(null, null, null, null);

        //then
        assertThat(cars, is(notNullValue()));
        assertThat(cars.size(), is(equalTo(2)));
    }

}