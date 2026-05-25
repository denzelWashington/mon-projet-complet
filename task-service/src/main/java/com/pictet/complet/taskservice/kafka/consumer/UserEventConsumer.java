package com.pictet.complet.taskservice.kafka.consumer;

import com.pictet.complet.taskservice.entities.ProcessedEvent;
import com.pictet.complet.taskservice.kafka.event.UserCreatedEvent;
import com.pictet.complet.taskservice.repositories.ProcessedEventRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
public class UserEventConsumer {

    private final ProcessedEventRepository processedEventRepository;

    UserEventConsumer(ProcessedEventRepository processedEventRepository) {
        this.processedEventRepository = processedEventRepository;
    }

    @KafkaListener(
            topics = "user-created-topic",
            groupId = "task-service-group"
    )
    public void consume(UserCreatedEvent event) {

        // 1. check idempotency
        if (this.processedEventRepository.existsById(UUID.fromString(event.getMessageId()))) {
            System.out.println("****** EXIST DEJA ********* ");
            return;
        }


        System.out.println("message id : " + event.getMessageId());
        System.out.println("USER RECEIVED: " + event.getUserId());
        System.out.println("QUOTA: " + event.getQuota());

        this.processedEventRepository.save(
                new ProcessedEvent(
                        UUID.fromString(event.getMessageId()),
                        Instant.now())
        );


        // TODO:
        // save in DB table user_quota
    }

    @PostConstruct
    public void init() {
        System.out.println("CONSUMER LOADED");
    }

}
