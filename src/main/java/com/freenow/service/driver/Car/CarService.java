package com.freenow.service.driver.Car;

import com.freenow.domainobject.CarDO;

public interface   CarService {

    CarDO saveCar(CarDO carDo);

    CarDO updateCar(Long carId, CarDO carDto);


    void deleteCar(Long carId);

    CarDO getCarById(Long carId);
}
