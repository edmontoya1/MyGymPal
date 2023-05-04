package com.eduardomontoya.config;

import com.eduardomontoya.model.User;
import com.eduardomontoya.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository) {
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
            userRepository.saveAll(
                    List.of(foo, bar)
            );
        };
    }
}
