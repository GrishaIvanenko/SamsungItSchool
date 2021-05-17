package com.example.timedrive.week.maincode;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timedrive.R;
import com.example.timedrive.activity.AddActivity;
import com.example.timedrive.database.asks.AsyncAllWithDate;
import com.example.timedrive.database.code.Task;
import com.example.timedrive.database.code.TaskBase;
import com.example.timedrive.extra.TaskTimeComparator;
import com.example.timedrive.extra.Helper;
import com.example.timedrive.week.recyclerview.recyclerAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.ExecutionException;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

public class WeekFragment extends Fragment {
    private TaskBase db;
    private RecyclerView recyclerView;
    private ArrayList<Task> cash;
    private ImageButton add;
    private ProgressBar progressBar;
    private TextView progress;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_week, container, false);

        progressBar = root.findViewById(R.id.progressBarWeek);
        progress = root.findViewById(R.id.textViewWeekProgress);

        refill();
        recyclerView = root.findViewById(R.id.recyclerViewInWeek);
        setAdapter();
        add = root.findViewById(R.id.plus_post_week);
        add.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), AddActivity.class);
            intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("Edit", "0");
            int somethingUseless = 1;
            startActivityForResult(intent, somethingUseless);
        });

        Helper.setup_progress(cash, progressBar, progress, getContext());

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("DONE", "onActivityResult: ");
        refill();
        setAdapter();
    }

    private void setAdapter() {
        recyclerAdapter adapter = new recyclerAdapter(cash, getContext(), progressBar, progress, this);
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void refill() {
        AsyncAllWithDate getter = new AsyncAllWithDate(this.getContext());
        getter.execute(Helper.getLongWeekBegin(), Helper.getLongWeekEnd());
        try {
            cash = getter.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        Comparator taskTimeComparator = new TaskTimeComparator();
        if (cash != null)
            Collections.sort(cash, taskTimeComparator);
        else
            cash = new ArrayList<>();

    }

    public void callEdit(Task task) {
        Intent intent = new Intent(getContext(), AddActivity.class);
        intent.putExtra("Edit", "1");
        intent.putExtra("TaskData", task.toString());
        intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP);
        int somethingUseless = 1;
        startActivityForResult(intent, somethingUseless);
    }
}

