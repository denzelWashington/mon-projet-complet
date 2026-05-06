package com.pictet.complet.userservice.controllers;

import com.pictet.complet.userservice.dtos.UserDTO;
import com.pictet.complet.userservice.entities.User;
import com.pictet.complet.userservice.mappers.UserMapper;
import com.pictet.complet.userservice.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping(path = "/api")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping(path = "/user")
    ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) {
        User user = userService.saveUser(userDTO);
        UserDTO userDto = userMapper.toDTO(user);
        return ResponseEntity.ok(userDto);
    }

}
