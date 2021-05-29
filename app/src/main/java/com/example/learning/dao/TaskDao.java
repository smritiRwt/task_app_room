package com.example.learning.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.learning.entity.Task;

import java.util.List;

@Dao
public interface TaskDao {
    @Insert
    void insert(Task task);
//fetch
    @Query("SELECT * FROM tasks")
    LiveData<List<Task>> getTasks();


   //update

   @Query("SELECT * FROM tasks WHERE id =:task_id")
   LiveData<Task> getTasksForUpdate(String task_id);


   @Update
    void update(Task task);

   //delete
    @Delete
    int delete(Task task);

}
