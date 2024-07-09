package com.pesto.test;

import com.pesto.test.Requests.TaskRequest;
import com.pesto.test.Responses.TaskResponse;
import com.pesto.test.controller.TaskController;
import com.pesto.test.entity.Task;
import com.pesto.test.entity.TaskStatus;
import com.pesto.test.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaskControllerTest {
    @Mock
    private TaskService taskService;
    @InjectMocks
    TaskController underTest;
    private LocalValidatorFactoryBean validator;

    @BeforeEach
    void setUp() {
        validator = new LocalValidatorFactoryBean();
        validator.afterPropertiesSet(); // Initialize the validator
    }

    @Test
    void createTask_shouldCreateSuccessfully(){
        TaskRequest taskRequest = TaskRequest.builder()
                .status(TaskStatus.IN_PROGRESS)
                .title("Test")
                .description("desc")
                .build();
        TaskResponse expectedTaskResponse = TaskResponse.builder()
                .status(TaskStatus.IN_PROGRESS)
                .title("Test")
                .description("desc")
                .id(1L)
                .build();
        when(taskService.createTask(taskRequest)).thenReturn(expectedTaskResponse);

        ResponseEntity<?> response = underTest.createTask(taskRequest);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), expectedTaskResponse);
    }

    @Test
    void createTask_shouldReturnInternalServerError(){
        TaskRequest taskRequest = TaskRequest.builder()
                .status(TaskStatus.IN_PROGRESS)
                .title("Test")
                .description("desc")
                .build();
        when(taskService.createTask(taskRequest)).thenThrow(new RuntimeException());

        ResponseEntity<?> response = underTest.createTask(taskRequest);
        assertEquals(response.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ParameterizedTest(name = "{index} => title={0}, status={1}, expectedStatus={2}")
    @MethodSource("invalidTaskRequestProvider")
    @DisplayName("create task should return Bad request when invalid title or status")
    void createTask_shouldReturnBadRequest_WhenInvalidRequest(String title, TaskStatus status, HttpStatus expectedStatus) {
        // Given
        TaskRequest taskRequest = TaskRequest.builder()
                .status(status)
                .title(title)
                .description("desc")
                .build();

        DataBinder dataBinder = new DataBinder(taskRequest);
        dataBinder.setValidator(validator);
        dataBinder.validate();
        BindingResult bindingResult = dataBinder.getBindingResult();

        // When
        ResponseEntity<?> response;
        if (bindingResult.hasErrors()) {
            response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            response = underTest.createTask(taskRequest);
        }

        // Then
        assertEquals(expectedStatus, response.getStatusCode());
    }

    private static Stream<Arguments> invalidTaskRequestProvider() {
        return Stream.of(
                Arguments.of("", TaskStatus.IN_PROGRESS, HttpStatus.BAD_REQUEST),
                Arguments.of("Test", null, HttpStatus.BAD_REQUEST),
                Arguments.of(null, TaskStatus.IN_PROGRESS, HttpStatus.BAD_REQUEST),
                Arguments.of("Test", null, HttpStatus.BAD_REQUEST)
        );
    }

    @ParameterizedTest(name = "{index} => title={0}, status={1}, expectedStatus={2}")
    @MethodSource("invalidTaskRequestProvider")
    @DisplayName("update task should return Bad request when invalid title or status")
    void updateTask_shouldReturnBadRequest_WhenInvalidRequest(String title, TaskStatus status, HttpStatus expectedStatus) {
        // Given
        TaskRequest taskRequest = TaskRequest.builder()
                .status(status)
                .title(title)
                .description("desc")
                .build();

        DataBinder dataBinder = new DataBinder(taskRequest);
        dataBinder.setValidator(validator);
        dataBinder.validate();
        BindingResult bindingResult = dataBinder.getBindingResult();

        // When
        ResponseEntity<?> response;
        if (bindingResult.hasErrors()) {
            response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            response = underTest.updateTask(1L, taskRequest);
        }

        // Then
        assertEquals(expectedStatus, response.getStatusCode());
    }

    @Test
    void updateTask_shouldReturnInternalServerError(){
        TaskRequest taskRequest = TaskRequest.builder()
                .status(TaskStatus.IN_PROGRESS)
                .title("Test")
                .description("desc")
                .build();
        when(taskService.updateTask(1L, taskRequest)).thenThrow(new RuntimeException());

        ResponseEntity<?> response = underTest.updateTask(1L, taskRequest);
        assertEquals(response.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    void updateTask_shouldUpdateSuccessfully(){
        TaskRequest taskRequest = TaskRequest.builder()
                .status(TaskStatus.IN_PROGRESS)
                .title("Test")
                .description("desc")
                .build();
        TaskResponse expectedTaskResponse = TaskResponse.builder()
                .status(TaskStatus.IN_PROGRESS)
                .title("updated")
                .description("desc")
                .id(1L)
                .build();
        when(taskService.updateTask(1L, taskRequest)).thenReturn(expectedTaskResponse);

        ResponseEntity<?> response = underTest.updateTask(1L, taskRequest);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), expectedTaskResponse);
    }

    @Test
    void deleteTask_shouldDeleteSuccessfully(){
        when(taskService.deleteTask(1L)).thenReturn(true);

        ResponseEntity<?> response = underTest.deleteTask(1L);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void deleteTask_shouldReturnInternalServerError(){
        when(taskService.deleteTask(1L)).thenThrow(new RuntimeException());

        ResponseEntity<?> response = underTest.deleteTask(1L);
        assertEquals(response.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    void getTask_shouldReturnInternalServerError(){
        when(taskService.getTask(1L)).thenThrow(new RuntimeException());

        ResponseEntity<?> response = underTest.getTask(1L);
        assertEquals(response.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    void getAllTask_shouldReturnInternalServerError(){
        when(taskService.fetchAllTasks()).thenThrow(new RuntimeException());

        ResponseEntity<?> response = underTest.getAllTasks();
        assertEquals(response.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    void getTask_shouldReturnTaskSuccessfully(){
        Task task = Task.builder().status(TaskStatus.IN_PROGRESS)
                .title("updated")
                .description("desc")
                .id(1L).build();
        TaskResponse expectedTaskResponse = TaskResponse.builder()
                .status(TaskStatus.IN_PROGRESS)
                .title("updated")
                .description("desc")
                .id(1L)
                .build();
        when(taskService.getTask(1L)).thenReturn(Optional.of(task));

        ResponseEntity<?> response = underTest.getTask(1L);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), expectedTaskResponse);
    }

    @Test
    void getTask_shouldReturnNotFound(){
        ResponseEntity<?> response = underTest.getTask(10L);
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    void deleteTask_shouldReturnNotFound(){
        ResponseEntity<?> response = underTest.deleteTask(10L);
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    void updateTask_shouldReturnNotFound(){
        ResponseEntity<?> response = underTest.updateTask(10L, TaskRequest.builder().build());
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    void getAllTask_shouldReturnNotFound(){
        ResponseEntity<?> response = underTest.getAllTasks();
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    void getAllTask_shouldReturnAllTaskSuccessfully(){
        TaskResponse expectedTaskResponse = TaskResponse.builder()
                .status(TaskStatus.IN_PROGRESS)
                .title("updated")
                .description("desc")
                .id(1L)
                .build();
        List<TaskResponse> expectedResponse = new ArrayList<>();
        expectedResponse.add(expectedTaskResponse);
        when(taskService.fetchAllTasks()).thenReturn(expectedResponse);

        ResponseEntity<?> response = underTest.getAllTasks();
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), expectedResponse);
    }
}
