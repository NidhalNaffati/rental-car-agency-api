package com.CarRentalAgency.entity;

public record TransactionRequest(
        long customerId,
        long dealerId,
        long carId
) {
}
