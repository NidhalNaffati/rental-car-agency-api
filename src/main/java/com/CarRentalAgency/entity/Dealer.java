package com.CarRentalAgency.entity;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.*;

import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Dealer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    @Email
    @NotBlank
    @Column(unique = true ,  nullable = false)
    @Size(min=10 , max=50 , message = "email should be >10 & <50")
    private String email ;


    @Size(message = "the minimum name have the letters." )
    @Column(length = 16)
    private String firstName ;

    @Column(length = 16)
    private String lastName ;

    // dealer have 0 or 1 or more cars.
    // car should be owned only by one  user
    @OneToMany
    private List<Car> carList ;

}
