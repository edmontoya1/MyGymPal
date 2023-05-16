package com.eduardomontoya.service;

import com.eduardomontoya.model.Session;
import com.eduardomontoya.model.User;
import com.eduardomontoya.repository.SessionRepository;
import com.eduardomontoya.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SessionServiceTest {

    @Mock
    private SessionRepository sessionRepository;
    private SessionService underTest;

    @BeforeEach
    void setUp() {
        underTest = new SessionService(sessionRepository);
        Session push = new Session(new Date(), "Push");
        Session pull = new Session(new Date(), "Pull");
        Session legs = new Session(new Date(), "Legs");
        sessionRepository.saveAll(List.of(push, pull, legs));
    }

    @Test
    void getAllSessions() {
        underTest.getAllSessions();

        verify(sessionRepository).findAll();
    }

    @Test
    void createSession() {
        Session foo = new Session(new Date(), "TestCreate");
        underTest.createSession(foo);

        ArgumentCaptor<Session> sessionArgumentCaptor = ArgumentCaptor.forClass(Session.class);
        verify(sessionRepository).save(sessionArgumentCaptor.capture());

        Session capturedSession = sessionArgumentCaptor.getValue();

        assertThat(capturedSession).isEqualTo(foo);
    }

    @Test
    void getSessionById() {
        underTest.getSessionById(anyLong());

        verify(sessionRepository).findById(anyLong());
    }
}