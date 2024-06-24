package com.josh.todolist.todo.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TodoErrorResponse {
    private final LocalDateTime timeStamp = LocalDateTime.now();
    private final int status;
    private final String message;
    private final Map<String, String> errors;


    public static TodoErrorResponse withValidationErrors(int status,String message, Map<String, String> errors) {
        return new TodoErrorResponse(status, message, errors);
    }
}
