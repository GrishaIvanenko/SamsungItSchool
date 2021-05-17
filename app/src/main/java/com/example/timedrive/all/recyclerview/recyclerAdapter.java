package com.example.timedrive.all.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timedrive.R;
import com.example.timedrive.all.maincode.AllFragment;
import com.example.timedrive.database.asks.AsyncUpdate;
import com.example.timedrive.database.code.Task;
import com.example.timedrive.extra.Helper;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder> {

    private ArrayList<Task> taskArrayList;
    private Context context;
    private ProgressBar progressBar;
    private TextView progressText;
    private AllFragment myFrag;

    public recyclerAdapter(ArrayList<Task> input, Context context,
                           ProgressBar progressBar, TextView progressText, AllFragment fragment) {
        taskArrayList = input;
        this.context = context;
        this.progressBar = progressBar;
        this.progressText = progressText;
        this.myFrag = fragment;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewTitle;
        private final TextView textViewDescription;
        private final CheckBox checkBoxer;
        private final AppCompatImageButton icon;
        private final AppCompatImageButton edit;
        public MyViewHolder (final View view) {
            super(view);
            textViewTitle = view.findViewById(R.id.textViewTitle);
            textViewDescription = view.findViewById(R.id.textViewDescription);
            checkBoxer = view.findViewById(R.id.checkBox);
            icon = view.findViewById(R.id.itemIcon);
            edit = view.findViewById(R.id.edit_button);
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
        String date = Helper.getStringDateWithFeatures(taskArrayList.get(position).getDate());
        String scoreTitle = date + ", " + title;
        holder.textViewTitle.setText(scoreTitle);
        String stringTime = Helper.parce_time(integerTime);
        String res = stringTime + ", " + taskArrayList.get(position).getDescription();
        holder.textViewDescription.setText(res);
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

        holder.icon.setImageResource(Helper.iconById(taskArrayList.get(position).getMyPicture()));

        holder.edit.setOnClickListener(v-> {
            myFrag.callEdit(taskArrayList.get(position));
        });
    }


    @Override
    public int getItemCount() {
        return taskArrayList.size();
    }
}
