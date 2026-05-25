package com.pictet.complet.userservice.kafka.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreatedEvent {
    private String userId;
    private long quota;
    private String messageId;
}
