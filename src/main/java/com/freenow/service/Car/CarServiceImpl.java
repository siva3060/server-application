package com.freenow.service.Car;

import com.freenow.dataaccessobject.CarRepository;
import com.freenow.dataaccessobject.DriverRepository;
import com.freenow.domainobject.CarDO;
import com.freenow.domainobject.DriverDO;
import com.freenow.exception.CarAlreadyInUseException;
import com.freenow.exception.CarNotFoundException;
import com.freenow.exception.DriverNotFound;
import com.freenow.exception.EntityNotFoundException;
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
        log.info("saving car to repository with license plate "+carDO.getLicensePlate());
        return carRepository.save(carDO);
    }

    public CarDO getCarById(Long carId) throws CarNotFoundException {
       return carRepository.findById(carId).orElseThrow(()->new CarNotFoundException("No Car Found With ID"+carId));
    }

    @Override
    public CarDO selectCar(Long driverId, Long carId) throws CarAlreadyInUseException,DriverNotFound,CarNotFoundException {
        DriverDO currentDriver = driverRepository.findById(driverId).
                orElseThrow(()->new DriverNotFound("No Driver Found With ID"+driverId));
        CarDO selectedCar = carRepository.findById(carId).
                orElseThrow(()->new CarNotFoundException("No Car Found With ID"+carId));
        log.info("Booking car "+selectedCar.getId()+"for driver "+currentDriver.getId());
        return bookCar(currentDriver,selectedCar);
    }

    private CarDO bookCar(DriverDO currentDriver,CarDO selectedCar){
        selectedCar.setBookedBy(currentDriver.getId());
        selectedCar.setIsAvaliable(false);
        currentDriver.setCarSelected(true);
        currentDriver.setCarId(selectedCar.getId());
        log.info(" Driver with ID " + currentDriver.getId() + " has booked car with Id" + selectedCar.getId());
        return selectedCar;
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
