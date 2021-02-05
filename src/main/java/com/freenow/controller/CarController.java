package com.freenow.controller;

import com.freenow.controller.mapper.CarMapper;
import com.freenow.datatransferobject.CarDTO;
import com.freenow.domainobject.CarDO;
import com.freenow.exception.CarNotFoundException;
import com.freenow.exception.EntityNotFoundException;
import com.freenow.service.Car.CarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("v1/cars")
public class CarController {

    @Autowired
    CarService carService;

    @GetMapping("/healthcheck")
    public String healthCheck(){
        log.info("Request for endpoint /healthcheck");
        return "KEEP_ALIVE_OK";
    }

    //curl http://localhost:8080/v1/cars/create -d '{ "id" : 2, "licensePlate" : "AU34AD2", "seatCount" : 4,"isConvertiable" : true, "rating": 4.5, "manufacturer" : { "make" : "KIA", "model" : "Optioma"} '
    @PostMapping("/addcar")
    public ResponseEntity<CarDTO> addCar(@Valid @RequestBody CarDTO carDto)  {
        log.info("Request for  a new car with license plate "+carDto.getLicensePlate());
        CarDO carDO = CarMapper.makeCarDo(carDto);
        return new ResponseEntity<>(CarMapper.makeCarDTO(carService.saveCar(carDO)), HttpStatus.CREATED);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public void handleConstrainVoildationException(){

    }

    @PostMapping("/updatecar/{carId}")
    public CarDTO updateCar(@PathVariable Long carId,@Valid @RequestBody CarDTO carDto){
        log.info("Request for updating car details with number plate "+carDto.getLicensePlate());
        CarDO carDO = CarMapper.makeCarDo(carDto);
        return CarMapper.makeCarDTO(carService.updateCar(carId,carDO));
    }

    @GetMapping("/getCar/{carId}")
    public CarDTO getCar(@PathVariable(value ="carId" ) Long carId) throws CarNotFoundException {
        log.info("Requesting car "+carId);
        return CarMapper.makeCarDTO(carService.getCarById(carId));
    }

    @DeleteMapping("/delete/{carId}")
    public void  deleteCar(@PathVariable(value ="carId" ) Long carId){
        log.info("Requesting to delete car "+ carId);
        carService.deleteCar(carId);
    }

    @GetMapping
    public List<CarDO> getAllCars(){
       log.info("Requesting all cars ");
       return carService.getAllCars();
    }
}
