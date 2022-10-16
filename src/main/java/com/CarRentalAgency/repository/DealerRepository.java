package com.CarRentalAgency.repository;

import com.CarRentalAgency.entity.Dealer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DealerRepository extends JpaRepository<Dealer,Long> {

    @Query("select d from Dealer d where d.email = :email")
    Optional<Dealer> findByEmail(String email);

    @Query("SELECT d.email FROM Dealer d WHERE d.email LIKE ?1 ")
    List<String> approximateEmail(String dealerEmail);

    List<Dealer> findByFirstNameIgnoreCase(String dealer);

    @Query("SELECT d.firstName FROM Dealer d WHERE d.firstName LIKE :dealerName ")
    List<String > approximateNames(String dealerName);
}
