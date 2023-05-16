package com.eduardomontoya.repository;

import com.eduardomontoya.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository underTest;

    @BeforeEach
    void setUp() {
        User foo = new User(
                "Foo",
                "Last1",
                "email1@gmail.com"
        );
        User bar = new User(
                "Bar",
                "Last2",
                "email2@gmail.com"
        );
        underTest.saveAll(List.of(foo, bar));
    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void findUserByEmailExists() {
        String email = "email2@gmail.com";
        boolean expected = underTest.findUserByEmail(email).isPresent();
        assertThat(expected).isTrue();
    }

    @Test
    void findUserByEmailNotExists() {
        String email = "notfound@gmail.com";
        boolean expected = underTest.findUserByEmail(email).isPresent();
        assertThat(expected).isFalse();
    }

    @Test
    void findUserByFirstNameExists() {
        String firstName = "Foo";
        boolean expected = underTest.findUserByFirstName(firstName).isPresent();
        assertThat(expected).isTrue();
    }

    @Test
    void findUserByFirstNameNotExists() {
        String firstName = "NotExists";
        boolean expected = underTest.findUserByFirstName(firstName).isPresent();
        assertThat(expected).isFalse();
    }
}