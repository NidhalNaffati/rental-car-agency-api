package com.CarRentalAgency.services;

import com.CarRentalAgency.entity.*;
import com.CarRentalAgency.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceImplTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private CustomerServiceImpl customerService;

    @Mock
    private CarServiceImpl carService;

    @Mock
    private DealerServiceImpl dealerService;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Test
    public void testSaveTransaction() {

        // given
        TransactionRequest request = new TransactionRequest(
                1L,
                2L,
                3L
        );

        Customer customer = new Customer();
        customer.setId(1L);

        Car car = new Car();
        car.setId(2L);

        Dealer dealer = new Dealer();
        dealer.setId(3L);

        when(customerService.findCustomerById(request.customerId()))
                .thenReturn(customer);

        when(carService.findCarById(request.carId()))
                .thenReturn(car);

        when(dealerService.findDealerById(request.dealerId()))
                .thenReturn(dealer);

        // when
        Transaction savedTransaction = transactionService.saveTransaction(request);

        // then
        assertNotNull(savedTransaction);
        assertEquals(customer, savedTransaction.getCustomer());
        assertEquals(car, savedTransaction.getCar());
        assertEquals(dealer, savedTransaction.getDealer());

        verify(transactionRepository).save(savedTransaction);
    }


    @Test
    public void testListAllTransaction() {

        // given
        List<Transaction> expectedTransactions = Arrays.asList(
                new Transaction(),
                new Transaction(),
                new Transaction()
        );

        when(transactionRepository.findAll()).thenReturn(expectedTransactions);

        // when
        List<Transaction> actualTransactions = transactionService.listAllTransaction();

        // then
        assertNotNull(actualTransactions);
        assertEquals(expectedTransactions.size(), actualTransactions.size());
        assertEquals(expectedTransactions.get(0), actualTransactions.get(0));
        assertEquals(expectedTransactions.get(1), actualTransactions.get(1));
        assertEquals(expectedTransactions.get(2), actualTransactions.get(2));

        verify(transactionRepository).findAll();
    }
}
