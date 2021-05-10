package com.example.timedrive.database.code;

import android.util.Log;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Task")
public class Task {

    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "Title")
    private String title;
    @ColumnInfo(name = "Description")
    private String description;
    @ColumnInfo(name = "Date")
    private long date;
    @ColumnInfo(name = "Time")
    private int time;
    @ColumnInfo(name = "Done")
    private boolean done;
    @ColumnInfo(name = "MyPicture")
    private int myPicture;

    public Task(String title, String description, long date, int time, boolean done, int myPicture) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.time = time;
        this.done = done;
        this.myPicture = myPicture;
    }

    public int getidId() {
        return id;
    }

    ;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public long getDate() {
        return date;
    }

    public int getTime() {
        return time;
    }

    public boolean getDone() {
        return done;
    }

    public int getMyPicture() {
        return myPicture;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void setMyPicture(int myPicture) {
        this.myPicture = myPicture;
    }

    public String infoString() {
        return "Id:" + String.valueOf(id) + "; Title: " + title + "; Description: " + description +
                "; Date: " + String.valueOf(date) + "; Time: " + String.valueOf(time) +
                "; Done: " + String.valueOf(done) + "; MyPicture: " + String.valueOf(myPicture);
    }

    public String toString() {
        return String.valueOf(id) + " $ " + title + " $ " + description + " $ "
                + String.valueOf(date) + " $ " + String.valueOf(time) + " $ "
                + String.valueOf(done) + " $ " + String.valueOf(myPicture);
    }

    @Ignore
    public Task(String data) {
        String TAG = "From String to Task";
        String[] cash = data.split(" $ ");
        if (cash.length != 6) {
            Log.d(TAG, "BAD DATA: data.size() = " +  String.valueOf(cash.length) +
                    "instead of ");
            assert false;
        }
        Log.d(TAG, "Start parsing");
        id = Integer.parseInt(cash[0]);
        title = cash[1];
        description = cash[2];
        date = Long.parseLong(cash[3]);
        time = Integer.parseInt(cash[4]);
        done = Boolean.parseBoolean(cash[5]);
        myPicture = Integer.parseInt(cash[6]);
        Log.d(TAG, "Parsing finished!");
    }
}