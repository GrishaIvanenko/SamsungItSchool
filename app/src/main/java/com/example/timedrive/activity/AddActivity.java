package com.example.timedrive.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;

import com.example.timedrive.R;
import com.example.timedrive.database.asks.AsyncAdd;
import com.example.timedrive.database.code.Task;
import com.example.timedrive.extra.Helper;
import com.google.android.material.textview.MaterialTextView;

import java.util.Calendar;
import java.util.concurrent.ExecutionException;

public class AddActivity extends AppCompatActivity implements
        TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    private AppCompatImageButton iconSelector;
    private AppCompatImageButton iconColor;
    private MaterialTextView myTime;
    private MaterialTextView myDate;
    private AppCompatButton finishHin;
    private EditText myTitle;
    private  EditText myDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        iconSelector = findViewById(R.id.icon);
        iconSelector.setImageResource(R.drawable.ic_gym);
        iconSelector.setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "Coming soon!", Toast.LENGTH_SHORT).show();
        });

        myTime = findViewById(R.id.textViewTime);
        myTime.setText(Helper.parce_time(
                Calendar.getInstance().get(Calendar.HOUR_OF_DAY) * 60 +
                        Calendar.getInstance().get(Calendar.MINUTE)
        ));

        myTime.setOnClickListener(v -> {
            TimePickerDialog timePicker = new TimePickerDialog(
                    AddActivity.this, this,
                    Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
                    Calendar.getInstance().get(Calendar.MINUTE), true);
            timePicker.show();
        });

        myDate = findViewById(R.id.textViewData);
        myDate.setText(Helper.getStringDateToday());

        myDate.setOnClickListener(v -> {
            DatePickerDialog datePicker = new DatePickerDialog(
                    AddActivity.this, this,
                    Calendar.getInstance().get(Calendar.YEAR),
                    Calendar.getInstance().get(Calendar.MONTH),
                    Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
            datePicker.show();
        });

        myTitle = findViewById(R.id.textViewTitle);

        myDescription = findViewById(R.id.textViewDescription);

        finishHin = findViewById(R.id.add_button);
        finishHin.setOnClickListener(v -> {
            String title = myTitle.getText().toString();
            String description = myDescription.getText().toString();
            String stringTime = myTime.getText().toString();
            Integer time = Helper.fromStringTimeToIntegerTime(stringTime);
            Long date = Helper.LongDataFromStringData(myDate.getText().toString());
            Task task = new Task(title, description, date, time, false, 0);
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

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar.getInstance().set(Calendar.HOUR_OF_DAY, hourOfDay);
        Calendar.getInstance().set(Calendar.MINUTE, minute);
        String res = Integer.toString(hourOfDay) + ":" + Helper.with_nul(Integer.toString(minute));
        myTime.setText(res);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar.getInstance().set(Calendar.YEAR, year);
        Calendar.getInstance().set(Calendar.MONTH, month);
        Calendar.getInstance().set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String res = Helper.with_nul(Integer.toString(dayOfMonth)) + "." +
                Helper.with_nul(Integer.toString(month + 1)) + "." + Integer.toString(year);
        myDate.setText(res);
    }


}