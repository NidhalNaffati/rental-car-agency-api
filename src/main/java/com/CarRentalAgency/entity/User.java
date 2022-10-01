package com.CarRentalAgency.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(
            name = "id",
            updatable = false,
            nullable = false
    )
    private Long id ;

    //TODO: fix this valid field.
    @Email
    @NotNull
    @NotBlank(message = "Email shouldn't be blank ")
    @Column(unique = true , length = 50)
    private String email ;


    @Size(min = 3,message = "the minimum name have the letters." )
    @Column(length = 16)
    private String firstName ;

    @Column(length = 16)
    private String lastName ;

}
