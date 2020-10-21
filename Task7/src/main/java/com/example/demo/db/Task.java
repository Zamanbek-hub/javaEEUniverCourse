package com.example.demo.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    private Long id;
    private String name;
    private String description;
    private Date deadlineDate;
    private boolean isCompleted;

    public Task(String name, String description, Date deadlineDate, boolean isCompleted) {
        this.name = name;
        this.description = description;
        this.deadlineDate = deadlineDate;
        this.isCompleted = isCompleted;
    }
}
