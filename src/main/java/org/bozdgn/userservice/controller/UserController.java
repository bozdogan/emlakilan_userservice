package org.bozdgn.userservice.controller;

import org.bozdgn.userservice.dto.UserInput;
import org.bozdgn.userservice.dto.UserOutput;
import org.bozdgn.userservice.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    @GetMapping
    public List<UserOutput> getAll() {
        return userService.getAll();
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    @GetMapping("/{username}")
    public UserOutput getOne(@PathVariable String username) {
        return userService.getByUsername(username);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PostMapping("/")
    public UserOutput save(@RequestBody UserInput userInput) {
        return userService.save(userInput);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{username}")
    public void delete(@PathVariable String username) {
        userService.deleteUserByUsername(username);
    }

}
