package org.bozdgn.userservice.controller;

import org.bozdgn.userservice.dto.UserInput;
import org.bozdgn.userservice.security.JWTTool;
import org.bozdgn.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserController {

    private final JWTTool jwtTool;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @Autowired
    public UserController(JWTTool jwtTool, AuthenticationManager authenticationManager, UserService userService) {
        this.jwtTool = jwtTool;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @PostMapping("/register")
    public void register(@RequestBody UserInput userRequest) {
        userService.save(userRequest);
    }

    @PostMapping("/login")
    public String generateToken(@RequestBody UserInput userInput) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userInput.getUsername(), userInput.getPassword()));
        } catch (BadCredentialsException exception) {
            throw new Exception("Incorrect username or password", exception);
        }
        UserDetails user = userService.loadUserByUsername(userInput.getUsername());
        return jwtTool.createToken(user);
    }
}
