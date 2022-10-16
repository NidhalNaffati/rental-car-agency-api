package com.CarRentalAgency.repository;

import com.CarRentalAgency.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByFirstNameIgnoreCase(String userName);

   /* @Query(value = "select p from Consultation p where " +
            "p.medecinTraitant like %?1% ")*/

 /*   @Query("select u from Customer u where u.firstName like %?1%")
    List<Customer> WrongNames(String userName);*/

   // @Query("select u from Customer u where u.email =: email")
  // Optional<Customer> findUserByEmail(@Param("email") String email);
    Optional<Customer> findCustomerByEmail(String email);




}
