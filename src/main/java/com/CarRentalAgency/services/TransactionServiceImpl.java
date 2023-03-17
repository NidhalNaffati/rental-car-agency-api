package com.CarRentalAgency.services;

import com.CarRentalAgency.entity.*;
import com.CarRentalAgency.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    private final CustomerServiceImpl customerService;

    private final CarServiceImpl carService;

    private final DealerServiceImpl dealerService;

    @Override
    public Transaction saveTransaction(TransactionRequest request) {

        Transaction transaction = null;

        try {

            Customer customer = customerService.findCustomerById(request.customerId());
            Car car = carService.findCarById(request.carId());
            Dealer dealer = dealerService.findDealerById(request.dealerId());

            Date currentDate = new Date();

            transaction = Transaction.builder()
                    .date(currentDate)
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
