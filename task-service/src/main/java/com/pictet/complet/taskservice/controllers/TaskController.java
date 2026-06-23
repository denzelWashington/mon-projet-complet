package com.pictet.complet.taskservice.controllers;

import com.pictet.complet.taskservice.aspects.LogMe;
import com.pictet.complet.taskservice.entities.Task;
import com.pictet.complet.taskservice.mappers.TaskMapper;
import com.pictet.complet.taskservice.models.TaskDTO;
import com.pictet.complet.taskservice.services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api")
public class TaskController {

    private final TaskMapper taskMapper;
    private final TaskService taskService;

    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @LogMe
    @GetMapping(path = "/task")
    String imAlive() {
        return "ok";
    }

    @PostMapping(path = "/task/{userId}")
    ResponseEntity<TaskDTO> saveTask(@RequestBody TaskDTO taskDTO, @PathVariable UUID userId) {
        Task task = taskService.saveTask(taskDTO, userId);
        TaskDTO taskDTO1 = taskMapper.toDTO(task);
        return ResponseEntity.ok(taskDTO1);
    }

    @GetMapping(path = "/task/{userId}")
    ResponseEntity<List<TaskDTO>> getTasks(@PathVariable UUID userId) {
        List<Task> task = taskService.getTasks(userId);

        List<TaskDTO> taskDTO1 = task.stream().map(taskMapper::toDTO).toList() ;
        return ResponseEntity.ok(taskDTO1);
    }
}
