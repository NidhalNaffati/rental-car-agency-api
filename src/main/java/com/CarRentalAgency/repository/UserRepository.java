package com.CarRentalAgency.repository;

import com.CarRentalAgency.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByFirstNameIgnoreCase(String userName);

   /* @Query(value = "select p from Consultation p where " +
            "p.medecinTraitant like %?1% ")*/

    @Query("select u from User u where u.firstName like %?1%")
    List<User> WrongNames(String userName);

    Optional<User> findUserByEmailContaining(String email);
}
