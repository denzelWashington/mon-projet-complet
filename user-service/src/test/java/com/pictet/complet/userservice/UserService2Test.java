package com.pictet.complet.userservice;

import com.pictet.complet.userservice.kafka.producer.UserEventProducer;
import com.pictet.complet.userservice.mappers.UserMapper;
import com.pictet.complet.userservice.repositories.UserRepository;
import com.pictet.complet.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


@SpringBootTest
class UserServiceSpringTest {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserMapper userMapper;

    @MockBean
    private UserEventProducer userEventProducer;

    @Autowired
    private UserService userService;
}

