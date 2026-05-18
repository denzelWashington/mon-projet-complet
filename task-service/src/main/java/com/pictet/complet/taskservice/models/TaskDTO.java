package com.pictet.complet.taskservice.models;

import java.util.UUID;

public record TaskDTO(
        String title,
        String status,
        UUID userId) {
}
