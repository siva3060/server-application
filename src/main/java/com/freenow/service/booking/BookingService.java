package com.freenow.service.booking;

import com.freenow.domainobject.CarDO;
import com.freenow.domainobject.DriverDO;
import com.freenow.exception.CarAlreadyInUseException;

public interface BookingService {
    CarDO bookCar(DriverDO currentDriver, CarDO selectedCar) throws CarAlreadyInUseException;

    CarDO unBookCar(DriverDO currentDriver, CarDO selectedCar) throws CarAlreadyInUseException;
}
