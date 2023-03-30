package com.CarRentalAgency.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @SequenceGenerator(
            name = "transaction_seq",
            sequenceName = "transaction_seq",
            allocationSize = 1,
            initialValue = 50 // start from 50 because we have some transactions in the database.
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_seq")
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date date;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties("carList")
    // used to ignore the carList --> the Json Object don't contain that long list of cars
    @JoinColumn(name = "dealer_id", referencedColumnName = "id")
    private Dealer dealer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car car;

}
