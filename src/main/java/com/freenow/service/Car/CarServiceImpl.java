package com.freenow.service.Car;

import com.freenow.dataaccessobject.CarRepository;
import com.freenow.dataaccessobject.DriverRepository;
import com.freenow.domainobject.CarDO;
import com.freenow.domainobject.DriverDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CarServiceImpl implements CarService{

    private static final Long NONE = 0L;
    
    @Autowired
    CarRepository carRepository;

    @Autowired
    DriverRepository driverRepository;

    @Override
    public CarDO saveCar(CarDO carDO) {
        log.info("saving car to repository with ID "+carDO.getId()+" and license plate "+carDO.getLicensePlate());
        return carRepository.save(carDO);
    }

    public CarDO getCarById(Long carId){
       Optional<CarDO> carDO = carRepository.findById(carId);
       if(carDO.isPresent()){
           log.info("car exist with carId "+carId+" and license plate "+carDO.get().getLicensePlate());
           return carDO.get();
       }
       log.info("No car exist with carId "+carId);
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

             currentDriver.setCarSelected(true);
             currentDriver.setCarId(carId);
             log.info(" Driver with ID "+currentDriver.getId()+ " has booked car with Id"+selectedCar.getId());
             return selectedCar;
        }
        log.info(" Diver "+ driverDO.get().getId()+ " not able to select car "+carDO.get().getId());
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
            currentDriver.setCarSelected(false);
            currentDriver.setCarId(DriverDO.NONE);
            log.info(" Driver with ID "+currentDriver.getId()+ " has deselected car with Id"+selectedCar.getId());
            return selectedCar;
        }
        log.info(" Diver "+ driverDO.get().getId()+ " not able to deselect car "+carDO.get().getId());
        return null;
    }

    @Override
    public List<CarDO> getAllCars() {
        log.info("Fetching all the cars in the database ");
        return carRepository.findAll();
    }

    @Override
    public CarDO updateCar(Long carId, CarDO newCarDo) {
        Optional<CarDO> carDO = carRepository.findById(carId);
        if(carDO.isPresent()){
            CarDO oldCarDo = carDO.get();
            newCarDo.setId(oldCarDo.getId());
            carRepository.save(newCarDo);
            log.info("A car "+ carId+" details has been updated");
            return newCarDo;
        }
        log.info("No car found with ID "+carId);
        return null;
    }


    @Override
    public void deleteCar(Long carId) {
         carRepository.deleteById(carId);
        log.info(" Car "+carId+" has been deleted ");
    }
}
