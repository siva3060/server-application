package com.freenow.dataaccessobject;

import com.freenow.domainobject.CarDO;
import com.freenow.domainobject.DriverDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class SearchCar{

@Autowired
EntityManager entityManager;

        public List<String> search(String key, String value){

            log.info("Querying Car Database for the parameter"+key);
            List<CarDO> result = entityManager.
                    createNativeQuery("select * from car where "+key+" = '"+value+"'", CarDO.class).getResultList();
             return result.stream()
                    .filter(car -> car.getIsAvaliable())
                    .map(car -> car.getBookedBy().toString())
                    .collect(Collectors.toList());
        }
}
