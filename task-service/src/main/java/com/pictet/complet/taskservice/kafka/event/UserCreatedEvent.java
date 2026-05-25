package com.pictet.complet.taskservice.kafka.event;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreatedEvent {
    private String messageId;
    private String userId;
    private long quota;
}
