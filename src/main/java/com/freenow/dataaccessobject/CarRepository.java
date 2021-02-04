package com.freenow.dataaccessobject;

import com.freenow.domainobject.CarDO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CarRepository extends CrudRepository<CarDO, Long>
{

}
