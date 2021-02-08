package com.freenow.service.driver;

import com.freenow.dataaccessobject.DriverRepository;
import com.freenow.dataaccessobject.SearchCar;
import com.freenow.dataaccessobject.SearchDriver;
import com.freenow.datatransferobject.DriverDTO;
import com.freenow.domainobject.DriverDO;
import com.freenow.domainvalue.GeoCoordinate;
import com.freenow.domainvalue.OnlineStatus;
import com.freenow.domainvalue.SearchType;
import com.freenow.exception.ConstraintsViolationException;
import com.freenow.exception.DriverNotFound;
import com.freenow.exception.EntityNotFoundException;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service to encapsulate the link between DAO and controller and to have business logic for some driver specific things.
 * <p/>
 */
@Slf4j
@Service
public class DefaultDriverService implements DriverService
{

    private static final Logger LOG = LoggerFactory.getLogger(DefaultDriverService.class);

    private final DriverRepository driverRepository;
    SearchCar searchCar;

    @Autowired
    SearchDriver searchDriver;


    public DefaultDriverService(final DriverRepository driverRepository)
    {
        this.driverRepository = driverRepository;
    }

    @Autowired
    public void setSearchCar(SearchCar searchCar) {
        this.searchCar = searchCar;
    }

    @Autowired
    public void setSearchDriver(SearchDriver searchDriver) {
        this.searchDriver = searchDriver;
    }

    /**
     * Selects a driver by id.
     *
     * @param driverId
     * @return found driver
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    public DriverDO findById(Long driverId) throws EntityNotFoundException
    {
        return findDriverChecked(driverId);
    }


    /**
     * Creates a new driver.
     *
     * @param driverDO
     * @return
     * @throws ConstraintsViolationException if a driver already exists with the given username, ... .
     */
    @Override
    public DriverDO create(DriverDO driverDO) throws ConstraintsViolationException
    {
        DriverDO driver;
        try
        {
            driver = driverRepository.save(driverDO);
        }
        catch (DataIntegrityViolationException e)
        {
            LOG.warn("ConstraintsViolationException while creating a driver: {}", driverDO, e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return driver;
    }


    /**
     * Deletes an existing driver by id.
     *
     * @param driverId
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    @Transactional
    public void delete(Long driverId) throws EntityNotFoundException
    {
        DriverDO driverDO = findDriverChecked(driverId);
        driverDO.setDeleted(true);
    }


    /**
     * Update the location for a driver.
     *
     * @param driverId
     * @param longitude
     * @param latitude
     * @throws EntityNotFoundException
     */
    @Override
    @Transactional
    public void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException
    {
        DriverDO driverDO = findDriverChecked(driverId);
        driverDO.setCoordinate(new GeoCoordinate(latitude, longitude));
    }


    /**
     * Find all drivers by online state.
     *
     * @param onlineStatus
     */
    @Override
    public List<DriverDO> findByOnlineStatus(OnlineStatus onlineStatus)
    {
        return driverRepository.findByOnlineStatus(onlineStatus);
    }

    @Override
    public List<DriverDTO>  searchByCriteria(SearchType searchParameter, String searchValue) {
        if(searchParameter.equals(SearchType.USERNAME)){
            log.info("Searching based on driver Parameter");
            return searchDriver.search(searchParameter.toString(), searchValue);
        }
        log.info("Searching based on car Parameter");
        return  searchCar.search(searchParameter.toString(), searchValue);

    }



    private DriverDO findDriverChecked(Long driverId) throws DriverNotFound
    {
        return driverRepository.findById(driverId)
            .orElseThrow(() -> new DriverNotFound("Could not find entity with id: " + driverId));
    }



}
