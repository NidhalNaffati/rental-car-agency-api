package com.CarRentalAgency.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    /*@Column(
            name = "id",
            updatable = false,
            nullable = false
    )*/
    private Long id ;

    //TODO: fix this valid field.
    @Email
    @NotNull(message = "email shouldnt be null ")
    @Column(unique = true , nullable = false)
    @Size(min=15 , max=50 , message = "email should be >10 & <50")
    private String email ;


    @Size(min=3 , max=16 , message = "first name should be >10 & <50")
    @NotBlank
    private String firstName ;

    @Size(min=3 , max=16 , message = "first name should be >10 & <50")
    @NotBlank
    private String lastName ;

}
