package com.stackroute.keepnote.service;

import com.stackroute.keepnote.exceptions.UserAlreadyExistsException;
import com.stackroute.keepnote.exceptions.UserNotFoundException;
import com.stackroute.keepnote.model.User;

import java.util.Optional;


public interface UserService {

//    User registerUser(User user) throws UserAlreadyExistsException;

    User updateUser(String userId,User user) throws UserNotFoundException;

    boolean deleteUser(String userId) throws UserNotFoundException;


    User getUserById(String userId) throws UserNotFoundException;
}
