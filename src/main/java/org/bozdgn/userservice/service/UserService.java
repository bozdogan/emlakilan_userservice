package org.bozdgn.userservice.service;

import org.bozdgn.userservice.dto.UserInput;
import org.bozdgn.userservice.dto.UserOutput;
import org.bozdgn.userservice.model.User;
import org.bozdgn.userservice.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    private final Logger log = LoggerFactory.getLogger(getClass().getCanonicalName());
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found by username '" + username + "'");
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        if (Boolean.TRUE.equals(user.getIsAdmin())) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    public UserOutput save(UserInput userInput) {
        User user = saveEntity(new User(
                null,
                userInput.getUsername(),
                userInput.getPassword(),
                userInput.getEmail(),
                userInput.getIsAdmin(),
                userInput.getFirstName(),
                userInput.getLastName(),
                userInput.getTelephone()));

        return new UserOutput(
                user.getUsername(),
                user.getEmail(),
                user.getIsAdmin(),
                user.getFirstName(),
                user.getLastName(),
                user.getTelephone());
    }

    public User saveEntity(User user) {
        log.info("Saving user '{}'", user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    public List<UserOutput> getAll() {
        return repository.findAll().stream().map(
                it -> new UserOutput(
                        it.getUsername(),
                        it.getEmail(),
                        it.getIsAdmin(),
                        it.getFirstName(),
                        it.getLastName(),
                        it.getTelephone())
        ).collect(Collectors.toList());
    }

    public UserOutput getByUsername(String username) {
        User user = repository.findByUsername(username);
        return new UserOutput(
                user.getUsername(),
                user.getEmail(),
                user.getIsAdmin(),
                user.getFirstName(),
                user.getLastName(),
                user.getTelephone());
    }

    public void deleteUserByUsername(String username) {
        User user = repository.findByUsername(username);
        if (user != null) {
            repository.delete(user);
        }
    }
}
