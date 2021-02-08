/*
package com.freenow.service.booking;

import com.freenow.dataaccessobject.CarRepository;
import com.freenow.dataaccessobject.DriverRepository;
import com.freenow.domainobject.CarDO;
import com.freenow.domainobject.DriverDO;
import com.freenow.domainvalue.EngineType;
import com.freenow.domainvalue.Manufacturer;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class DefaultBookingServiceTest {

    @Autowired
    DefaultBookingService bookingService;




    @Test
    @DisplayName("Testing Booking Car For Driver ")
    void shouldAbleToBookCar() {
        Manufacturer mockManufacturer = new Manufacturer("Honda","Accord");
        CarDO mockCarDO = new CarDO("KA54TH",5,true,4.5, EngineType.GAS,mockManufacturer);
        DriverDO mockDriverDO = new DriverDO("Free Now","Password");
        CarDO bookedCarDO = bookingService.bookCar(mockDriverDO,mockCarDO);
        assertFalse(bookedCarDO.getIsAvaliable());
    }

    @Test
    void unBookCar() {
    }
}
*/
