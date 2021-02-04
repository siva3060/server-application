package com.freenow.domainvalue;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
public class Manufacturer {

     @Column(nullable = false)
     private String make;

     @Column(nullable = false)
     private String model;
}
