package com.freenow.controller.mapper;

import com.freenow.datatransferobject.CarDTO;
import com.freenow.domainobject.CarDO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CarMapper
{

        public static CarDO makeCarDo(CarDTO carDTO){
            log.info("Transforming car DTO -> car DO for car license plate "+carDTO.getLicensePlate());
              return new CarDO(carDTO.getLicensePlate(),
                      carDTO.getSeatCount(),
                      carDTO.getIsConvertible(),
                      carDTO.getRating(),
                      carDTO.getEngineType(),
                      carDTO.getManufacturer(),
                      carDTO.getIsAvailable(),
                      carDTO.getBookedBy());
        }

        public static CarDTO makeCarDTO(CarDO carDO){
            log.info("Transforming car DO -> car DTO for car license plate "+ carDO.getLicensePlate());
            System.out.println("Printing car Do ");
            System.out.println(carDO.toString());
                return new CarDTO(carDO.getId(),
                        carDO.getLicensePlate(),
                        carDO.getSeatCount(),
                        carDO.getIsConvertible(),
                        carDO.getRating(),
                        carDO.getEngineType(),
                        carDO.getManufacturer(),
                        carDO.getIsAvaliable(),
                        carDO.getBookedBy());
        }

}
