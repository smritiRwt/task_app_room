package com.example.learning.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.learning.dao.TaskDao;
import com.example.learning.entity.Task;
@Database(entities = { Task.class }, version = 1, exportSchema = false)
 public abstract class TaskDatabase extends RoomDatabase {


  //list of db access object
      public  abstract TaskDao taskdao();

      //db instance
      private  static  volatile TaskDatabase taskDatabaseinstance;

  public static TaskDatabase getTaskDatabase(final Context context){
    if(taskDatabaseinstance==null)
    {
      synchronized (TaskDatabase.class){
        if(taskDatabaseinstance==null)
        {
          taskDatabaseinstance= Room.databaseBuilder(context.getApplicationContext()
                  ,TaskDatabase.class,
                  "task_database").build();
        }
      }
    }
    return  taskDatabaseinstance;
  }

}
