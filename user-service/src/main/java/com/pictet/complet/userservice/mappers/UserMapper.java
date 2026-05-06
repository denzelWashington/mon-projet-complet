package com.pictet.complet.userservice.mappers;

import com.pictet.complet.userservice.dtos.UserDTO;
import com.pictet.complet.userservice.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    public UserDTO toDTO(User user);
    public User toEntity(UserDTO userDTO);
}
