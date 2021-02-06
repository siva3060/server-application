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
import com.freenow.service.booking.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CarServiceImpl implements CarService{


    
    @Autowired
    CarRepository carRepository;

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    BookingService bookingService;


    @Override
    public CarDTO getCar(Long carId) throws CarNotFoundException {
        CarDO CarDO =  carRepository.findById(carId).orElseThrow(()->new CarNotFoundException("No Car Found With ID"+carId));
        return CarMapper.makeCarDTO(CarDO);
    }

    @Override
    public CarDTO processCarCreateRequest(CarDTO carDto) {
        CarDO carDO = CarMapper.makeCarDo(carDto);
        log.info("Saving Car into repository with Id "+ carDO.getId()+" and license plate "+ carDO.getLicensePlate());
        return CarMapper.makeCarDTO(carRepository.save(carDO));
    }

    @Override
    public CarDTO updateCar(Long carId, CarDTO carDTO) {
        CarDO carDO = carRepository.findById(carId).orElseThrow(()->new CarNotFoundException("No Car Found to update with ID"+carId));
        CarDO newCarDO = CarMapper.makeCarDo(carDTO);
        newCarDO.setId(carDO.getId());
        return CarMapper.makeCarDTO(carRepository.save(newCarDO));
    }


    @Override
    public void deleteCar(Long carId) {
        carRepository.deleteById(carId);
        log.info(" Car "+carId+" has been deleted ");
    }


    @Override
    public CarDTO selectCar(Long driverId, Long carId) throws CarAlreadyInUseException,DriverNotFound,CarNotFoundException {
        DriverDO currentDriver = driverRepository.findById(driverId).
                orElseThrow(()->new DriverNotFound("No Driver Found With ID"+driverId));
        CarDO selectedCar = carRepository.findById(carId).
                orElseThrow(()->new CarNotFoundException("No Car Found With ID"+carId));

        if(selectedCar.getIsAvaliable()){
            log.info("Booking car "+selectedCar.getId()+"for driver "+currentDriver.getId());
            return CarMapper.makeCarDTO(bookingService.bookCar(currentDriver,selectedCar));
        }
        throw new CarAlreadyInUseException("Car "+selectedCar.getId()+" is already booked");
    }



    @Override
    public CarDTO deSelectCar(Long driverId, Long carId) throws DriverNotFound, CarNotFoundException {
        DriverDO currentDriver = driverRepository.findById(driverId).
                orElseThrow(()->new DriverNotFound("No Driver Found With ID"+driverId));
        CarDO selectedCar = carRepository.findById(carId).
                orElseThrow(()->new CarNotFoundException("No Car Found With ID"+carId));
        log.info(" Suspending car "+selectedCar.getId()+"for driver "+currentDriver.getId());
        return CarMapper.makeCarDTO(bookingService.unBookCar(currentDriver,selectedCar));
    }

    @Override
    public List<CarDO> getAllCars() {
        log.info("Fetching all the cars in the database ");
        return carRepository.findAll();
    }


 //end of Car Service Class
}
