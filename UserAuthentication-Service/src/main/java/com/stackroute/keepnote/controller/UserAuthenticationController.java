package com.stackroute.keepnote.controller;


import com.stackroute.keepnote.exception.UserAlreadyExistsException;
import com.stackroute.keepnote.exception.UserIdAndPasswordMismatchException;
import com.stackroute.keepnote.exception.UserNotFoundException;
import com.stackroute.keepnote.exception.UserNullException;
import com.stackroute.keepnote.jwt.SecurityTokenGenrator;
import com.stackroute.keepnote.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.stackroute.keepnote.service.UserAuthenticationService;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class UserAuthenticationController {

    @Autowired
    private UserAuthenticationService authicationService;


    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody User user) {

        ResponseEntity responseEntity = null;

        try {

            authicationService.saveUser(user);
            responseEntity = new ResponseEntity<String>("User registered successfully", HttpStatus.CREATED);


        } catch (UserAlreadyExistsException exception) {
            responseEntity = new ResponseEntity<String>("{ \"message\": \"" + exception.getMessage() + "\"}", HttpStatus.CONFLICT);
        }


        return responseEntity;

    }


    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody User loginDetails) {


        try {


            String userId = loginDetails.getUserId();
            String password = loginDetails.getUserPassword();


            if (userId == null || password == null) {
                throw new UserNullException("Userid and Password cannot be empty");
            }

            User user = authicationService.findByUserIdAndPassword(userId, password);

            if (user == null) {
                throw new UserNotFoundException("User with given Id does not exists");
            }


            String fetchedPassword = user.getUserPassword();
            if (!password.equals(fetchedPassword)) {
                throw new UserIdAndPasswordMismatchException("Invalid login credential, Please check username and password ");
            }




            // generating token
            SecurityTokenGenrator securityTokenGenrator = (User userDetails) -> {

                String jwtToken = "";
                jwtToken = Jwts.builder().setId(user.getUserId()).setSubject(user.getUserRole()).setIssuedAt(new Date())
                        .signWith(SignatureAlgorithm.HS256, "secretkey").compact();

                Map<String, String> map1 = new HashMap<>();
                map1.put("token", jwtToken);
                map1.put("message", "User successfully logged in");
                return map1;
            };




            Map<String, String> map = securityTokenGenrator.generateToken(user);

            return new ResponseEntity<>(map, HttpStatus.OK);


        } catch (UserNullException | UserNotFoundException | UserIdAndPasswordMismatchException exception) {

            return new ResponseEntity<>("{ \"message\": \"" + exception.getMessage() + "\"}", HttpStatus.UNAUTHORIZED);
        }


    }


}
