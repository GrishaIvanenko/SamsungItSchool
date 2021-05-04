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

    public Task(String  description, int priority, int time) {
        this.description = description;
        this.priority = 0;
        this.time = -1;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    public int getTime() {
        return time;
    }

}