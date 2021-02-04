
package com.freenow.domainobject;

import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
    private int seatCount;

    @Column(nullable = false)
    private Boolean isConvertible = false;

    @Column(nullable = false)
    private double rating;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EngineType engineType;

    @Column(nullable = false)
    private Manufacturer manufacturer;

}
