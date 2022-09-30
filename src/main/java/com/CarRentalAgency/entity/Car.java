package com.CarRentalAgency.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Car {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private long id ;

    private int registrationNumber ;

    private short seats ;

    private short doors ;

    private int kilometres ;

    @Column(length = 20)
    private String model ;

   /* private enum status{
        Disponible , NotDisponible
    }*/


}
