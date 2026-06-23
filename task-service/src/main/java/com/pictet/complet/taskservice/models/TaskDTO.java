package com.pictet.complet.taskservice.models;

import lombok.Builder;

import java.util.UUID;

@Builder
public record TaskDTO(
        UUID id,
        String title,
        String status,
        UUID userId) {
}
