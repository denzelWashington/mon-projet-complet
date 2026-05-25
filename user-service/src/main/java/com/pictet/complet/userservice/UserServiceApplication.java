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


    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

}
