package com.example.timedrive.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;

import com.example.timedrive.R;

public class AddActivity extends AppCompatActivity {

    //private EditText editText;
    //private Button button;
    private AppCompatImageButton exiter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        exiter = findViewById(R.id.exit_add_button);
        exiter.setOnClickListener(v -> {
            this.finish();
        });

        /*editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);
        button.setOnClickListener(v->{
            String desc = editText.getText().toString();
            Task task = new Task(desc, "fool", 1, 1400, true, 3);
            AsyncAdd adder = new AsyncAdd(getApplicationContext());
            adder.execute(task);
            try {
                adder.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }


            this.finish();
        });*/
    }
}