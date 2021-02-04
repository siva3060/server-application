package com.freenow.service.driver.Car;

import com.freenow.datatransferobject.CarDTO;
import com.freenow.domainobject.CarDO;

import java.util.Optional;

public interface   CarService {
    void saveCar(CarDTO carDto);

    void updateCar(Long carId, CarDTO carDto);

    Optional<CarDO> getCar(Long carId);

    void deleteCar(Long carId);
}
