package com.josh.todolist.todo;

import java.util.List;

public interface TodoService {
    TodoDTO getTodoById(Long id);
    List<Todo> getAllTodos();
    TodoDTO createTodo(TodoDTO todoDTO);
    TodoDTO updateTodo(Long id, TodoDTO todoDTO);
    void deleteTodo(Long id);
}
