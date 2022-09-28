package com.CarRentalAgency.services;

import com.CarRentalAgency.entity.User;
import com.CarRentalAgency.exception.UserNotFoundException;
import com.CarRentalAgency.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;


    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) throws UserNotFoundException {

        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new UserNotFoundException("This user id is not existing");
        }
        return user.get();
    }

    @Override
    public List<User> findByFirstNameIgnoreCase(String userName) {
        return userRepository.findByFirstNameIgnoreCase(userName);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(Long id, User newUser) {
        User userDB = userRepository.findById(id).get();

        //updating the firstName
        if (Objects.nonNull(userDB.getFirstName()) &&
                !"".equalsIgnoreCase(userDB.getFirstName())) {
            userDB.setFirstName(newUser.getFirstName());
        }

        //updating the lastName
        if (Objects.nonNull(userDB.getLastName()) &&
                !"".equalsIgnoreCase(userDB.getLastName())) {
            userDB.setLastName(newUser.getLastName());
        }

        //updating the email
        if (Objects.nonNull(userDB.getEmail()) &&
                !"".equalsIgnoreCase(userDB.getEmail())) {
            userDB.setEmail(newUser.getEmail());
        }


        return userRepository.save(userDB);
    }


}
