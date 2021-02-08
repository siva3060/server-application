package com.freenow.service.Car;

import com.freenow.datatransferobject.CarDTO;
import com.freenow.datatransferobject.DriverDTO;
import com.freenow.domainobject.CarDO;
import com.freenow.exception.CarAlreadyInUseException;
import com.freenow.exception.CarNotFoundException;
import com.freenow.exception.DriverNotFound;
import com.freenow.exception.EntityNotFoundException;

import java.util.List;

public interface   CarService {

    CarDTO processCarCreateRequest(CarDTO carDTO);
    List<CarDO> getAllCars();
    CarDTO updateCar(Long carId, CarDTO carDTO) throws CarNotFoundException;
    void deleteCar(Long carId) throws CarNotFoundException;
    CarDTO getCar(Long carId) throws CarNotFoundException;

    CarDTO selectCar(Long driverId, Long carId) throws CarAlreadyInUseException, DriverNotFound, CarNotFoundException;
    CarDTO deSelectCar(Long driverId, Long carId) throws DriverNotFound, CarNotFoundException, CarAlreadyInUseException;


}
