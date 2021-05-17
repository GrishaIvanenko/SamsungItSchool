package com.example.timedrive.extra.settingsBase.code;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.timedrive.extra.settingsBase.asks.AsyncAdd;
import com.example.timedrive.extra.settingsBase.asks.AsyncGetByName;
import com.example.timedrive.extra.settingsBase.asks.AsyncUpdate;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


@Database(entities = {StringItem.class}, version = 1, exportSchema = false)
public abstract class StringBase extends RoomDatabase {
    public abstract StringDao stringDao();

    private static StringBase instance;

    // Use once to Create and setup the object
    public static StringBase getInstance(Context context) {
        if (instance == null) {
            synchronized (com.example.timedrive.database.code.TaskBase.class) {
                instance = Room.databaseBuilder(context.getApplicationContext(),
                        StringBase.class, "StringBase").build();
                hard_setup(context);
            }
        }
        return instance;
    }

    private static void hard_setup(Context context) {
        hard_add(context, "no_result", "На текущий момент задач нет!");
        hard_add(context, "resultfrom0to20", "Работы ещё много!");
        hard_add(context, "resultfrom20to40", "Это немного, зато честная работа");
        hard_add(context, "resultfrom40to60", "Осталось примерно столько же");
        hard_add(context, "resultfrom60to80", "Уже не стыдно!");
        hard_add(context, "resultfrom80to100", "Всегда бы так работал!");
        hard_add(context, "resultfrom100to100", "Возьми с полки пирожок!");
    }

    private static void hard_add(Context context, String title, String value) {

        AsyncGetByName rab = new AsyncGetByName(context);
        rab.execute(title);
        ArrayList<StringItem> res = new ArrayList<StringItem>();
        try {
            res = rab.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (res != null && res.size() == 1) {
            Log.d("SETTING STRINGBASE NAME=" + title, "Has yet!");
            return;
        }

        AsyncAdd kekw = new AsyncAdd(context);
        StringItem val = new StringItem(title, value);
        kekw.execute(val);
        try {
            kekw.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.d("SETTING STRINGBASE NAME=" + title, "Done!");
    }

    public static String getValue(Context context, String title) {
        AsyncGetByName rab = new AsyncGetByName(context);
        rab.execute(title);
        ArrayList<StringItem> res = new ArrayList<StringItem>();
        try {
            res = rab.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (res != null && res.size() == 1) {
            return res.get(0).getValue();
        }
        Log.d("DITCH", "NO this!");
        return null;
    }

    public static void change(Context context, String name, String nw) {
        AsyncGetByName rab = new AsyncGetByName(context);
        rab.execute(name);
        ArrayList<StringItem> res = new ArrayList<StringItem>();
        try {
            res = rab.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (res == null) {
            Log.d("DITCH", "No string with this title! NUll!");
            return;
        }
        if (res.size() != 1) {
            Log.d("DITCH", "No string with this title!, ans.size() = " + String.valueOf(res.size()));
            return;
        }
        StringItem kekw = res.get(0);
        kekw.setValue(nw);
        AsyncUpdate rab1 = new AsyncUpdate(context);
        rab1.execute(kekw);
        try {
            rab1.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("DONE228", "change completed!" + kekw.getidId() + " " + kekw.getTitle() + " " + kekw.getValue() + " but " + nw);
    }
}
