package com.freenow.dataaccessobject;

import com.freenow.domainobject.DriverDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SearchDriver {

    @Autowired
    EntityManager entityManager;

    public List<String> search(String key, String value){

        log.info("inside search driver bean ");
        System.out.println(key+" -> "+value);
        List<DriverDO> result =  entityManager.
                createNativeQuery("select * from driver where "+key+" = '"+value+"'",DriverDO.class).getResultList();
        return result.stream().map( driver ->  driver.getId().toString()).collect(Collectors.toList());
    }


}
