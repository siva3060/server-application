package com.freenow.controller;

import com.freenow.datatransferobject.CarDTO;
import com.freenow.domainobject.CarDO;
import com.freenow.service.Car.CarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        log.info("Request for health check endpoint ");
        return "KEEP_ALIVE_OK";
    }

    @GetMapping("/getCar/{carId}")
    public ResponseEntity<CarDTO> getCar(@PathVariable(value ="carId" ) Long carId){
        log.info("Requesting car "+carId);
        CarDTO carDTO = carService.getCar(carId);
        return new ResponseEntity<>(carDTO, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CarDTO> addCar(@Valid @RequestBody CarDTO carDto){
        log.info("Request to add  a new car with license plate "+carDto.getLicensePlate());
        CarDTO carDTO = carService.processCarCreateRequest(carDto);
        return new ResponseEntity<>(carDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{carId}")
    public void deleteCar(@PathVariable(value ="carId" ) Long carId){
        log.info("Requesting to delete car "+ carId);
        carService.deleteCar(carId);
    }


    @PutMapping("/update/{carId}")
    public ResponseEntity<CarDTO> updateCar(@PathVariable Long carId,@Valid @RequestBody CarDTO carDto){
        log.info("Request for updating car details with number plate "+carDto.getLicensePlate());
        CarDTO carDTO = carService.updateCar(carId,carDto);
        return new ResponseEntity<>(carDTO, HttpStatus.ACCEPTED);
    }



    @GetMapping("/getAll")
    public List<CarDO> getAllCars(){
       log.info("Requesting all cars ");
       return carService.getAllCars();
    }

}
