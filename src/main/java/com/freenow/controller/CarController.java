package com.freenow.controller;

import com.freenow.datatransferobject.CarDTO;
import com.freenow.service.driver.Car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/cars")
public class CarController {

    @Autowired
    CarService carService;

    @PostMapping("/addcar")
    public void addCar(CarDTO carDto){
        carService.saveCar(carDto);
    }

    @PostMapping("/addcar")
    public void updateCar(CarDTO carDto, Long carId){
        carService.updateCar(carId,carDto);
    }

    @GetMapping("/{carId}")
    public CarDTO getCar(@PathVariable(value ="carId" ) Long carId){
        return CarMapper.makeCarDTO(carService.getCar(carId));
    }

    @DeleteMapping("/{carId}")
    public void  deleteCar(@PathVariable(value ="carId" ) Long carId){
        carService.deleteCar(carId);
    }
}
