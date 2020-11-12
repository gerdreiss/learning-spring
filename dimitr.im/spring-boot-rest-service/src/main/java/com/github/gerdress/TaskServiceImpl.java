package com.github.gerdress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl {
    @Autowired
    private TaskRepository repository;

    public List<TaskDTO> findAll() {
        return repository.findAll().stream()
                .map(entity -> new TaskDTO(entity.getId(), entity.getTask(), entity.isCompleted()))
                .collect(Collectors.toList());
    }
}