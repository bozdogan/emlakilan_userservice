package org.bozdgn.userservice.repository;

import org.bozdgn.userservice.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private UserRepository underTest;

    @Test
    void userIsSavedAndUsernameExists() {
        String username = "bjork";
        User testUser = new User(
                null,
                username,
                "test",
                "bjork@bjorkss.on",
                false,
                "Björk",
                "Björkoğlu",
                "01112223344");
        underTest.save(testUser);

        User retrievedUser = underTest.findByUsername(username);
        assertThat(retrievedUser).isNotNull();

        String expected = retrievedUser.getUsername();
        assertThat(username).isEqualTo(expected);
    }

    @Test
    void userDoesNotExist() {
        String username = "haberturk";

        User retrievedUser = underTest.findByUsername(username);
        assertThat(retrievedUser).isNull();
    }
}