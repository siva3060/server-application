package com.freenow.datatransferobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.freenow.domainvalue.EngineType;
import com.freenow.domainvalue.Manufacturer;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDTO {

    @JsonIgnore
    private Long id;

    @NotNull(message = "license plate can not be null!")
    @Size(min=5,max=8,message = "licensePlate should be atlest 5  and Max 8 characters ")
    private String licensePlate;

    @NotNull(message = "seat count  can not be null!")
    @Range(min = 2,max=8,message = "seat count should be in the range 2 - 10")
    private int seatCount;

    @NotNull(message = "Car should be convertible ")
    private Boolean isConvertible = false;

    @NotNull
    @DecimalMax(value = "5.0",message = "rating cannot be more that 5.0")
    private double rating;

    @NotNull(message = "Engine Should be ELECTRIC / GAS / HYBRID")
    private EngineType engineType;

    @NotNull(message = "Manufacture data can not be null!")
    private Manufacturer manufacturer;


    @JsonProperty
    public Long getId()
    {
        return id;
    }

    @JsonProperty(value = "isConvertible")
    public void setConvertible(Boolean convertible) {
        isConvertible = convertible;
    }

    public static CarDTOBuilder newBuilder() {
        return new CarDTOBuilder();
    }

    private CarDTO()
    {
    }

    public CarDTO(Long id,String licensePlate, int seatCount,
                  Boolean isConvertible, double rating,
                  EngineType engineType, Manufacturer manufacturer) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.seatCount = seatCount;
        this.isConvertible = isConvertible;
        this.rating = rating;
        this.engineType = engineType;
        this.manufacturer = manufacturer;
    }

    public static class CarDTOBuilder{

        private Long id;
        private String licensePlate;
        private int seatCount;
        private Boolean isConvertible = false;
        private double rating;
        private EngineType engineType;
        private Manufacturer manufacturer;

        public CarDTOBuilder getId() {
            this.id = id;
            return this;
        }

        public CarDTOBuilder getLicensePlate() {
            this.licensePlate = licensePlate;
            return this;
        }

        public CarDTOBuilder getSeatCount() {
            this.seatCount =  seatCount;
            return this;
        }

        public CarDTOBuilder getConvertible() {
            this.isConvertible =  isConvertible;
            return this;
        }

        public CarDTOBuilder getRating() {
            this.rating =  rating;
            return this;
        }

        public CarDTOBuilder getEngineType() {
            this.engineType =  engineType;
            return this;
        }

        public CarDTOBuilder getManufacturer() {
             this.manufacturer = manufacturer;
             return this;
        }
        public CarDTO createCarDTO()
        {
            return new CarDTO(id,licensePlate,seatCount,isConvertible,rating,engineType,manufacturer);
        }
    }
}
