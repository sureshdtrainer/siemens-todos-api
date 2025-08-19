package com.siemens.todos_api.controllers;

import com.siemens.todos_api.exceptions.ResourceNotFoundException;
import com.siemens.todos_api.models.Todo;
import com.siemens.todos_api.services.TodosService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodosController {

    private static final Logger logger = LoggerFactory.getLogger(TodosController.class);
    @Autowired
    private TodosService todosService;

    @GetMapping("todos")
    public List<Todo> getAllTodos(){
        return todosService.getAllTodos();
    }

    @GetMapping("todos/{id}")
    public Todo getTodoById(@PathVariable int id){
        try {
            return todosService.getTodoById(id);
        }catch(ResourceNotFoundException resourceNotFoundException){
            logger.warn(resourceNotFoundException.getMessage());
            throw resourceNotFoundException;
        }
    }

    @PostMapping("/todos")
    public Todo saveTodo(@RequestBody Todo todo){
        Todo newTodo= todosService.saveTodo(todo);
        return todo;
    }

}
