package com.freenow.service.driver.Car;

import com.freenow.dataaccessobject.CarRepository;
import com.freenow.dataaccessobject.DriverRepository;
import com.freenow.domainobject.CarDO;
import com.freenow.domainobject.DriverDO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class CarServiceImpl implements CarService{

    private static final Long NONE = 0L;
    
    @Autowired
    CarRepository carRepository;

    @Autowired
    DriverRepository driverRepository;

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
    public CarDO selectCar(Long driverId, Long carId) {
        Optional<CarDO> carDO = carRepository.findById(carId);
        Optional<DriverDO> driverDO = driverRepository.findById(driverId);

        if(carDO.isPresent() && driverDO.isPresent()){
             CarDO selectedCar = carDO.get();
             DriverDO currentDriver = driverDO.get();

             selectedCar.setBookedBy(driverId);
             selectedCar.setIsAvaliable(false);

             currentDriver.setCarSelected(carId);
        }
        return null;
    }

    @Override
    public CarDO deSelectCar(Long driverId, Long carId) {
        Optional<CarDO> carDO = carRepository.findById(carId);
        Optional<DriverDO> driverDO = driverRepository.findById(driverId);

        if(carDO.isPresent() && driverDO.isPresent()){
            CarDO selectedCar = carDO.get();
            DriverDO currentDriver = driverDO.get();
            selectedCar.setBookedBy(NONE);
            selectedCar.setIsAvaliable(true);
            currentDriver.setCarSelected(NONE);
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
