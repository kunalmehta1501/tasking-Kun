package com.pesto.test.controller;

import com.pesto.test.Requests.TaskRequest;
import com.pesto.test.Responses.TaskResponse;
import com.pesto.test.entity.Task;
import com.pesto.test.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@Validated
@CrossOrigin(origins = "http://localhost:3000")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskResponse>> getAllTasks(){
        try {
            List<TaskResponse> tasks = taskService.fetchAllTasks();

            if (tasks.isEmpty())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(tasks, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<TaskResponse> getTask(@PathVariable("id") Long id){
        try {
            Optional<Task> optionalTask = taskService.getTask(id);

            if (optionalTask.isEmpty())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            Task task = optionalTask.get();
            TaskResponse taskResponse = TaskResponse.builder()
                    .id(task.getId())
                    .title(task.getTitle())
                    .description(task.getDescription())
                    .status(task.getStatus())
                    .build();

            return new ResponseEntity<>(taskResponse, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<TaskResponse> deleteTask(@PathVariable("id") Long id){
        try {
            boolean deleted = taskService.deleteTask(id);

            if (!deleted)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<?> updateTask(@PathVariable("id") Long id, @Valid @RequestBody TaskRequest taskRequest){
        try {
            TaskResponse taskResponse = taskService.updateTask(id, taskRequest);

            if (taskResponse == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(taskResponse, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/tasks")
    public ResponseEntity<?> createTask(@Valid @RequestBody TaskRequest taskRequest){
        try {
            TaskResponse taskResponse = taskService.createTask(taskRequest);
            return new ResponseEntity<>(taskResponse, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
