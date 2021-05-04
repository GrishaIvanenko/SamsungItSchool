package com.example.timedrive.today.recyclerview;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timedrive.R;
import com.example.timedrive.database.code.Task;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder> {

    private ArrayList<Task> taskArrayList;

    public recyclerAdapter(ArrayList<Task> input) {
        taskArrayList = input;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewTime;
        private final TextView textViewDescription;
        private final View layoutTaskView;

        public MyViewHolder (final View view) {
            super(view);
            textViewTime = view.findViewById(R.id.textViewTime);
            textViewDescription = view.findViewById(R.id.textViewDescription);
            layoutTaskView = view.findViewById(R.id.layoutTask);
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
       Integer time = taskArrayList.get(position).getTime();
       String parced_time = parce_time(time);
       holder.textViewTime.setText(parced_time);
       String Description = taskArrayList.get(position).getDescription();
       holder.textViewDescription.setText(Description);
       Integer prior = taskArrayList.get(position).getPriority();
       Integer nprior = parce_prior(prior);
       holder.layoutTaskView.setBackgroundColor(nprior);
    }

    private Integer parce_prior(Integer prior) {
        Integer res = prior * 1000;
        return res;
    }

    private String parce_time(Integer time) {
        Integer ntime = time % (24 * 60);
        Integer hours = ntime / 60;
        Integer minutes = ntime % 60;
        String ans = hours.toString() + ":" + minutes.toString();
        return ans;
    }

    @Override
    public int getItemCount() {
        return taskArrayList.size();
    }
}
