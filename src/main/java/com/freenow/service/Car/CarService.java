package com.freenow.service.Car;

import com.freenow.datatransferobject.DriverDTO;
import com.freenow.domainobject.CarDO;
import com.freenow.exception.CarAlreadyInUseException;

import java.util.List;

public interface   CarService {

    CarDO saveCar(CarDO carDo);

    CarDO updateCar(Long carId, CarDO carDto);


    void deleteCar(Long carId);

    CarDO getCarById(Long carId);


    CarDO selectCar(Long driverId, Long carId) throws CarAlreadyInUseException;

    CarDO deSelectCar(Long driverId, Long carId);

    List<CarDO> getAllCars();

}
