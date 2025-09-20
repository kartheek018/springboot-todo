package com.sample.angular.service;

import com.sample.angular.model.Todo;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    private final List<Todo> todos = Arrays.asList(
            new Todo(1, "Buy groceries", false),
            new Todo(2, "Finish homework", true),
            new Todo(3, "Call mom", false),
            new Todo(4, "Clean the house", true),
            new Todo(5, "Read a book", false)
    );

    public List<Todo> findAllTodo(){
        return todos;
    }

    public Optional<Todo> findById(int id){
        return todos.stream()
                .filter(todo->todo.getId()==id)
                .findFirst();
    }
}
