package com.freenow.dataaccessobject;

import com.freenow.domainobject.CarDO;
import org.springframework.data.repository.CrudRepository;


public interface CarRepository extends CrudRepository<CarDO, Long>
{

}
