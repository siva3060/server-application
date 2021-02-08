
package com.freenow.domainobject;

import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.freenow.domainvalue.EngineType;
import com.freenow.domainvalue.Manufacturer;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import static com.freenow.domainobject.DriverDO.NONE;

@Data
@Entity
@Table(name = "Car")
public class CarDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();

    @Column(nullable = false)
    @NotNull(message = "License Plate  can not be null!")
    private String licensePlate;

    @Column(nullable = false)
    @NotNull(message = "Seat count cannot be null")
    private int seatCount;

    private Boolean isConvertible = false;

    @Column(nullable = false)
    @NotNull(message = "Rating cannot be null")
    private double rating;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EngineType engineType;

    @Column(nullable = false)
    private Manufacturer manufacturer;

    private boolean isAvaliable;
    private Long bookedBy;

    public CarDO() {

    }

    public CarDO( String licensePlate, int seatCount,
                 Boolean isConvertible, double rating,
                  EngineType engineType, Manufacturer manufacturer,Boolean isAvaliable,Long bookedBy) {
        this.dateCreated = dateCreated;
        this.licensePlate = licensePlate;
        this.seatCount = seatCount;
        this.isConvertible = isConvertible;
        this.rating = rating;
        this.engineType = engineType;
        this.manufacturer = manufacturer;
        this.isAvaliable = isAvaliable;
        this.bookedBy = bookedBy;
    }

    public void setIsAvaliable(boolean b) {
        this.isAvaliable = b;
    }

    public boolean getIsAvaliable(){
        return isAvaliable;
    }
}
