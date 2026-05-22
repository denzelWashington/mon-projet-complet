package com.pictet.complet.userservice.services;

import com.pictet.complet.userservice.dtos.UserDTO;
import com.pictet.complet.userservice.dtos.UserQuotaDTO;
import com.pictet.complet.userservice.entities.User;
import com.pictet.complet.userservice.kafka.event.UserCreatedEvent;
import com.pictet.complet.userservice.kafka.producer.UserEventProducer;
import com.pictet.complet.userservice.mappers.UserMapper;
import com.pictet.complet.userservice.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserEventProducer userEventProducer;
    public UserService(UserRepository userRepository, UserMapper userMapper,
                       UserEventProducer userEventProducer) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.userEventProducer = userEventProducer;
    }

    public User saveUser(UserDTO userDTO) {
        User user = this.userMapper.toEntity(userDTO);


        User user1=  this.userRepository.save(user);


        UserCreatedEvent userEvent = new UserCreatedEvent(
                user1.getId().toString(),
                user1.getQuota()
        );
        this.userEventProducer.send(userEvent);

        return user1;
    }

    public Iterable<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public User getUserById(UUID id) {
        return this.userRepository.findById(id).orElse(null);
    }

    public List<UserQuotaDTO> getAllQuota() {
        return this.userRepository.findAllQuota();
    }
}
