package com.example.timedrive.today.maincode;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.example.timedrive.database.code.TaskTimeComparator;
import com.example.timedrive.today.recyclerview.recyclerAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.ExecutionException;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

public class TodayFragment extends Fragment {
    private TaskBase db;
    private RecyclerView recyclerView;
    private ArrayList<Task> cash;
    private ImageButton add;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_today, container, false);
        refill();
        recyclerView = root.findViewById(R.id.recyclerViewInToday);
        setAdapter();
        add = root.findViewById(R.id.plus_post);
        add.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), AddActivity.class);
            intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP);
            int somethingUseless = 1;

            startActivityForResult(intent, somethingUseless);

        });
        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("DONE", "onActivityResult: ");
        refill();
        setAdapter();
    }

    private void setAdapter() {
        recyclerAdapter adapter = new recyclerAdapter(cash);
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void refill() {
        AsyncAll getter = new AsyncAll(this.getContext());
        getter.execute();
        try {
            cash = getter.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        Comparator taskTimeComparator = new TaskTimeComparator();
        Collections.sort(cash, taskTimeComparator);

    }
}

