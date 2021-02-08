package com.freenow.service.booking;

import com.freenow.domainobject.CarDO;
import com.freenow.domainobject.DriverDO;
import com.freenow.domainvalue.OnlineStatus;
import com.freenow.exception.CarAlreadyInUseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DefaultBookingService implements BookingService{

    private static final Long NONE = 0L;

    public CarDO bookCar(DriverDO currentDriver, CarDO selectedCar) throws CarAlreadyInUseException {
        if(selectedCar.getIsAvaliable()) {
            selectedCar.setBookedBy(currentDriver.getId());
            selectedCar.setIsAvaliable(false);
            currentDriver.setCarSelected(true);
            currentDriver.setCarId(selectedCar.getId());
            log.info(" Driver with ID " + currentDriver.getId() + " has booked car with Id" + selectedCar.getId());
            return selectedCar;
        }
        throw new CarAlreadyInUseException("Car "+selectedCar.getId()+" is already booked");
    }

    public CarDO unBookCar(DriverDO currentDriver,CarDO selectedCar) throws CarAlreadyInUseException {
        if(currentDriver.getCarId().equals(selectedCar.getLicensePlate())) {
            selectedCar.setBookedBy(currentDriver.getId());
            selectedCar.setIsAvaliable(true);
            currentDriver.setCarSelected(false);
            currentDriver.setCarId(NONE);
            log.info(" Driver with ID " + currentDriver.getId() + " has booked car with Id" + selectedCar.getId());
            return selectedCar;
        }
        throw new CarAlreadyInUseException("Car "+selectedCar.getId()+" cannot be suspended booked by other / available to book ");
    }

}
