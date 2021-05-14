package com.example.timedrive.week.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timedrive.R;
import com.example.timedrive.database.code.Task;
import com.example.timedrive.extra.Helper;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder> {

    private ArrayList<Task> taskArrayList;

    public recyclerAdapter(ArrayList<Task> input) {
        taskArrayList = input;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewTitle;
        private final TextView textViewDescription;
        public MyViewHolder (final View view) {
            super(view);
            textViewTitle = view.findViewById(R.id.textViewTitle);
            textViewDescription = view.findViewById(R.id.textViewDescription);
        }
    }

    @NonNull
    @Override
    public recyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.task_item_decor, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerAdapter.MyViewHolder holder, int position) {
        String title = taskArrayList.get(position).getTitle();
        Integer integerTime = taskArrayList.get(position).getTime();
        String stringTime = Helper.parce_time(integerTime);
        Long date = taskArrayList.get(position).getDate();
        String stringDate = Helper.parceDate(date);
        String scoreTitle = stringDate + ", " + title;
        holder.textViewTitle.setText(scoreTitle);
        String description = taskArrayList.get(position).getDescription();
        String scoreDescription = stringTime + ", " + description;
        holder.textViewDescription.setText(scoreDescription);
    }


    @Override
    public int getItemCount() {
        return taskArrayList.size();
    }
}
