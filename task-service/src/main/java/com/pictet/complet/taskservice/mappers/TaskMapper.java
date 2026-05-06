package com.pictet.complet.taskservice.mappers;

import com.pictet.complet.taskservice.entities.Task;
import com.pictet.complet.taskservice.models.TaskDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    Task toEntity(TaskDTO taskDTO);
    TaskDTO toDTO(Task task);
}
