package com.example.timedrive.today.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timedrive.R;
import com.example.timedrive.database.asks.AsyncUpdate;
import com.example.timedrive.database.code.Task;
import com.example.timedrive.extra.Helper;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder> {

    private ArrayList<Task> taskArrayList;
    private Context context;
    private ProgressBar progressBar;
    private TextView progressText;

    public recyclerAdapter(ArrayList<Task> input, Context context,
                           ProgressBar progressBar, TextView progressText) {
        taskArrayList = input;
        this.context = context;
        this.progressBar = progressBar;
        this.progressText = progressText;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewTitle;
        private final TextView textViewDescription;
        private final CheckBox checkBoxer;
        public MyViewHolder (final View view) {
            super(view);
            textViewTitle = view.findViewById(R.id.textViewTitle);
            textViewDescription = view.findViewById(R.id.textViewDescription);
            checkBoxer = view.findViewById(R.id.checkBox);
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
        String scoreTitle = stringTime + ", " + title;
        holder.textViewTitle.setText(scoreTitle);
        String description = taskArrayList.get(position).getDescription();
        holder.textViewDescription.setText(description);
        holder.checkBoxer.setOnClickListener(v-> {
            if (holder.checkBoxer.isChecked()) {
                taskArrayList.get(position).setDone(true);
                holder.checkBoxer.setChecked(true);
            } else {
                taskArrayList.get(position).setDone(false);
                holder.checkBoxer.setChecked(false);
            }
            Helper.setup_progress(taskArrayList, progressBar, progressText);
            Task prev = taskArrayList.get(position);
            AsyncUpdate rab = new AsyncUpdate(context);
            rab.execute(prev);
        });

        if (taskArrayList.get(position).getDone() == true) {
            holder.checkBoxer.setChecked(true);
        } else {
            holder.checkBoxer.setChecked(false);
        }
    }


    @Override
    public int getItemCount() {
        return taskArrayList.size();
    }
}
