package com.example.learning.viewmodel;
import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.learning.dao.TaskDao;
import com.example.learning.database.TaskDatabase;
import com.example.learning.entity.Task;

import java.util.List;

public class EditViewModel extends AndroidViewModel {

    private  String tag=this.getClass().getSimpleName();
    private TaskDao taskDao;
    private TaskDatabase db;


    public EditViewModel(@NonNull Application application) {
        super(application);
        Log.d(tag,"edit viewmodel");
        db=TaskDatabase.getTaskDatabase(application);
        taskDao =db.taskdao();
    }


    public LiveData<Task> getTaskForUpdate(String task_id){
        return  taskDao.getTasksForUpdate(task_id);
    }


}
