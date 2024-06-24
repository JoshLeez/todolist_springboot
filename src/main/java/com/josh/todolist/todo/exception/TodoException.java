package com.josh.todolist.todo.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class TodoException extends ResponseStatusException {

    public TodoException(HttpStatus status, Long id){
        super(status, "Todo of id " + id + " Not Found");
    }

}
