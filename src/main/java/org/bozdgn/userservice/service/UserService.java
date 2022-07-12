package org.bozdgn.userservice.service;

import org.bozdgn.userservice.dto.UserInput;
import org.bozdgn.userservice.model.User;
import org.bozdgn.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUser(Long id) {
        return repository.findById(id).orElse(null);
    }

    public User getByUsername(String username) {
        return repository.findByUsername(username).orElse(null);
    }

    public User save(UserInput userInput) {
        User user = new User(userInput.getUsername(), userInput.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getByUsername(username);
    }
}
