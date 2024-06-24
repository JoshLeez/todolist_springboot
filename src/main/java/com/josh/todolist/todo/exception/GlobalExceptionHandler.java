package com.josh.todolist.todo.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TodoException.class)
    public ResponseEntity<TodoErrorResponse> TodoExceptionHandler(TodoException x){
        TodoErrorResponse todoErrorResponse =  TodoErrorResponse.builder()
                .status(x.getStatusCode().value())
                .message(x.getReason())
                .build();
        return new ResponseEntity<>(todoErrorResponse, x.getStatusCode());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<TodoErrorResponse> handleValidationException(MethodArgumentNotValidException x){
        Map<String, String> errors = new HashMap<>();
        x.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        TodoErrorResponse todoErrorResponse = TodoErrorResponse.withValidationErrors(HttpStatus.BAD_REQUEST.value(), "Validation Errors" ,errors);
        return new ResponseEntity<>(todoErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleHttpMessageNotReadableException() {
        return ResponseEntity.badRequest().body("Invalid request: The request body is empty or invalid");
    }
}
