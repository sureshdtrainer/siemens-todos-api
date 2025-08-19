package com.siemens.todos_api.services.impl;

import com.siemens.todos_api.exceptions.ResourceNotFoundException;
import com.siemens.todos_api.models.Todo;
import com.siemens.todos_api.services.TodosService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TodosServiceInMemoryImpl implements TodosService {
    private static List<Todo> todos = new ArrayList<>();
    private static int todosCount=3;

    static{
        todos.add(new Todo(1, "Learn Spring Boot",new Date(), false));
        todos.add(new Todo(2, "Learn Spring JPA",new Date(), false));
        todos.add(new Todo(3, "Learn Spring Security",new Date(), false));
    }

    @Override
    public Todo saveTodo(Todo todo) {
       todo.setId(++todosCount);
       todos.add(todo);
       return todo;
    }

    @Override
    public List<Todo> getAllTodos() {
        return todos;
    }

    @Override
    public Todo getTodoById(int id) throws ResourceNotFoundException {
        for(Todo todo : todos){
            if(todo.getId() == id){
                return todo;
            }
        }
        throw new ResourceNotFoundException("Todos with id "+ id+ " Not Found");
    }
}
