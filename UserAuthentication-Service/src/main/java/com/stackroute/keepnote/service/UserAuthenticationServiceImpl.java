package com.stackroute.keepnote.service;

import com.stackroute.keepnote.exception.UserAlreadyExistsException;
import com.stackroute.keepnote.exception.UserNotFoundException;
import com.stackroute.keepnote.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.keepnote.repository.UserAutheticationRepository;

import java.util.Optional;

@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

    @Autowired
    private UserAutheticationRepository authenticationRepository;

    @Override
    public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException {

        User user = authenticationRepository.findByUserIdAndUserPassword(userId, password);
        if (user == null) {
            throw new UserNotFoundException("UserId and Password mismatch");
        }
        return user;
    }

    @Override
    public boolean saveUser(User user) throws UserAlreadyExistsException {
        Optional<User> u1 = authenticationRepository.findById(user.getUserId());
        if (u1.isPresent()) {
            throw new UserAlreadyExistsException("User with Id already exists");
        }
        authenticationRepository.save(user);
        return true;
    }
}
