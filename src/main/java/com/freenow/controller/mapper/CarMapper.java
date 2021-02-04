package com.freenow.controller.mapper;

        import com.freenow.datatransferobject.CarDTO;
        import com.freenow.datatransferobject.DriverDTO;
        import com.freenow.domainobject.CarDO;
        import com.freenow.domainobject.DriverDO;
        import com.freenow.domainvalue.GeoCoordinate;
        import java.util.Collection;
        import java.util.List;
        import java.util.stream.Collectors;

public class CarMapper
{

        public static CarDO makeCarDo(CarDTO carDTO){
              return new CarDO(carDTO.getLicensePlate(),
                      carDTO.getSeatCount(),
                      carDTO.getIsConvertible(),
                      carDTO.getRating(),
                      carDTO.getEngineType(),
                      carDTO.getManufacturer());
        }

        public static CarDTO makeCarDTO(CarDO carDO){
                return new CarDTO(carDO.getId(),
                        carDO.getLicensePlate(),
                        carDO.getSeatCount(),
                        carDO.getIsConvertible(),
                        carDO.getRating(),
                        carDO.getEngineType(),
                        carDO.getManufacturer());
        }

}
