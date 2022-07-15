package org.bozdgn.userservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/login")
    public String login() {
        return "forward:/login.html";
    }

    @GetMapping("/register")
    public String register() {
        return "forward:/register.html";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    @GetMapping("/testuser")
    public ResponseEntity<String> showCurrentUserInfo() {
        return ResponseEntity.ok("Only users can see this!");
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("/testadmin")
    public ResponseEntity<String> adminPage() {
        return ResponseEntity.ok("Only amdins can see this!");
    }
}
