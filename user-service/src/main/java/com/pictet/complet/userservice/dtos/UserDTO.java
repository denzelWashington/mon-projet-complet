package com.pictet.complet.userservice.dtos;

import lombok.Builder;

import java.util.UUID;

@Builder
public record UserDTO(UUID id,
                      String firstName,
                      String lastName,
                      long quota) {
}
