package com.CarRentalAgency.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @SequenceGenerator(
            name = "customer_seq",
            sequenceName = "customer_seq",
            allocationSize = 1,
            initialValue = 4 // start from 4 because we have 3 customers in the database.
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
    private Long id;

    @Email
    @NotNull(message = "email shouldnt be null ")
    @Column(unique = true, nullable = false)
    @Size(min = 15, max = 50, message = "email should be >10 & <50")
    private String email;


    @Size(min = 3, max = 16, message = "first name should be >10 & <50")
    @NotBlank
    private String firstName;

    @Size(min = 3, max = 16, message = "first name should be >10 & <50")
    @NotBlank
    private String lastName;

}
