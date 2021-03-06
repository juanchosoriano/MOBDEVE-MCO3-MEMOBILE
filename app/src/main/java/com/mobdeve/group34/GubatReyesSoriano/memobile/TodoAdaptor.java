package com.mobdeve.group34.GubatReyesSoriano.memobile;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.List;

public class TodoAdaptor extends RecyclerView.Adapter {

    List<TodoModel> todoModelList;
    TodoClickListener todoClickListener;
    //TodoCheckListener todoCheckListener;
    public TodoAdaptor(List<TodoModel> todoModelList, TodoClickListener todoClickListener){
        this.todoModelList = todoModelList;
        this.todoClickListener = todoClickListener;
        //this.todoClickListener
    }

    public static class TodoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public CheckBox checkBox;
        public LinearLayout todoLayout;
        ItemClickListener itemClickListener;
        public ConstraintLayout cl_todo;
        public TextView tvDate;

        public TodoViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.cb_item);
            todoLayout = itemView.findViewById(R.id.ll_todo);
            cl_todo = itemView.findViewById(R.id.cl_todo);
            tvDate = itemView.findViewById(R.id.tv_todo_date);
        }
        public void setItemClickListener(ItemClickListener ic)
        {
            this.itemClickListener=ic;
        }
        @Override
        public void onClick(View v) {
            this.itemClickListener.onItemClick(v,getLayoutPosition());
        }

        interface ItemClickListener {

            void onItemClick(View v,int pos);
        }
    }
    @NonNull
    @NotNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_row, null);
        return new TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        TodoViewHolder todoViewHolder = (TodoViewHolder) holder;
        todoViewHolder.checkBox.setText(todoModelList.get(position).getTodo_Text());
        String schedule_date = new SimpleDateFormat("MMM dd yyyy").format(todoModelList.get(position).getTodo_date());
        todoViewHolder.tvDate.setText(schedule_date);
        Log.d("ITEMCHECKED", ""+ todoModelList.get(position).isChecked());
        todoViewHolder.checkBox.setChecked(todoModelList.get(position).isChecked());
        int priority_color = todoModelList.get(position).getPriority();
        Log.d("PRIOR_C", ""+priority_color);
        if(priority_color == 1){
            Log.d("color", "in prior 1");
            todoViewHolder.cl_todo.getBackground().setTint(Color.argb(100, 227, 130, 130));
        } else if (priority_color == 2){
            todoViewHolder.cl_todo.getBackground().setTint(Color.argb(100, 237, 216, 149));

        } else {
            todoViewHolder.cl_todo.getBackground().setTint(Color.argb(100, 152, 213, 135));

        }

        todoViewHolder.todoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //CheckBox mycheckbox = (CheckBox) v;
                todoClickListener.onClickItem(todoModelList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return todoModelList.size();
    }
}
