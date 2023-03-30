package com.CarRentalAgency.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Entity
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Car {

    @Id
    @SequenceGenerator(
            name = "car_seq",
            sequenceName = "car_seq",
            allocationSize = 1,
            initialValue = 18 // start from 18 because we have 17 cars in the database.
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "car_seq")
    private Long id;


    @Length(min = 5, max = 50)
    private String name;

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


    public enum Model {
        SUV, SPORTS_CAR, COUPE, MINIVAN, CONVERTIBLE, HATCHBACK, SEDAN, PICKUP_TRUCK
    }

    public enum Gear {
        Automatic, Manual
    }

    public enum Fuel {
        Diesel, Petrol
    }
}
