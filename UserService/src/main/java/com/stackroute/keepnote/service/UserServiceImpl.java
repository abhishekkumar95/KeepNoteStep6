package com.stackroute.keepnote.service;

import com.stackroute.keepnote.exceptions.UserAlreadyExistsException;
import com.stackroute.keepnote.exceptions.UserNotFoundException;
import com.stackroute.keepnote.model.User;
import com.stackroute.keepnote.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    @Override
//    public User registerUser(User user) throws UserAlreadyExistsException {
//        User savedUser = null;
//        if (userRepository.existsById(user.getUserId())) {
//            throw new UserAlreadyExistsException("User with ID" + user.getUserId() + "already exists");
//        } else {
//            user.setUserAddedDate(new Date());
//
//            savedUser = userRepository.insert(user);
//            if (savedUser == null) {
//                throw new UserAlreadyExistsException("User with ID" + user.getUserId() + "already exists");
//            }
//
//        }
//        return savedUser;
//    }

    @Override
    public User updateUser(String userId, User user) throws UserNotFoundException {

        try {
            User fecthedUser = userRepository.findById(userId).get();
            fecthedUser.setUserName(user.getUserName());
            fecthedUser.setUserMobile(user.getUserMobile());
            fecthedUser.setUserPassword(user.getUserPassword());
            fecthedUser.setUserId(user.getUserId());

            userRepository.save(fecthedUser);
            return fecthedUser;

        } catch (NoSuchElementException exception) {

            throw new UserNotFoundException("User does not exists");
        }


    }

    @Override
    public boolean deleteUser(String userId) throws UserNotFoundException {
        boolean status = false;
        try {
            User fecthedUser = userRepository.findById(userId).get();

            if (fecthedUser != null) {

                userRepository.delete(fecthedUser);
                status = true;
            }

        } catch (NoSuchElementException exception) {
            throw new UserNotFoundException("User does not exists");
        }
        return status;
    }

    @Override
    public User getUserById(String userId) throws UserNotFoundException {
        User fecthedUser = userRepository.findById(userId).get();
        if (fecthedUser == null) {
            throw new UserNotFoundException("User does not exists");
        }
        return fecthedUser;
    }
}
