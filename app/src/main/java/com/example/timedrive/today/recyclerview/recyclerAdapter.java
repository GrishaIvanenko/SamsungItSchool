package com.example.timedrive.today.recyclerview;

import android.util.Log;
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
        String stringTime = parce_time(integerTime);
        String scoreTitle = stringTime + ", " + title;
        holder.textViewTitle.setText(scoreTitle);

        String description = taskArrayList.get(position).getDescription();
        holder.textViewDescription.setText(description);

    }


    private String parce_time(Integer time) {
        String TAG = "parcing time";
        if (time < 0 | time >= 60 * 24) {
            Log.wtf(TAG, "parce_time Error: time = " + time.toString() + " BUT time must be [0; 24 * 60 * 60)!!!");
            assert false;
        }
        Integer hours = time / 60;
        Integer minutes = time % 60;
        String ans;
        if (minutes < 10)
            ans = hours.toString() + ":0" + minutes.toString();
        else
            ans = hours.toString() + ":" + minutes.toString();
        Log.wtf(TAG, "parce_time: " + ans + "; -OK!");
        return ans;
    }

    @Override
    public int getItemCount() {
        return taskArrayList.size();
    }
}
