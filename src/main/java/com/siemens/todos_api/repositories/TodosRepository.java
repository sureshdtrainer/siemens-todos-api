package com.siemens.todos_api.repositories;

import com.siemens.todos_api.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodosRepository extends JpaRepository<Todo, Integer> {
}
