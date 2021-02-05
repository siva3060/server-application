package com.freenow.service.Car;

import com.freenow.domainobject.CarDO;

public interface   CarService {

    CarDO saveCar(CarDO carDo);

    CarDO updateCar(Long carId, CarDO carDto);


    void deleteCar(Long carId);

    CarDO getCarById(Long carId);


    CarDO selectCar(Long driverId, Long carId);

    CarDO deSelectCar(Long driverId, Long carId);
}
