package com.freenow.dataaccessobject;

import com.freenow.domainobject.CarDO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CarRepository extends CrudRepository<CarDO, Long> {
    @Override
    List<CarDO> findAll();
}
