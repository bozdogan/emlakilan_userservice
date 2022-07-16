package org.bozdgn.userservice.service;

import org.bozdgn.userservice.model.User;
import org.bozdgn.userservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    private static PasswordEncoder passwordEncoder;
    @Mock
    private UserRepository userRepository;
    private UserService underTest;

    @BeforeAll
    static void initializePasswordEncoder() {
        passwordEncoder = new BCryptPasswordEncoder();
    }

    @BeforeEach
    void setUp() {
        underTest = new UserService(userRepository, passwordEncoder);
    }

    @Test
    void canGetAllUsers() {
        underTest.getAll();
        Mockito.verify(userRepository).findAll();  // NOTE(bora): This test passes if UserRepository#findAll method is invoked.
    }

    @Test
    void canSaveUser() {
        User user = new User(
                null,
                "bjork",
                "test",
                "bjork@bjorkss.on",
                false,
                "Björk",
                "Björkoğlu",
                "01112223344");

        underTest.saveEntity(user);

        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        Mockito.verify(userRepository).save(userArgumentCaptor.capture());

        User capturedUser = userArgumentCaptor.getValue();
        assertThat(capturedUser).isEqualTo(user);
    }
}