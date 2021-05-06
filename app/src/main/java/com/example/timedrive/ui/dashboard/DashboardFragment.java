package com.example.timedrive.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.timedrive.R;
import com.example.timedrive.database.asks.AsyncAdd;
import com.example.timedrive.database.asks.AsyncAll;
import com.example.timedrive.database.code.Task;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class DashboardFragment extends Fragment {
    private ArrayList<Task> cash;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        AsyncAll getter = new AsyncAll(this.getContext());
        getter.execute();
        try {
            cash = getter.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        AsyncAdd gg = new AsyncAdd(getContext());
        gg.execute(new Task("biba", cash.size() * 10, 2));
        return root;
    }
}