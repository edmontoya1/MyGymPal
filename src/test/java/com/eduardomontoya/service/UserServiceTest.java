package com.eduardomontoya.service;

import com.eduardomontoya.model.User;
import com.eduardomontoya.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    private UserService underTest;

    @BeforeEach
    void setUp() {
        underTest = new UserService(userRepository);
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
        userRepository.saveAll(List.of(foo, bar));
    }

    @Test
    void getAllUsers() {
        // when
        underTest.getAllUsers();

        // then
        verify(userRepository).findAll();
    }

    @Test
    void deleteUserById() {
        // when
        Long id = 1L;
        underTest.deleteUserById(id);

        // then
        verify(userRepository).deleteById(id);
    }

    @Test
    void getUserById() {
        // when
        underTest.getUserById(anyLong());

        // then
        verify(userRepository).findById(anyLong());
    }

    @Test
    void canCreateUser() {
        // given
        User foo = new User(
                "Foo",
                "Last1",
                "email@gmail.com"
        );
        // when
        underTest.createUser(foo);

        // then
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userArgumentCaptor.capture());

        User capturedUser = userArgumentCaptor.getValue();

        assertThat(capturedUser).isEqualTo(foo);
    }

    @Test
    void cantCreateUser() {
        // given
        String email = "email1@gmail.com";
        User foo = new User(
                "Foo",
                "Last1",
                email
        );
        Optional<User> opt = Optional.of(foo);
        given(userRepository.findUserByEmail(email)).willReturn(opt);
        // when
        // then
        assertThatThrownBy(() -> underTest.createUser(foo))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("User email already been used");

        verify(userRepository, never()).save(any());
    }

    @Test
    void canGetUserByFirstName() {
        // given
        String email = "email1@gmail.com";
        User foo = new User(
                "Foo",
                "Last1",
                email
        );
        Optional<User> opt = Optional.of(foo);
        given(userRepository.findUserByFirstName(foo.getFirstName()))
                .willReturn(opt);
        // when
        // then
       assertThat(underTest.getUserByFirstName(foo.getFirstName())).isEqualTo(opt);
    }

    @Test
    void cantGetUserByFirstName() {
        // given
        String email = "email1@gmail.com";
        User foo = new User(
                "Cant",
                "Last1",
                email
        );
        Optional<User> opt = Optional.of(foo);
        given(userRepository.findUserByFirstName(foo.getFirstName()))
                .willReturn(Optional.empty());
        // when
        // then
        assertThatThrownBy(() -> underTest.getUserByFirstName(foo.getFirstName()))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("User not found");
    }

    @Test
    @Disabled
    void canDeleteUserByEmail() {

    }
}