package org.bozdgn.userservice.service;

import org.bozdgn.userservice.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

class UserServiceTest {

    private static PasswordEncoder passwordEncoder;
    @Mock
    private UserRepository userRepository;
    private AutoCloseable mocks;
    private UserService underTest;

    @BeforeAll
    static void initializePasswordEncoder() {
        passwordEncoder = new BCryptPasswordEncoder();
    }

    @BeforeEach
    void setUp() {
        mocks = MockitoAnnotations.openMocks(this);
        underTest = new UserService(userRepository, passwordEncoder);
    }

    @Test
    void canGetAllUsers() {
        underTest.getAll();
        Mockito.verify(userRepository).findAll();  // NOTE(bora): This test passes if UserRepository#findAll method is invoked.
    }

    @AfterEach
    void tearDown() throws Exception {
        mocks.close();
    }
}