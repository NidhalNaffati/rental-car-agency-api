package com.CarRentalAgency.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CarOwner {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(
            name = "id",
            updatable = false,
            nullable = false
    )
    private Long id ;


    @Email
    @NotNull
    @NotBlank(message = "Email shouldn't be blank ")
    @Column(unique = true , length = 50)
    private String email ;


    @Size(message = "the minimum name have the letters." )
    @Column(length = 16)
    private String firstName ;

    @Column(length = 16)
    private String lastName ;

    // user car have 0 or 1 or more cars.
    // car should be owned only by one  user
    @OneToMany
    private List<Car> carList ;

}
