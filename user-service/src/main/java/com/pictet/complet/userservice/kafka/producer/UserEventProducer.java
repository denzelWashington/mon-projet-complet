package com.pictet.complet.userservice.kafka.producer;

import com.pictet.complet.userservice.kafka.event.UserCreatedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserEventProducer {

    private final KafkaTemplate<String, UserCreatedEvent> kafkaTemplate;

    public UserEventProducer(KafkaTemplate<String, UserCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(UserCreatedEvent event) {
        kafkaTemplate.send("user-created-topic", event.getId(), event);
    }
}

