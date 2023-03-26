package com.CarRentalAgency.repository;

import com.CarRentalAgency.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT c FROM Customer c WHERE c.firstName LIKE  ?1  ")
    List<Customer> findByFirstNameIgnoreCase(String userName);

    @Query("select u.firstName from Customer u where u.firstName like %?1%")
    List<String> approximateNames(String userName);

    @Query("select c from Customer c where c.email = :email")
    Optional<Customer> findCustomerByEmail(@Param("email") String email);

    @Query("select u.email from Customer u where u.email like ?1 ")
    List<String> approximateEmails(String userName);

    @Query("select c from Customer c where c.id = :id")
    Customer findCustomerById(@Param("id") long id);
}