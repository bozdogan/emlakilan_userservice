package org.bozdgn.userservice.controller;

import org.bozdgn.userservice.dto.UserInput;
import org.bozdgn.userservice.model.User;
import org.bozdgn.userservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAuthController {
    private final UserService userService;

    public UserAuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserInput userInput) {

        User user = new User(null, userInput.getUsername(), userInput.getPassword(), userInput.getEmail());
        userService.saveEntity(user);

        return ResponseEntity.ok("User '" + user.getUsername() + "' saved");
    }
}
