package com.siemens.todos_api.services;

import com.siemens.todos_api.models.Todo;

import java.util.List;

public interface TodosService {

    Todo saveTodo(Todo todo);

    List<Todo> getAllTodos();

    Todo getTodoById(int id);

    Todo updateTodo(int id, Todo todo);

    boolean deleteTodo(int id);
}
