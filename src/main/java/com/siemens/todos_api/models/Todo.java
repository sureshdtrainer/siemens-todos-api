package com.siemens.todos_api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="todos")
public class Todo {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name="description", nullable = false)
    @NotNull
    @Size(min=3, message="Enter atleast 3 characters")
    private String description;
    @NotNull
    private Date targetDate;
    private boolean isDone;
}
