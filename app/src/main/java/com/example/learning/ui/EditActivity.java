package com.example.learning.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.learning.R;
import com.example.learning.entity.Task;
import com.example.learning.viewmodel.EditViewModel;
import com.example.learning.viewmodel.TaskViewModel;

import java.util.List;

public class EditActivity extends AppCompatActivity {
    EditViewModel editViewModel;
private  Bundle bundle;
private String taskid;
private EditText updateet;
private LiveData<Task> task;

public static final String TASK_ID="task_id";
public static final String UPDATED_TASK="task_text";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        updateet = findViewById(R.id.edit);
        Button editbtn = findViewById(R.id.update);
        Button cancelbtn = findViewById(R.id.cancel);

        bundle=getIntent().getExtras();
        if(bundle!=null){
            taskid=bundle.getString("task_id");
        }


        editViewModel = new ViewModelProvider(this).get(EditViewModel.class);

        task=editViewModel.getTaskForUpdate(taskid);
        task.observe(this, new Observer<Task>() {
            @Override
            public void onChanged(Task task) {
                updateet.setText(task.getTask());
            }
        });

        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTask(v);
            }
        });
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelTask(v);
            }
        });
    }


    private  void updateTask(View view){
        String updateTask=updateet.getText().toString();
        Intent result=new Intent();
        result.putExtra(TASK_ID,taskid);
        result.putExtra(UPDATED_TASK,updateTask);
        setResult(RESULT_OK,result);
        finish();
    }

    private  void cancelTask(View view){
        finish();
    }
}