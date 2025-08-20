package com.siemens.todos_api.services.impl;

import com.siemens.todos_api.controllers.TodosController;
import com.siemens.todos_api.exceptions.ResourceNotFoundException;
import com.siemens.todos_api.models.Todo;
import com.siemens.todos_api.repositories.TodosRepository;
import com.siemens.todos_api.services.TodosService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodosServiceDBImpl implements TodosService {

    private static final Logger logger = LoggerFactory.getLogger(TodosServiceDBImpl.class);
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
        logger.info("Inside UpdateTodo");
        Todo existingTodo = todosRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Todos with id "+ id+ " Not Found"));

        existingTodo.setTargetDate(todo.getTargetDate());
        existingTodo.setDone(todo.isDone());
        //save to db
        todosRepository.save(existingTodo);
        logger.info(existingTodo.toString());
        return existingTodo;
    }

    @Override
    public boolean deleteTodo(int id) {
        todosRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Todos with id "+ id+ " Not Found"));
        todosRepository.deleteById(id);
        return true;
    }
}
