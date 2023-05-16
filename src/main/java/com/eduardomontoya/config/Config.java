package com.eduardomontoya.config;

import com.eduardomontoya.model.Session;
import com.eduardomontoya.model.User;
import com.eduardomontoya.repository.SessionRepository;
import com.eduardomontoya.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.List;

@Configuration
public class Config {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository, SessionRepository sessionRepository) {
        return args -> {
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
            Session session1 = new Session(
                    new Date(),
                    "Push"
            );
            Session session2 = new Session(
                    new Date(),
                    "Pull"
            );
            Session session3 = new Session(
                    new Date(),
                    "Legs"
            );
            sessionRepository.saveAll(
                    List.of(session1, session2, session3)
            );
            userRepository.saveAll(
                    List.of(foo, bar)
            );
        };
    }
}
