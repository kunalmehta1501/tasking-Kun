package com.pesto.test.Requests;

import com.pesto.test.entity.TaskStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class TaskRequest {

    private Long id;
    @NotNull(message = "title can not be null")
    @NotBlank(message = "title can not be blank")
    @NotEmpty(message = "title can not be empty")
    private String title;
    private String description;
    @NotNull(message = "status can not be null")
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
}

