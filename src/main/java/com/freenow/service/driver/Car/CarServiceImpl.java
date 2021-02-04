package com.freenow.service.driver.Car;

import com.freenow.dataaccessobject.CarRepository;
import com.freenow.datatransferobject.CarDTO;
import com.freenow.domainobject.CarDO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class CarServiceImpl implements CarService{

    @Autowired
    CarRepository carRepository;

    @Override
    public void saveCar(CarDTO carDto) {
        CarDO carDO = CarDoFactory(carDto);
        carRepository.save(carDO);

    }

    @Override
    public void updateCar(Long carId, CarDTO carDto) {
    }

    @Override
    public Optional<CarDO> getCar(Long carId) {
        return carRepository.findById(carId);
    }

    @Override
    public void deleteCar(Long carId) {

    }
}
