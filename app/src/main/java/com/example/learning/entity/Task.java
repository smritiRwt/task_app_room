package com.example.learning.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tasks")
public class Task {

    @PrimaryKey
    @NonNull
    private  String id;

    @NonNull
    @ColumnInfo(name="task")
    private  String task;

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public void setTask(@NonNull String task) {
        this.task = task;
    }

    @NonNull
    public String getTask() {
        return this.task;
    }

    public Task(String id, String task){
        this.id=id;
        this.task=task;
        }
}
