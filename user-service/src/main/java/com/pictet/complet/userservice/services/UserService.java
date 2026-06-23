package com.pictet.complet.userservice.services;

import com.pictet.complet.userservice.dtos.UserDTO;
import com.pictet.complet.userservice.dtos.UserQuotaDTO;
import com.pictet.complet.userservice.entities.User;
import com.pictet.complet.userservice.kafka.event.UserCreatedEvent;
import com.pictet.complet.userservice.kafka.producer.UserEventProducer;
import com.pictet.complet.userservice.mappers.UserMapper;
import com.pictet.complet.userservice.repositories.UserRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

    @CacheEvict(value = "users:list", allEntries = true)
    public User saveUser(UserDTO userDTO) {
        User user = this.userMapper.toEntity(userDTO);
        User user1 = this.userRepository.save(user);

        var messageId = UUID.randomUUID().toString();
        UserCreatedEvent userEvent = new UserCreatedEvent(
                user1.getId().toString(),
                user1.getQuota(),
                messageId
        );
        this.userEventProducer.send(userEvent);

        return user1;
    }

    @Cacheable(value = "users:list")
    public Iterable<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @Cacheable(value = "users", key = "#id")
    public User getUserById(UUID id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Cacheable(value = "quotas")
    public List<UserQuotaDTO> getAllQuota() {
        return this.userRepository.findAllQuota();
    }


}
