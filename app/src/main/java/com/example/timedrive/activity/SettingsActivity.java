package com.example.timedrive.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.timedrive.R;
import com.example.timedrive.extra.settingsBase.code.StringBase;

public class SettingsActivity extends AppCompatActivity {

    private AppCompatButton buttonDefault;
    private AppCompatButton buttonSet1;
    private EditText No_res;
    private EditText res_0_20;
    private EditText res_20_40;
    private EditText res_40_60;
    private EditText res_60_80;
    private EditText res_80_100;
    private EditText res_100_100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        No_res = findViewById(R.id.textViewno_result);
        res_0_20 = findViewById(R.id.textViewresultfrom0to20);
        res_20_40 = findViewById(R.id.textViewresultfrom20to40);
        res_40_60 = findViewById(R.id.textViewresultfrom40to60);
        res_60_80 = findViewById(R.id.textViewresultfrom60to80);
        res_80_100 = findViewById(R.id.textViewresultfrom80to100);
        res_100_100 = findViewById(R.id.textViewresultfrom100to100);

        redraw();

        buttonDefault = findViewById(R.id.make_default);
        buttonDefault.setOnClickListener(v-> {
            StringBase base = StringBase.getInstance(getApplicationContext());
            Context context = getApplicationContext();
            base.change(context, "no_result", "На текущий момент задач нет!");
            base.change(context, "resultfrom0to20", "Работы ещё много!");
            base.change(context, "resultfrom20to40", "Это немного, зато честная работа");
            base.change(context, "resultfrom40to60", "Осталось примерно столько же");
            base.change(context, "resultfrom60to80", "Уже не стыдно!");
            base.change(context, "resultfrom80to100", "Всегда бы так работал!");
            base.change(context, "resultfrom100to100", "Возьми с полки пирожок!");
            redraw();
        });

        buttonSet1 = findViewById(R.id.make_now);
        buttonSet1.setOnClickListener(v-> {
            StringBase base = StringBase.getInstance(getApplicationContext());;
            Context context = getApplicationContext();
            base.change(context, "no_result", No_res.getText().toString());
            base.change(context, "resultfrom0to20", res_0_20.getText().toString());
            base.change(context, "resultfrom20to40", res_20_40.getText().toString());
            base.change(context, "resultfrom40to60", res_40_60.getText().toString());
            base.change(context, "resultfrom60to80", res_60_80.getText().toString());
            base.change(context, "resultfrom80to100", res_80_100.getText().toString());
            base.change(context, "resultfrom100to100", res_100_100.getText().toString());
            redraw();
        });
    }

    private void redraw() {
        StringBase base = StringBase.getInstance(getApplicationContext());
        String kekw = base.getValue(getApplicationContext(), "no_result");
        if (kekw != null)
            Log.d("LOOPA", kekw);
        else {
            Log.d("LOOPA", "NULL");
        }
        No_res.setText(base.getValue(getApplicationContext(), "no_result"));
        res_0_20.setText(base.getValue(getApplicationContext(), "resultfrom0to20"));
        res_20_40.setText(base.getValue(getApplicationContext(), "resultfrom20to40"));
        res_40_60.setText(base.getValue(getApplicationContext(), "resultfrom40to60"));
        res_60_80.setText(base.getValue(getApplicationContext(), "resultfrom60to80"));
        res_80_100.setText(base.getValue(getApplicationContext(), "resultfrom80to100"));
        res_100_100.setText(base.getValue(getApplicationContext(), "resultfrom100to100"));
    }
}