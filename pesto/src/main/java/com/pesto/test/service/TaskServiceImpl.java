package com.pesto.test.service;

import com.pesto.test.Requests.TaskRequest;
import com.pesto.test.Responses.TaskResponse;
import com.pesto.test.entity.Task;
import com.pesto.test.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<TaskResponse> fetchAllTasks() {
        List<Task> tasks =  taskRepository.findAll();
        List<TaskResponse> taskResponse = new ArrayList<>();
        for(Task task: tasks){
            taskResponse.add(TaskResponse.builder()
                    .id(task.getId())
                    .title(task.getTitle())
                    .description(task.getDescription())
                    .status(task.getStatus())
                    .build());
        }

        return taskResponse;
    }

    @Override
    public Optional<Task> getTask(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public boolean deleteTask(Long id) {
        Optional<Task> optionalTask = getTask(id);
        if(optionalTask.isPresent()){
            taskRepository.delete(optionalTask.get());
            return true;
        }

        return false;
    }

    @Override
    public TaskResponse updateTask(Long id, TaskRequest taskRequest) {
        Optional<Task> optionalTask = getTask(id);
        if(optionalTask.isPresent()){
            Task task = optionalTask.get();
            task.setDescription(taskRequest.getDescription());
            task.setStatus(taskRequest.getStatus());
            task.setTitle(taskRequest.getTitle());
            taskRepository.save(task);
            return TaskResponse.builder()
                    .id(task.getId())
                    .title(task.getTitle())
                    .description(task.getDescription())
                    .status(task.getStatus())
                    .build();
        }

        return null;
    }

    @Override
    public TaskResponse createTask(TaskRequest taskRequest) {
        Task task = Task.builder().description(taskRequest.getDescription())
                .title(taskRequest.getTitle()).status(taskRequest.getStatus()).build();
        task = taskRepository.save(task);
        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .build();
    }
}
