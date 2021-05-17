package com.example.timedrive.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;

import com.example.timedrive.R;
import com.example.timedrive.database.asks.AsyncAdd;
import com.example.timedrive.database.asks.AsyncDelById;
import com.example.timedrive.database.asks.AsyncUpdate;
import com.example.timedrive.database.code.Task;
import com.example.timedrive.extra.Helper;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;

public class AddActivity extends AppCompatActivity implements
        TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    private MaterialTextView myTime;
    private MaterialTextView myDate;
    private AppCompatButton finishHin;
    private EditText myTitle;
    private  EditText myDescription;
    private AppCompatImageButton go_left;
    private AppCompatImageButton go_right;
    private AppCompatImageButton left_ic;
    private AppCompatImageButton right_ic;
    private AppCompatImageButton main_ic;
    private ArrayList<Integer> input;
    private int position;
    private AppCompatImageButton delete22;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        delete22 = findViewById(R.id.icon_delete);

        go_left = findViewById(R.id.icon_left_move228);
        go_left.setImageResource(R.drawable.ic_go_left);
        go_left.setOnClickListener(v -> {
            position = prev(position);
            redraw();
        });

        go_right = findViewById(R.id.icon_right_move228);
        go_right.setImageResource(R.drawable.ic_go_right);
        go_right.setOnClickListener(v -> {
            position = next(position);
            redraw();
        });

        left_ic = findViewById(R.id.icon_left);
        right_ic = findViewById(R.id.icon_right);
        main_ic = findViewById(R.id.icon_selected);
        setup_icons();

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
            Task task = new Task(title, description, date, time, false, input.get(position));
            AsyncAdd adder = new AsyncAdd(getApplicationContext());
            adder.execute(task);
            try {
                adder.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            this.finish();
        });

        Intent intent = getIntent();
        String type = intent.getStringExtra("Edit");
        Integer res = Integer.parseInt(type);
        if (res == 0)
            return;

        Task task = new Task(intent.getStringExtra("TaskData"));

        delete22.setBackgroundResource(R.drawable.ic_delete_shape);
        delete22.setImageResource(R.drawable.ic_delete);
        delete22.setOnClickListener(v-> {
            AsyncDelById rab = new AsyncDelById(getApplicationContext());
            rab.execute(task.getidId());
            try {
                rab.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            this.finish();
        });

        myTitle.setText(task.getTitle());
        myDescription.setText(task.getDescription());
        myTime.setText(Helper.parce_time(task.getTime()));
        myDate.setText(Helper.getStringDate(task.getDate()));
        for (int i = 0; i < input.size(); ++i)
            if (input.get(i) == task.getMyPicture())
                position = i;
        redraw();
        finishHin.setOnClickListener(v -> {
            String title = myTitle.getText().toString();
            String description = myDescription.getText().toString();
            String stringTime = myTime.getText().toString();
            Integer time = Helper.fromStringTimeToIntegerTime(stringTime);
            Long date = Helper.LongDataFromStringData(myDate.getText().toString());
            task.setTitle(title);
            task.setDescription(description);
            task.setTime(time);
            task.setDate(date);
            task.setMyPicture(input.get(position));
            AsyncUpdate adder = new AsyncUpdate(getApplicationContext());
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

    private void setup_icons() {
        input = new ArrayList<>();
        input.add(0);
        input.add(1);
        input.add(2);
        input.add(3);
        position = 1;
        redraw();
    }

    private int prev(int x) {
        if (x != 0)
            return x - 1;
        return input.size() - 1;
    }

    private int next(int x) {
        if (x + 1 == input.size())
            return 0;
        return x + 1;
    }

    private void redraw() {
        int left = input.get(prev(position));
        int mid = input.get(position);
        int right = input.get(next(position));

        left_ic.setImageResource(Helper.iconById(left));
        main_ic.setImageResource(Helper.iconById(mid));
        right_ic.setImageResource(Helper.iconById(right));
    }


}