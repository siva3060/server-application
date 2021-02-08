package com.freenow.dataaccessobject;

import com.freenow.controller.mapper.DriverMapper;
import com.freenow.datatransferobject.DriverDTO;
import com.freenow.domainobject.CarDO;
import com.freenow.domainobject.DriverDO;
import com.freenow.exception.CarNotFoundException;
import com.freenow.exception.DriverNotFound;
import com.freenow.service.driver.DriverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class SearchCar{
@Autowired
EntityManager entityManager;

@Autowired
DriverService driverService;
        public List<DriverDTO> search(String key, String value){

            log.info("Querying Car Database for the parameter"+key);
            List<CarDO> result = entityManager.
                    createNativeQuery("select * from car where "+key+" = '"+value+"'", CarDO.class).getResultList();

            if(result.isEmpty()) throw new DriverNotFound("No Driver with this search criteria "+ key);
            List<DriverDO> driverResult = result.stream()
                    .filter(car -> !car.getIsAvaliable())
                    .map(car -> car.getBookedBy())
                    .map(id -> driverService.findById(id))
                    .collect(Collectors.toList());
            return DriverMapper.makeDriverDTOList(driverResult);
        }
}
