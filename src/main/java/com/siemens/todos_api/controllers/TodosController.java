package com.siemens.todos_api.controllers;

import com.siemens.todos_api.exceptions.ResourceNotFoundException;
import com.siemens.todos_api.models.Todo;
import com.siemens.todos_api.services.TodosService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
public class TodosController {

    private static final Logger logger = LoggerFactory.getLogger(TodosController.class);
    @Autowired
    private TodosService todosService;

    @GetMapping
    public List<Todo> getAllTodos(){
        logger.info("Inside getAllTodos method");
        return todosService.getAllTodos();
    }

    @GetMapping("{id}")
    public Todo getTodoById(@PathVariable int id){
        try {
            return todosService.getTodoById(id);
        }catch(ResourceNotFoundException resourceNotFoundException){
            logger.warn(resourceNotFoundException.getMessage());
            throw resourceNotFoundException;
        }
    }

    @PostMapping
    public ResponseEntity<Todo> saveTodo(@RequestBody Todo todo){
        Todo newTodo= todosService.saveTodo(todo);
        if(newTodo==null){
            return  ResponseEntity.noContent().build();
        }
        return new ResponseEntity<Todo>(newTodo, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable int id,@RequestBody Todo todo){
        Todo updatedTodo = todosService.updateTodo(id, todo);
        return new ResponseEntity<Todo>(updatedTodo, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public boolean deleteTodo(@PathVariable int id){
        boolean result = todosService.deleteTodo(id);
        if(!result){
            throw new ResourceNotFoundException("Todos with id "+ id+ " Not Found");
        }
        return result;
    }

}
