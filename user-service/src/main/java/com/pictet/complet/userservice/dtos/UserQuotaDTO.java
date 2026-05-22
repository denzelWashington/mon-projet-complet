package com.pictet.complet.userservice.dtos;

import lombok.Builder;

import java.util.UUID;

@Builder
public record UserQuotaDTO(UUID id, long quota) {

}
