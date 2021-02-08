/*
package com.freenow.controller;


import com.freenow.FreeNowServerApplicantTestApplication;
import com.freenow.datatransferobject.CarDTO;
import com.freenow.domainvalue.EngineType;
import com.freenow.domainvalue.Manufacturer;
import com.freenow.service.Car.CarServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CarControllerTest {

    @MockBean
    private CarServiceImpl carService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("GET /healthcheck")
    public void testHealthCar() throws Exception {
       mockMvc.perform(get("/v1/cars/healthcheck")).andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /find/{carId}")
    public void testGetCar() throws Exception{
        Manufacturer testManufacturer = new Manufacturer("Honda","Accord");
        CarDTO testCarDTO = new CarDTO(5L,"GN54TH",5,
                Boolean.FALSE,4.6, EngineType.GAS,testManufacturer);
        when(carService.getCar(5l)).thenReturn(testCarDTO);
        mockMvc.perform(get("/v1/cars/find/5")).andExpect(status().isOk());
    }

*/
/*
    @Test
    @DisplayName("GET /getCar/{carId}  ")
    public void testGetCar() throws Exception {
         mockMvc.perform(get("v1/cars/getCar/5")).andExpect(status().isOk());
    }
*//*

    public void testAddCar() {
    }


    public void testUpdateCar() {
    }


    public void testDeleteCar() {
    }

    public void testGetAllCars() {
    }
}
*/
