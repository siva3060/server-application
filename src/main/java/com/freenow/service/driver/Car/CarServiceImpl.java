package com.freenow.service.driver.Car;

import com.freenow.dataaccessobject.CarRepository;
import com.freenow.domainobject.CarDO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class CarServiceImpl implements CarService{

    @Autowired
    CarRepository carRepository;

    @Override
    public CarDO saveCar(CarDO carDO) {
        return carRepository.save(carDO);
    }

    public CarDO getCarById(Long carId){
       Optional<CarDO> carDO = carRepository.findById(carId);
       if(carDO.isPresent()){
           return carDO.get();
       }
       return null;
    }
    @Override
    public CarDO updateCar(Long carId, CarDO newCarDo) {
        Optional<CarDO> carDO = carRepository.findById(carId);
        if(carDO.isPresent()){
            CarDO oldCarDo = carDO.get();
            newCarDo.setId(oldCarDo.getId());
            carRepository.save(newCarDo);
        }
        return null;
    }


    @Override
    public void deleteCar(Long carId) {

    }
}
