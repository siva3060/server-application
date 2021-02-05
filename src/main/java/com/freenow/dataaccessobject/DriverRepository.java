package com.freenow.dataaccessobject;

import com.freenow.domainobject.CarDO;
import com.freenow.domainobject.DriverDO;
import com.freenow.domainvalue.OnlineStatus;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Database Access Object for driver table.
 * <p/>
 */
public interface DriverRepository extends CrudRepository<DriverDO, Long>
{

        List<DriverDO> findByOnlineStatus(OnlineStatus onlineStatus);

        @Query("Select * from driver where keyWord = value")
        List<DriverDO> findBy(@Param("keyWord") String value);
}
