package com.example.timedrive.activity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;

import com.example.timedrive.R;
import com.example.timedrive.database.asks.AsyncAdd;
import com.example.timedrive.database.code.Task;
import com.google.android.material.textview.MaterialTextView;

import java.util.Calendar;
import java.util.concurrent.ExecutionException;

public class AddActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    private AppCompatImageButton exiter;
    private AppCompatImageButton iconSelector;
    private AppCompatImageButton iconColor;
    private MaterialTextView myTime;
    private AppCompatButton finishHin;
    private EditText myTitle;
    private  EditText myDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        exiter = findViewById(R.id.exit_add_button);
        exiter.setOnClickListener(v -> {
            this.finish();
        });

        iconSelector = findViewById(R.id.icon);
        iconSelector.setImageResource(R.drawable.ic_gym);
        iconSelector.setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "Coming soon!", Toast.LENGTH_SHORT).show();
        });

        myTime = findViewById(R.id.textViewTime);
        myTime.setOnClickListener(v -> {
            TimePickerDialog timePicker = new TimePickerDialog(
                    AddActivity.this, this,
                    Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
                    Calendar.getInstance().get(Calendar.MINUTE), true);
            timePicker.show();
        });

        myTitle = findViewById(R.id.textViewTitle);

        myDescription = findViewById(R.id.textViewDescription);

        finishHin = findViewById(R.id.add_button);
        finishHin.setOnClickListener(v -> {
            String title = myTitle.getText().toString();
            String description = myDescription.getText().toString();
            String stringTime = myTime.getText().toString();
            Integer time = fromStringTimeToIntegerTime(stringTime);
            Task task = new Task(title, description, 0, time, false, 0);
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

    private Integer fromStringTimeToIntegerTime(String s) {
        String[] strings = s.split(":");
        Integer hours = Integer.parseInt(strings[0]);
        Integer minutes = Integer.parseInt(strings[1]);
        Integer result = hours * 60 + minutes;
        return result;
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar.getInstance().set(Calendar.HOUR_OF_DAY, hourOfDay);
        Calendar.getInstance().set(Calendar.MINUTE, minute);
        String hours = Integer.toString(hourOfDay);
        String minutes;
        if (minute < 10)
            minutes = "0" + Integer.toString(minute);
        else
            minutes = Integer.toString(minute);
        String res = hours + ":" + minutes;
        myTime.setText(res);
    }
}