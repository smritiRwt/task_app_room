package com.example.learning.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learning.R;
import com.example.learning.entity.Task;
import com.example.learning.ui.EditActivity;
import com.example.learning.ui.MainActivity;

import java.util.List;

public class RecyclerViewAdaptor extends RecyclerView.Adapter<RecyclerViewAdaptor.RecyclerviewViewHolder> {


    public interface OnDeleteClickListener{
        void OnDeleteClickListener(Task mytask);
    }


    private  OnDeleteClickListener onDeleteClickListener;

    private List<Task> mtasks;

    private final LayoutInflater layoutInflater;
    private Context mContext;

    public RecyclerViewAdaptor(Context context,OnDeleteClickListener listener) {
        layoutInflater = LayoutInflater.from(context);
        mContext = context;
        this.onDeleteClickListener=listener;
    }



    @NonNull
    @Override
    public RecyclerviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View itemview=layoutInflater.inflate(R.layout.rv_items,parent,false);
       RecyclerviewViewHolder viewHolder=new RecyclerviewViewHolder(itemview);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerviewViewHolder holder, int position) {
if(mtasks!=null)
{
    Task task=mtasks.get(position);
    holder.setData(task.getTask(),position);
    holder.setListeners();
}else {
    holder.taskItemView.setText("no task found");
}
    }

    @Override
    public int getItemCount() {
    if(mtasks !=null){
        return  mtasks.size();
        }
    else{
      return  0;
        }

    }

    public  void  setTasks(List<Task> tasks){
        mtasks=tasks;
        notifyDataSetChanged();
    }


    public class RecyclerviewViewHolder  extends  RecyclerView.ViewHolder{

        private TextView taskItemView;
        private int pos;
        private ImageView delete, edit;

        public RecyclerviewViewHolder(View itemView) {
            super(itemView);
            taskItemView = itemView.findViewById(R.id.txvNote);
            delete 	 = itemView.findViewById(R.id.ivRowDelete);
            edit 	 = itemView.findViewById(R.id.ivRowEdit);
        }

        public void setData(String note, int position) {
            taskItemView.setText(note);
            pos = position;
        }
        public  void setListeners(){
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(mContext, EditActivity.class);
                    intent.putExtra("task_id",mtasks.get(pos).getId());
                    ((Activity)mContext).startActivityForResult(intent,MainActivity.UPDATE_TASK_ACTIVITY_REQUEST_CODE);

                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(onDeleteClickListener!=null){
                        onDeleteClickListener.OnDeleteClickListener(mtasks.get(pos));
                    }
                       }
            });
        }
    }
}
