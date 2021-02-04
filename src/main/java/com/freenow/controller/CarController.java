package com.freenow.controller;

import com.freenow.controller.mapper.CarMapper;
import com.freenow.datatransferobject.CarDTO;
import com.freenow.domainobject.CarDO;
import com.freenow.service.driver.Car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/cars")
public class CarController {

    @Autowired
    CarService carService;

    @PostMapping("/addcar")
    public CarDTO addCar(CarDTO carDto){
        CarDO carDO = CarMapper.makeCarDo(carDto);
        return CarMapper.makeCarDTO(carService.saveCar(carDO));
    }

    @PostMapping("/updatecar/{carId}")
    public CarDTO updateCar(@PathVariable Long carId,@Valid @RequestBody CarDTO carDto){
        CarDO carDO = CarMapper.makeCarDo(carDto);
        return CarMapper.makeCarDTO(carService.updateCar(carId,carDO));
    }

    @GetMapping("/{carId}")
    public CarDTO getCar(@PathVariable(value ="carId" ) Long carId){

        return CarMapper.makeCarDTO(carService.getCarById(carId));
    }

    @DeleteMapping("/{carId}")
    public void  deleteCar(@PathVariable(value ="carId" ) Long carId){
        carService.deleteCar(carId);
    }
}
