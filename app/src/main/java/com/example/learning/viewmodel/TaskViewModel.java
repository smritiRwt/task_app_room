package com.example.learning.viewmodel;

import android.app.Application;
import android.net.LinkAddress;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.learning.dao.TaskDao;
import com.example.learning.database.TaskDatabase;
import com.example.learning.entity.Task;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {

    private String Tag=this.getClass().getSimpleName();

    private TaskDao taskDao;
    private TaskDatabase taskDatabase;
    private LiveData<List<Task>> nAlltasks;

    public TaskViewModel(@NonNull Application application) {
        super(application);

        taskDatabase=TaskDatabase.getTaskDatabase(application);
        taskDao=taskDatabase.taskdao();
        nAlltasks=taskDao.getTasks();

    }

 public  void insert(Task task)
 {
     new InsertAsyncTask(taskDao).execute(task);
 }
//select data

   public LiveData<List<Task>> getnAlltasks(){
        return  nAlltasks;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.i(Tag,"Viewmodel destroyed");
    }


    private static class InsertAsyncTask extends AsyncTask<Task,Void,Void>{
            TaskDao mtaskdao;

        public InsertAsyncTask(TaskDao mtaskdao) {
            this.mtaskdao = mtaskdao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            mtaskdao.insert(tasks[0]);
            return null;
        }
    }
    //update

    public  void update(Task task){
        new UpdateAsyncTask(taskDao).execute(task);
    }
  //delete
    public  void delete(Task task){
        new DeleteAsyncTask(taskDao).execute(task);
    }


    private static class UpdateAsyncTask extends AsyncTask<Task,Void,Void> {
        TaskDao mtaskdao;

        public UpdateAsyncTask(TaskDao mtaskdao) {
            this.mtaskdao = mtaskdao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            mtaskdao.update(tasks[0]);
            return null;
        }
    }



    private static class DeleteAsyncTask extends AsyncTask<Task,Void,Void> {
        TaskDao mtaskdao;

        public DeleteAsyncTask(TaskDao mtaskdao) {
            this.mtaskdao = mtaskdao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            mtaskdao.delete(tasks[0]);
            return null;
        }
    }
}
