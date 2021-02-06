package com.freenow.service.booking;

import com.freenow.domainobject.CarDO;
import com.freenow.domainobject.DriverDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DefaultBookingService implements BookingService{

    private static final Long NONE = 0L;

    public CarDO bookCar(DriverDO currentDriver, CarDO selectedCar){
        selectedCar.setBookedBy(currentDriver.getId());
        selectedCar.setIsAvaliable(false);
        currentDriver.setCarSelected(true);
        currentDriver.setCarId(selectedCar.getId());
        log.info(" Driver with ID " + currentDriver.getId() + " has booked car with Id" + selectedCar.getId());
        return selectedCar;
    }

    public CarDO unBookCar(DriverDO currentDriver,CarDO selectedCar){
        selectedCar.setBookedBy(currentDriver.getId());
        selectedCar.setIsAvaliable(true);
        currentDriver.setCarSelected(false);
        currentDriver.setCarId(NONE);
        log.info(" Driver with ID " + currentDriver.getId() + " has booked car with Id" + selectedCar.getId());
        return selectedCar;
    }

}
