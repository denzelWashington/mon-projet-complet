package com.pictet.complet.userservice;

import com.pictet.complet.userservice.entities.User;
import com.pictet.complet.userservice.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
public class UserServiceApplication {

    /*@Bean
    CommandLineRunner start(UserRepository userRepository) {
        return args -> {
            userRepository.save(new User(null, "Wassim", "SB", 500));
            userRepository.save(new User(null, "Jean", "Dupont", 200));
            System.out.println("Base de données initialisée !");
        };
    }*/

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

}
