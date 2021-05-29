package com.example.learning.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.ParcelUuid;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.learning.R;

import javax.xml.transform.Result;

public class Add_task_activity extends AppCompatActivity {
public  static  final String NOTE_ADDED="NEW_NOTE";
    private EditText addnote;
    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task_activity);

        addnote= findViewById(R.id.note);
        add=findViewById(R.id.addnote);
                add.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                            Intent result=new Intent();
                            if(TextUtils.isEmpty(addnote.getText())){
                            setResult(RESULT_CANCELED,result);
                            }
                            else{
                                String note=addnote.getText().toString();
                                result.putExtra(NOTE_ADDED,note);
                                setResult(RESULT_OK, result);
                            }
                            finish();
                     }
                    });
          }


}