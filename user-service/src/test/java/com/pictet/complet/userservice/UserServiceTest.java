package com.pictet.complet.userservice;

import com.pictet.complet.userservice.entities.User;
import com.pictet.complet.userservice.kafka.producer.UserEventProducer;
import com.pictet.complet.userservice.mappers.UserMapper;
import com.pictet.complet.userservice.repositories.UserRepository;
import com.pictet.complet.userservice.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    UserMapper userMapper;

    @Mock
    UserEventProducer userEventProducer;

    @InjectMocks
    UserService service;

    @Test
    void shouldReturnUserWhenUserExists() {
        UUID id = UUID.randomUUID();
        User user = new User();
        user.setId(id);

        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        User user1 = service.getUserById(id);

        assertEquals(id, user1.getId());
        verify(userRepository).findById(id);
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {

        UUID id = UUID.randomUUID();
        when(userRepository.findById(id))
                .thenReturn(Optional.empty());

        RuntimeException exception =
                assertThrows(RuntimeException.class,
                        () -> service.getUserById(id));

        assertEquals("User not found", exception.getMessage());
        verify(userRepository).findById(id);
    }

}
