package com.freenow.service.driver;

import com.freenow.datatransferobject.DriverDTO;
import com.freenow.domainobject.DriverDO;
import com.freenow.domainvalue.OnlineStatus;
import com.freenow.domainvalue.SearchType;
import com.freenow.exception.ConstraintsViolationException;
import com.freenow.exception.EntityNotFoundException;
import java.util.List;

public interface DriverService
{

    DriverDO findById(Long driverId) throws EntityNotFoundException;

    DriverDO create(DriverDO driverDO) throws ConstraintsViolationException;

    void delete(Long driverId) throws EntityNotFoundException;

    void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException;

    List<DriverDO> findByOnlineStatus(OnlineStatus onlineStatus);

    List<DriverDTO>  searchByCriteria(SearchType keyWord, String value);

    //List<DriverDO> searchBy(String keyWord,String value);
}
