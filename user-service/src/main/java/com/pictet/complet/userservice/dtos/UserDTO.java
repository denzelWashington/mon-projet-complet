package com.pictet.complet.userservice.dtos;


import lombok.Data;

import java.util.UUID;
@Data
public class UserDTO {
    private UUID id;
    private String firstName;
    private String lastName;
    private long quota;
}
