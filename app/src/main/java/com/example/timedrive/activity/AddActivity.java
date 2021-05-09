package com.example.timedrive.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.timedrive.R;
import com.example.timedrive.database.asks.AsyncAdd;
import com.example.timedrive.database.code.Task;

import java.util.concurrent.ExecutionException;

public class AddActivity extends AppCompatActivity {

    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);
        button.setOnClickListener(v->{
            String desc = editText.getText().toString();
            Task task = new Task(desc, 0, 228);
            AsyncAdd adder = new AsyncAdd(getApplicationContext());
            adder.execute(task);
            try {
                adder.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }


            this.finish();
        });
    }
}