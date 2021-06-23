package com.example.testproject;

import com.example.testproject.entity.User;
import com.example.testproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@RequiredArgsConstructor
@EnableJpaAuditing
@SpringBootApplication
public class TestProjectApplication implements CommandLineRunner {

    private final UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(TestProjectApplication.class, args);
    }

    @Override
    public void run(String... args) {
        User user1 = User.builder()
                .email("unkwn11@naver.com")
                .username("a")
                .password("a")
                .build();

        User user2 = User.builder()
                .email("unkwn12@naver.com")
                .username("b")
                .password("b")
                .build();

        User user3 = User.builder()
                .email("unkwn13@naver.com")
                .username("c")
                .password("c")
                .build();

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
    }
}
