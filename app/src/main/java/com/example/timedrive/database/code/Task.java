package com.example.timedrive.database.code;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Task")
public class Task {

    @PrimaryKey(autoGenerate =  true)
    int id;
    @ColumnInfo(name = "Description")
    String description;
    @ColumnInfo(name = "Priority")
    int priority;
    @ColumnInfo(name = "Time")
    int time;
    @ColumnInfo(name = "Completed")
    int completed;

    public Task(String  description, int priority, int time) {
        this.description = description;
        this.priority = priority;
        this.time = time;
        completed = 0;
    }

    public int getidId() {return id;}

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    public int getTime() {
        return time;
    }

    public int getcompletedCompleted() {return completed; }

}