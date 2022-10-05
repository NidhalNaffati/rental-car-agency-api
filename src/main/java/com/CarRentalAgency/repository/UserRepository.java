package com.CarRentalAgency.repository;

import com.CarRentalAgency.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByFirstNameIgnoreCase(String userName);

   /* @Query(value = "select p from Consultation p where " +
            "p.medecinTraitant like %?1% ")*/

    @Query("select u from User u where u.firstName like %?1%")
    List<User> WrongNames(String userName);

   // @Query("select u from User u where u.email =: email")
  // Optional<User> findUserByEmail(@Param("email") String email);
    Optional<User> findUserByEmail(String email);




}
