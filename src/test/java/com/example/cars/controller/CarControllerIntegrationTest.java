package com.example.cars.controller;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = {"data.folder=testData"})
@AutoConfigureMockMvc
public class CarControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenAccessRoot_shouldReturnAllVehicles() throws Exception {
        this.mockMvc.perform(get("/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].make").value("audi"))
                .andExpect(jsonPath("$.[0].model").value("a4"))
                .andExpect(jsonPath("$.[1].make").value("audi"))
                .andExpect(jsonPath("$.[1].model").value("a5"));
    }

    @Test
    public void whenAccessFindvehiclesWithoutParams_shouldReturnAllVehicles() throws Exception {
        this.mockMvc.perform(get("/findvehicles")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].make").value("audi"))
                .andExpect(jsonPath("$.[0].model").value("a4"))
                .andExpect(jsonPath("$.[1].make").value("audi"))
                .andExpect(jsonPath("$.[1].model").value("a5"));
    }


    @Test
    public void whenAccessFindvehiclesWithParams_shouldReturnFilteredVehicles() throws Exception {
        this.mockMvc.perform(get("/findvehicles?model=a4")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[0].make").value("audi"))
                .andExpect(jsonPath("$.[0].model").value("a4"));

    }

    @Test
    public void testFindVehiclesWithNonExistingModel() throws Exception {
        this.mockMvc.perform(get("/findvehicles?model=b6")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(0)));

    }

}