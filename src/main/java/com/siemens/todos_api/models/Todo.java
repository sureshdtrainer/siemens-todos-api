package com.siemens.todos_api.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Todo {
    private int id;
    private String description;
    private Date targetDate;
    private boolean isDone;
}
