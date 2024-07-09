package com.pesto.test.service;

import com.pesto.test.Requests.TaskRequest;
import com.pesto.test.Responses.TaskResponse;
import com.pesto.test.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    List<TaskResponse> fetchAllTasks();

    Optional<Task> getTask(Long id);

    boolean deleteTask(Long id);

    TaskResponse updateTask(Long id, TaskRequest taskRequest);

    TaskResponse createTask(TaskRequest taskRequest);
}
