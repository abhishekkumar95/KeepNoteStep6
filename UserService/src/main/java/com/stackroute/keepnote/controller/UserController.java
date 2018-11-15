package com.stackroute.keepnote.controller;

import com.stackroute.keepnote.exceptions.UserAlreadyExistsException;
import com.stackroute.keepnote.exceptions.UserNotFoundException;
import com.stackroute.keepnote.model.User;
import com.stackroute.keepnote.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {


    private UserService userService;

    //Autowiring UserService
    @Autowired
    public UserController(UserService userService) {

        this.userService = userService;
    }
/*

    // Register User
    @PostMapping()
    public ResponseEntity<?> registerUser(@RequestBody User user) {

        ResponseEntity responseEntity = null;

        try {
            userService.registerUser(user);
            responseEntity = new ResponseEntity(HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }


        return responseEntity;
    }
*/


    //Update User Record
    @PutMapping("/{userId}")
    public ResponseEntity updateUser(@PathVariable() String userId, @RequestBody User user) {


        ResponseEntity responseEntity = null;

        try {
            User fetchedUser = userService.updateUser(userId, user);
            responseEntity = new ResponseEntity(fetchedUser, HttpStatus.OK);
        } catch (UserNotFoundException exception) {

            responseEntity = new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);

        }

        return responseEntity;
    }

    // Delete User Record
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable() String id) {
        ResponseEntity responseEntity = null;

        try {
            userService.deleteUser(id);
            responseEntity = new ResponseEntity<>("Successfully Deleted User with id: " + id, HttpStatus.OK);

        } catch (UserNotFoundException exception) {
            responseEntity = new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);

        }

        return responseEntity;

    }

    // Get User Record
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable() String id) {
        ResponseEntity responseEntity = null;
        try {
            User fetchedUser = userService.getUserById(id);
            responseEntity = new ResponseEntity(fetchedUser, HttpStatus.OK);


        } catch (UserNotFoundException e) {
            responseEntity = new ResponseEntity<>("{ \"message\": \"" + e.getMessage() + "\"}", HttpStatus.NOT_FOUND);
        }


        return responseEntity;
    }


}
