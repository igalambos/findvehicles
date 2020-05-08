package com.example.cars.controller;

import com.example.cars.model.Car;
import com.example.cars.model.Price;
import com.example.cars.service.CarService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarService carService;

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
    public void testGetAllCarsSuccess() throws Exception {
        given(this.carService.findAllVehicles()).willReturn(cars);
        this.mockMvc.perform(get("/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.[0].make").value("Audi"))
                .andExpect(jsonPath("$.[0].model").value("A4"))
                .andExpect(jsonPath("$.[1].make").value("Audi"))
                .andExpect(jsonPath("$.[1].model").value("A5"));
    }

}