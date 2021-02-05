package com.freenow.controller;

import com.freenow.controller.mapper.CarMapper;
import com.freenow.datatransferobject.CarDTO;
import com.freenow.domainobject.CarDO;
import com.freenow.service.Car.CarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
        log.info("Request for endpoint /healthcheck");
        return "KEEP_ALIVE_OK";
    }

    @PostMapping("/addcar")
    public CarDTO addCar(CarDTO carDto){
        log.info("Request for  a new car with number plate "+carDto.getLicensePlate());
        CarDO carDO = CarMapper.makeCarDo(carDto);
        return CarMapper.makeCarDTO(carService.saveCar(carDO));
    }

    @PostMapping("/updatecar/{carId}")
    public CarDTO updateCar(@PathVariable Long carId,@Valid @RequestBody CarDTO carDto){
        log.info("Request for updating car details with number plate "+carDto.getLicensePlate());
        CarDO carDO = CarMapper.makeCarDo(carDto);
        return CarMapper.makeCarDTO(carService.updateCar(carId,carDO));
    }

    @GetMapping("/getCar/{carId}")
    public CarDTO getCar(@PathVariable(value ="carId" ) Long carId){
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
