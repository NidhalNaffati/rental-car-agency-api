package com.CarRentalAgency.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Length(min = 5 ,max = 20)
    private String carName ;

    @Column(
            unique = true,
            nullable = false
    )
    private int registrationNumber;

    @NotNull
    private short seats;

    @NotNull
    private int kilometres;

    @NotNull
    private short doors;

    @Enumerated(EnumType.STRING)
    @Column(name = "model")
    private Model model;

    @Enumerated(EnumType.STRING)
    private Gear gear;

    @Enumerated(EnumType.STRING)
    @Column(name = "fuel")
    private Fuel fuel;

    @ManyToOne
    @JsonBackReference // use when we could not write JSON: Infinite recursion exception.
    @JoinColumn(name = "dealer_id", nullable = false)
    private Dealer dealer;


    private enum Model {
        SUV, SPORTS_CAR, COUPE, MINIVAN, CONVERTIBLE, HATCHBACK, SEDAN, PICKUP_TRUCK
    }

    private enum Gear {
        Automatic, Manual
    }

    private enum Fuel {
        Diesel, Petrol
    }
}
