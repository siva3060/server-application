package com.freenow.dataaccessobject;

import com.freenow.controller.mapper.DriverMapper;
import com.freenow.datatransferobject.DriverDTO;
import com.freenow.domainobject.DriverDO;
import com.freenow.exception.DriverNotFound;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class SearchDriver {

    @Autowired
    EntityManager entityManager;

    public List<DriverDTO> search(String key, String value){

        log.info("Querying database for "+key);
        List<DriverDO> result =  entityManager.
                createNativeQuery("select * from driver where "+key+" = '"+value+"'",DriverDO.class).getResultList();
        if(result.isEmpty()) throw new DriverNotFound("No Driver with this search criteria "+ key);
        return DriverMapper.makeDriverDTOList(result);
    }
}
