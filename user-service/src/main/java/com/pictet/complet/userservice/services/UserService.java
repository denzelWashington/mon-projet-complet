package com.pictet.complet.userservice.services;

import com.pictet.complet.userservice.dtos.UserDTO;
import com.pictet.complet.userservice.entities.User;
import com.pictet.complet.userservice.mappers.UserMapper;
import com.pictet.complet.userservice.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public User saveUser(UserDTO userDTO) {
        User user = this.userMapper.toEntity(userDTO);
        return this.userRepository.save(user);
    }

}
