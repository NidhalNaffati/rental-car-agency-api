package com.CarRentalAgency.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

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


    // TODO: 19/10/2022 i should handle the exception when the stupid use put a duplicated  registrationNumber
    /*
     * java.sql.SQLIntegrityConstraintViolationException:
     *  Duplicate entry '116' for key 'car.UK_41gx8ie1hc8w4eylj60io4pvy'
     */
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


    // TODO: 19/10/2022 i should handel the exception when the user put a wrong type of gear
    /* Resolved [org.springframework.http.converter.HttpMessageNotReadableException: JSON parse error:
     Cannot deserialize value of type `com.CarRentalAgency.entity.Car$Gear` from String "seseg":
     not one of the values accepted for Enum class: [Automatic, Manual]; */
    private enum Gear {
        Automatic, Manual
    }

    private enum Fuel {
        Diesel, Petrol
    }
}
