package com.CarRentalAgency.services;

import com.CarRentalAgency.entity.User;
import com.CarRentalAgency.exception.UserNotFoundException;
import com.CarRentalAgency.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
        if (user.isEmpty()) {
            throw new UserNotFoundException("This user id is not existing");
        }
        return user.get();
    }

    @Override
    public User findByFirstNameIgnoreCase(String userName) throws UserNotFoundException {
        Optional<User> userFound = Optional.ofNullable(userRepository.findByFirstNameIgnoreCase(userName));
        if(userFound.isEmpty()) {

            throw new UserNotFoundException("Oops This userName doesnt exist !! " +
                    "Maybe you mean: " + userRepository.WrongNames(userName).get(1).getFirstName());
        }
        return userFound.get();
    }

    @Override
    public Optional<User> findUserByEmailContaining(String email) {
        return userRepository.findUserByEmailContaining(email);
    }




    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }


    //todo: not finished yet.
    @Override
    public User updateUser(Long id, User newUser) throws UserNotFoundException {
        if(newUser == null || newUser.getId()==null)
            throw new UserNotFoundException("User cant be null") ;

        Optional<User> optionalUser = userRepository.findById(newUser.getId());
        if(optionalUser.isEmpty())
            throw new UserNotFoundException("user with id: "+newUser.getId()+"doesnt exist!!");

        User existingUser = optionalUser.get();
        existingUser.setFirstName(newUser.getFirstName());
        existingUser.setLastName(newUser.getLastName());
        existingUser.setEmail(newUser.getEmail());

        //updating the firstName
       /* if (Objects.nonNull(userDB.getFirstName()) &&
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
        }*/


        return userRepository.save(existingUser);
    }


}
