package com.freenow.service.booking;

import com.freenow.domainobject.CarDO;
import com.freenow.domainobject.DriverDO;

public interface BookingService {
    CarDO bookCar(DriverDO currentDriver, CarDO selectedCar);

    CarDO unBookCar(DriverDO currentDriver, CarDO selectedCar);
}
