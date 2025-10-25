package com.sample.angular.service;

import com.sample.angular.model.Todo;
import com.sample.angular.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> findAllTodo(){
        return todoRepository.findAll();
    }

    public Optional<Todo> findById(int id){
        return todoRepository.findById(id);
    }

    public void saveTodo(Todo todo){
        todoRepository.save(todo);
    }

    public void deleteById(int id){
        todoRepository.deleteById(id);
    }
}
