package com.pictet.complet.taskservice.services;

import com.pictet.complet.taskservice.entities.Task;
import com.pictet.complet.taskservice.mappers.TaskMapper;
import com.pictet.complet.taskservice.models.TaskDTO;
import com.pictet.complet.taskservice.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final UserQuotaService userQuotaService;

    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper, UserQuotaService userQuotaService) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
        this.userQuotaService = userQuotaService;
    }



    public Task saveTask(TaskDTO taskDTO, UUID userId) {
        this.userQuotaService.decrementQuota(userId);
        Task task1 = taskMapper.toEntity(taskDTO);
        task1.setUserId(userId);
        return this.taskRepository.save(task1);
    }

    public List<Task> getTasks(UUID userId) {
        return this.taskRepository.findByUserId(userId).stream().toList();
    }
}
