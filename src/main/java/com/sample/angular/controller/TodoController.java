package com.sample.angular.controller;


import com.sample.angular.model.Todo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private List<Todo> todos = new ArrayList<>();
    private int idCounter = 1;

    @GetMapping
    public List<Todo> getAllTodos() {
        return todos;
    }

    @GetMapping("/{id}")
    public Todo getTodoById(@PathVariable int id) {
        System.out.println("CI/CD test push");
        return todos.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public Todo createTodo(@RequestBody Todo todo) {
        todo.setId(idCounter++);
        todos.add(todo);
        return todo;
    }

    @PutMapping("/{id}")
    public Todo updateTodo(@PathVariable int id, @RequestBody Todo updatedTodo) {
        for (Todo todo : todos) {
            if (todo.getId() == id) {
                todo.setTask(updatedTodo.getTask());
                todo.setCompleted(updatedTodo.isCompleted());
                return todo;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String deleteTodo(@PathVariable int id) {
        boolean removed = todos.removeIf(t -> t.getId() == id);
        return removed ? "Todo deleted successfully" : "Todo not found";
    }
}
