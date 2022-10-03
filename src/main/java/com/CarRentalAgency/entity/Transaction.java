package com.CarRentalAgency.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    @DateTimeFormat
    private Date date ;


    // TODO: 03/10/2022  in this class i should handle all transaction that can be hapeened.
    // i should learn the relations oneToOne & oneToMany
}
