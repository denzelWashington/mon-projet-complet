package com.pictet.complet.taskservice.kafka.consumer;

import com.pictet.complet.taskservice.kafka.event.UserCreatedEvent;
import jakarta.annotation.PostConstruct;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserEventConsumer {

    @KafkaListener(
            topics = "user-created-topic",
            groupId = "task-service-group"
    )
    public void consume(UserCreatedEvent event) {

        System.out.println("USER RECEIVED: " + event.getId());
        System.out.println("QUOTA: " + event.getQuota());

        // TODO:
        // save in DB table user_quota
    }

    @PostConstruct
    public void init() {
        System.out.println("CONSUMER LOADED");
    }

}
