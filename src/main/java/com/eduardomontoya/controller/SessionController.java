package com.eduardomontoya.controller;

import com.eduardomontoya.model.Session;
import com.eduardomontoya.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("api/v1/sessions")
public class SessionController {

    @Autowired
    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping
    public List<Session> getAllSessions() {
        return sessionService.getAllSessions();
    }

    @PostMapping
    public void createSession(@RequestBody Session session) {
        sessionService.createSession(session);
    }

    @GetMapping("/{sessionId}")
    public Optional<Session> getSessionById(@PathVariable(value = "sessionId") Long id) {
        return sessionService.getSessionById(id);
    }

}
