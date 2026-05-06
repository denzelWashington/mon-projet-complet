package com.pictet.complet.taskservice.services;

import com.pictet.complet.taskservice.entities.Task;
import com.pictet.complet.taskservice.mappers.TaskMapper;
import com.pictet.complet.taskservice.models.TaskDTO;
import com.pictet.complet.taskservice.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    public Task saveTask(TaskDTO taskDTO, UUID userId) {
        Task task1 = taskMapper.toEntity(taskDTO);
        task1.setUserId(userId);
        return this.taskRepository.save(task1);
    }
}
