package com.sample.angular.controller;


import com.sample.angular.model.Todo;
import com.sample.angular.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
    private TodoService todoService;

    public TodoController(TodoService todoService){
        this.todoService=todoService;
    }

    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodos() {
        List<Todo> todos = todoService.findAllTodo();
        if (todos == null || todos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        System.out.println(todos);
        return ResponseEntity.ok(todos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable int id) {
        Optional<Todo> todo=todoService.findById(id);
        if(todo.isPresent()){
            return ResponseEntity.ok(todo.get());
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public void createTodo(@RequestBody Todo todo) {
        todoService.saveTodo(todo);
    }

    @PutMapping("/{id}")
    public Todo updateTodo(@PathVariable int id, @RequestBody Todo updatedTodo) {
        System.out.println("Updating Todo with ID: " + id);
        Optional<Todo> existingTodo=todoService.findById(id);
        Todo todo=existingTodo.get();
        todo.setTask(updatedTodo.getTask());
        todo.setCompleted(updatedTodo.isCompleted());
        todoService.saveTodo(todo);
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable int id) {
        todoService.deleteById(id);
    }
}
