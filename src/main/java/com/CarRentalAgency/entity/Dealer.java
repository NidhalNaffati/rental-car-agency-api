package com.CarRentalAgency.entity;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Dealer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Email(message = "must be a well-formed email address")
    @NotBlank
    @Column(unique = true, nullable = false)
    @Size(min = 10, max = 50, message = "email should be >10 & <50")
    private String email;


    @Size(message = "the minimum name have the letters.")
    @Column(length = 16)
    private String firstName;

    @Column(length = 16)
    private String lastName;

    // dealer have 0 or 1 or more cars.
    // car should be owned only by one  user

    @JsonManagedReference  // use when we could not write JSON: Infinite recursion exception.
    @OneToMany(mappedBy = "dealer")
    private List<Car> carList;

}
