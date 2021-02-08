package com.freenow.controller;

import com.freenow.controller.mapper.DriverMapper;
import com.freenow.datatransferobject.CarDTO;
import com.freenow.datatransferobject.DriverDTO;
import com.freenow.domainobject.DriverDO;
import com.freenow.domainvalue.OnlineStatus;
import com.freenow.domainvalue.SearchType;
import com.freenow.exception.*;
import com.freenow.service.Car.CarService;
import com.freenow.service.driver.DriverService;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * All operations with a driver will be routed by this controller.
 * <p/>
 */
@Slf4j
@RestController
@RequestMapping("v1/drivers")
public class DriverController
{

    private final DriverService driverService;
    private final CarService carService;


    @Autowired
    public DriverController(final DriverService driverService,final CarService carService){
        this.driverService = driverService;
        this.carService = carService;
    }


    @GetMapping("/{driverId}")
    public DriverDTO getDriver(@PathVariable long driverId) throws EntityNotFoundException
    {
        return DriverMapper.makeDriverDTO(driverService.findById(driverId));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DriverDTO createDriver(@Valid @RequestBody DriverDTO driverDTO) throws ConstraintsViolationException
    {
        DriverDO driverDO = DriverMapper.makeDriverDO(driverDTO);
        return DriverMapper.makeDriverDTO(driverService.create(driverDO));
    }


    @DeleteMapping("/{driverId}")
    public void deleteDriver(@PathVariable long driverId) throws EntityNotFoundException
    {
        driverService.delete(driverId);
    }


    @PutMapping("/{driverId}")
    public void updateLocation(
        @PathVariable long driverId, @RequestParam double longitude, @RequestParam double latitude)
        throws EntityNotFoundException
    {
        driverService.updateLocation(driverId, longitude, latitude);
    }

    @GetMapping("/searchBy/{searchBy}/{searchValue}")
    public List<DriverDTO>  searchDriverBy(@NotNull  @PathVariable("searchBy") SearchType searchBy,
                                @NotNull @PathVariable("searchValue") String searchValue){
        log.info("Searching for driver with "+searchBy+" with search value "+searchValue);
            return driverService.searchByCriteria(searchBy,searchValue);
    }

    @GetMapping
    public List<DriverDTO> findDrivers(@RequestParam OnlineStatus onlineStatus)
    {
        return DriverMapper.makeDriverDTOList(driverService.findByOnlineStatus(onlineStatus));
    }

    @GetMapping("/select/{carId}/{driverId}")
    public CarDTO selectCarById(@PathVariable Long driverId,Long carId) throws CarAlreadyInUseException, CarNotFoundException, DriverNotFound {
        log.info(" driver "+driverId+" requested to book car "+carId);
        return carService.selectCar(driverId,carId);
    }
    @GetMapping("/deselect/{carId}/{driverId}")
    public CarDTO deSelectCarById(@PathVariable Long driverId,Long carId) throws  CarNotFoundException, DriverNotFound {
        log.info(" driver "+driverId+" requested to unbook car "+carId);
        return carService.deSelectCar(driverId,carId);
    }
}
