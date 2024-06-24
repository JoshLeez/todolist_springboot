package com.josh.todolist.todo;


import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TodoDTO {
    @NotBlank @NotEmpty
    private String title;

    @NotBlank @NotEmpty
    private String description;

    @NotNull
    private boolean completed;
}
