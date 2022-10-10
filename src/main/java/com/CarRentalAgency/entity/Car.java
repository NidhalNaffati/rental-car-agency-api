package com.CarRentalAgency.entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Data
@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(
            unique = true,
            nullable = false
    )
    private int registrationNumber;

    @NotNull
    private short seats;

    @NotNull
    private short doors;


    @NotNull
    private int kilometres;


    @Column(
            length = 20,
            nullable = false
    )
    private String model;


    @Enumerated(EnumType.STRING)
    @Column(name = "car_status")
    private status carStatus;

    private enum status {
        Disponible, NotDisponible
    }

     private enum Gear{
        Automatic , Manual
    }

    private enum Fuels{
        Diesel , Petrol
    }


}
