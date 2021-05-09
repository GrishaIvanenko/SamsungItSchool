package com.example.timedrive.today.maincode;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timedrive.R;
import com.example.timedrive.activity.AddActivity;
import com.example.timedrive.database.asks.AsyncAll;
import com.example.timedrive.database.code.Task;
import com.example.timedrive.database.code.TaskBase;
import com.example.timedrive.today.recyclerview.recyclerAdapter;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class TodayFragment extends Fragment {
    private TaskBase db;
    private RecyclerView recyclerView;
    private ArrayList<Task> cash;
    private ImageButton add;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_today, container, false);
        AsyncAll getter = new AsyncAll(this.getContext());
        getter.execute();
        try {
            cash = getter.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        recyclerView = root.findViewById(R.id.recyclerViewInToday);
        setAdapter();
        add = root.findViewById(R.id.plus_post);
        add.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), AddActivity.class);
            startActivity(intent);
        });
        return root;
    }

    private void setAdapter() {
        recyclerAdapter adapter = new recyclerAdapter(cash);
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
}

