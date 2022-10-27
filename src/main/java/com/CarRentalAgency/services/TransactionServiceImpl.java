package com.CarRentalAgency.services;

import com.CarRentalAgency.entity.Car;
import com.CarRentalAgency.entity.Customer;
import com.CarRentalAgency.entity.Dealer;
import com.CarRentalAgency.entity.Transaction;
import com.CarRentalAgency.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    private final CustomerServiceImpl customerService;


    private final CarServiceImpl carService;
    private final DealerServiceImpl dealerService;


    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository,
                                  CustomerServiceImpl customerService,
                                  DealerServiceImpl dealerService,
                                  CarServiceImpl carService) {

        this.transactionRepository = transactionRepository;
        this.customerService = customerService;
        this.carService = carService;
        this.dealerService = dealerService;
    }

    @Override
    public Transaction makeTransaction(long customerID, long dealerID, int carRegistrationNumber) {

        Transaction transaction = null;

        try {

            Customer customer = customerService.findCustomerById(customerID);


            Dealer dealer = dealerService.findDealerById(dealerID);


            Car car = carService.findCarByRegistrationNumber(carRegistrationNumber);

            transaction = Transaction.builder()
                    .date(Date.from(Instant.now()))
                    .customer(customer)
                    .dealer(dealer)
                    .car(car)
                    .build();


            transactionRepository.save(transaction);


        } catch (IllegalArgumentException exception) {
            exception.printStackTrace();
        }


        return transaction;

    }

    @Override
    public List<Transaction> listAllTransaction() {
        return transactionRepository.findAll();
    }
}
