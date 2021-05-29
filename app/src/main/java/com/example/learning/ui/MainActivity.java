package com.example.learning.ui;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learning.R;
import com.example.learning.entity.Task;
import com.example.learning.ui.adapter.RecyclerViewAdaptor;
import com.example.learning.viewmodel.TaskViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.UUID;


public class MainActivity extends AppCompatActivity implements RecyclerViewAdaptor.OnDeleteClickListener {
  public  static  final int NEW_TASK_ACTIVITY_REQUEST_CODE=1;
    public  static  final int UPDATE_TASK_ACTIVITY_REQUEST_CODE=2;
  private TaskViewModel taskViewModel;
    private RecyclerViewAdaptor recyclerViewAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       //set recyclerview data
       RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerViewAdaptor = new RecyclerViewAdaptor(this, this);
        recyclerView.setAdapter(recyclerViewAdaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        FloatingActionButton add=findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
Intent intent=new Intent(MainActivity.this,Add_task_activity.class);
startActivityForResult(intent,NEW_TASK_ACTIVITY_REQUEST_CODE);

                          }
        });


        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);


        taskViewModel.getnAlltasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                recyclerViewAdaptor.setTasks(tasks);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==NEW_TASK_ACTIVITY_REQUEST_CODE && resultCode==RESULT_OK)
        {
            //insert code
            final String task_id = UUID.randomUUID().toString();
                        Task task=new Task(task_id,data.getStringExtra(Add_task_activity.NOTE_ADDED));
            taskViewModel.insert(task);

            Toast.makeText(getApplicationContext(),"note added succesfully",Toast.LENGTH_SHORT).show();
        }
        else if(requestCode==UPDATE_TASK_ACTIVITY_REQUEST_CODE && resultCode==RESULT_OK)
        {
            Task task=new Task(
                    data.getStringExtra(EditActivity.TASK_ID),
                    data.getStringExtra(EditActivity.UPDATED_TASK));
            taskViewModel.update(task);
            Toast.makeText(getApplicationContext(),"note succesfully updated",Toast.LENGTH_SHORT).show();

        }

        else {
            Toast.makeText(getApplicationContext(),"failed",Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void OnDeleteClickListener(Task mytask) {
        //delete
                taskViewModel.delete(mytask);
    }
}