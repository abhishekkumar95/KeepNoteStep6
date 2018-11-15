package com.stackroute.keepnote.jwt;

import com.stackroute.keepnote.model.User;
import org.springframework.stereotype.Service;

import java.util.Map;

@FunctionalInterface
public interface SecurityTokenGenrator {

    Map<String, String> generateToken(User user);
}
