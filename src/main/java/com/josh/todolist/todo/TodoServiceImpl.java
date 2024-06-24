package com.josh.todolist.todo;

import com.josh.todolist.todo.exception.TodoException;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    @Autowired  private final TodoRepository todoRepository;
    @Autowired private Validator validator;

    @Override
    public TodoDTO getTodoById(Long id){
        Optional<Todo> todoById = todoRepository.findById(id);
        return todoById.map(this::convertTodo).orElseThrow(() -> new TodoException(HttpStatus.NOT_FOUND, id));
    };

    @Override
    public List<Todo> getAllTodos(){
        return todoRepository.findAll();
    };

    @Override
    @Transactional
    public TodoDTO createTodo(TodoDTO todoDTO){
        return convertTodo(todoRepository.save(Todo.builder()
                        .title(todoDTO.getTitle())
                        .description(todoDTO.getDescription())
                        .completed(todoDTO.isCompleted())
                        .build()));
    };

    @Override
    @Transactional
    public TodoDTO updateTodo(Long id, TodoDTO todoDTO) {
        // Find the Todo by id, or throw an exception if not found
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new TodoException(HttpStatus.NOT_FOUND, id));
        // Update the fields with the data from TodoDTO
        todo.setTitle(todoDTO.getTitle());
        todo.setDescription(todoDTO.getDescription());
        todo.setCompleted(todoDTO.isCompleted());
        // Save the updated Todo entity and return the converted DTO
        return convertTodo(todoRepository.save(todo));
    }

    @Override
    public void deleteTodo(Long id){
        todoRepository.deleteById(id);
    };

    private TodoDTO convertTodo(Todo todo){
      return TodoDTO.builder()
              .title(todo.getTitle())
              .description(todo.getDescription())
              .completed(todo.isCompleted())
              .build();
    };
}
