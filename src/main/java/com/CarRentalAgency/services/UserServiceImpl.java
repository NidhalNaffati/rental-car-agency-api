package com.CarRentalAgency.services;

import com.CarRentalAgency.entity.User;
import com.CarRentalAgency.exception.NoSuchElementException;
import com.CarRentalAgency.exception.AlreadyExistsException;
import com.CarRentalAgency.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    //private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        Optional<User> userDB = userRepository.findUserByEmail(user.getEmail());
        if (userDB.isPresent())
            throw new AlreadyExistsException("this email: " + user.getEmail() + " already exists.");
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }


    @Override
    public List<User> findByFirstNameIgnoreCase(String userName) throws NoSuchElementException {
        List<User> userListFound = userRepository.findByFirstNameIgnoreCase(userName);
        if (userListFound.isEmpty()) {
            //   LOGGER.error("ERROR printing the user name.");
            throw new NoSuchElementException("Oops This userName doesnt exist !! " +
                    "Maybe you mean: " /*+ userRepository.WrongNames(userName).get(1).getFirstName()*/);
        }
        return userListFound;
    }

    @Override
    public Optional<User> findById(Long id) throws NoSuchElementException {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty())
            throw new NoSuchElementException("there is no user with this id:" + id);
        return userOptional;
    }

    @Override
    public Optional<User> findUserByEmail(String email) throws NoSuchElementException {
        Optional<User> userOptional = userRepository.findUserByEmail(email);
        if (userOptional.isEmpty())
            throw new NoSuchElementException("there is no user with this email:" + email);
        return userOptional;
    }


    @Override
    public void deleteById(Long id)  {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty())
            throw new NoSuchElementException("cannot delete unexisting user, this id: " + id
                    + " doesnt exist or he is already deleted.");
        userRepository.deleteById(id);

    }


    // TODO: 04/10/2022 i  should handel the null fields when i save or update.
    @Override
    public User updateUser(Long id, User newUser) throws NoSuchElementException, AlreadyExistsException {
        
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty())
            throw new NoSuchElementException("user id: " + id + " doesnt exist!!");

        User existingUser = optionalUser.get();

        if (userRepository.findUserByEmail(newUser.getEmail()).isPresent())
            throw new AlreadyExistsException("this email: " + newUser.getEmail() + " already exists !!");

        existingUser.setFirstName(newUser.getFirstName());
        existingUser.setLastName(newUser.getLastName());
        existingUser.setEmail(newUser.getEmail());

       /* if (Objects.nonNull(userDB.getFirstName()) &&
                !"".equalsIgnoreCase(userDB.getFirstName())) {
            userDB.setFirstName(newUser.getFirstName());
        }
       */
        return userRepository.save(existingUser);
    }


}
