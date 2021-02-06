package com.freenow.service.Car;

import com.freenow.controller.mapper.CarMapper;
import com.freenow.dataaccessobject.CarRepository;
import com.freenow.dataaccessobject.DriverRepository;
import com.freenow.datatransferobject.CarDTO;
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

    public CarDO getCarById(Long carId) throws CarNotFoundException {
        return carRepository.findById(carId).orElseThrow(()->new CarNotFoundException("No Car Found With ID"+carId));
    }
    public CarDTO processCarCreateRequest(CarDTO carDto) {
        CarDO carDO = CarMapper.makeCarDo(carDto);
        return CarMapper.makeCarDTO(carRepository.save(carDO));
    }
    @Override
    public CarDO selectCar(Long driverId, Long carId) throws CarAlreadyInUseException,DriverNotFound,CarNotFoundException {
        DriverDO currentDriver = driverRepository.findById(driverId).
                orElseThrow(()->new DriverNotFound("No Driver Found With ID"+driverId));
        CarDO selectedCar = carRepository.findById(carId).
                orElseThrow(()->new CarNotFoundException("No Car Found With ID"+carId));
        log.info("Booking car "+selectedCar.getId()+"for driver "+currentDriver.getId());
        return toggleCarBook(currentDriver,selectedCar);
    }


    @Override
    public CarDO deSelectCar(Long driverId, Long carId) throws DriverNotFound, CarNotFoundException {
        DriverDO currentDriver = driverRepository.findById(driverId).
                orElseThrow(()->new DriverNotFound("No Driver Found With ID"+driverId));
        CarDO selectedCar = carRepository.findById(carId).
                orElseThrow(()->new CarNotFoundException("No Car Found With ID"+carId));
        return toggleCarBook(currentDriver,selectedCar);
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

    private CarDO toggleCarBook(DriverDO currentDriver,CarDO selectedCar){
        if(currentDriver.getCarId().equals(null)){
            return bookCar(currentDriver,selectedCar);
        }
        return unBookcar(currentDriver,selectedCar);
    }

    private CarDO bookCar(DriverDO currentDriver,CarDO selectedCar){
        selectedCar.setBookedBy(currentDriver.getId());
        selectedCar.setIsAvaliable(false);
        currentDriver.setCarSelected(true);
        currentDriver.setCarId(selectedCar.getId());
        log.info(" Driver with ID " + currentDriver.getId() + " has booked car with Id" + selectedCar.getId());
        return selectedCar;
    }
    private CarDO unBookcar(DriverDO currentDriver,CarDO selectedCar){
        selectedCar.setBookedBy(currentDriver.getId());
        selectedCar.setIsAvaliable(true);
        currentDriver.setCarSelected(false);
        currentDriver.setCarId(NONE);
        log.info(" Driver with ID " + currentDriver.getId() + " has booked car with Id" + selectedCar.getId());
        return selectedCar;
    }
}
