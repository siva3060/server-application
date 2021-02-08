package com.freenow.domainvalue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Manufacturer {

     @Column(nullable = false)
     private String make;

     @Column(nullable = false)
     private String model;
}
