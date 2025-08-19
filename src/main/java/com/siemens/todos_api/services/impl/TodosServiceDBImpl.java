package com.siemens.todos_api.services.impl;

import com.siemens.todos_api.exceptions.ResourceNotFoundException;
import com.siemens.todos_api.models.Todo;
import com.siemens.todos_api.repositories.TodosRepository;
import com.siemens.todos_api.services.TodosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodosServiceDBImpl implements TodosService {

    @Autowired
    private TodosRepository todosRepository;

    @Override
    public Todo saveTodo(Todo todo) {
       return todosRepository.save(todo);
    }

    @Override
    public List<Todo> getAllTodos() {
        return todosRepository.findAll();
    }

    @Override
    public Todo getTodoById(int id) {
        Optional<Todo> todo = todosRepository.findById(id);
        if(todo.isPresent())
            return todo.get();
        throw new ResourceNotFoundException("Todos with id "+ id+ " Not Found");
    }

    @Override
    public Todo updateTodo(int id, Todo todo) {
        return null;
    }

    @Override
    public boolean deleteTodo(int id) {
        return false;
    }
}
