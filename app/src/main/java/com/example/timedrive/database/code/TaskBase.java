package com.example.timedrive.database.code;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Task.class}, version = 1, exportSchema = false)
public abstract class TaskBase extends RoomDatabase {
    public abstract TaskDao RoomTaskDao();

    private static TaskBase instance;

    // Use once to Create and setup the object
    public static TaskBase getInstance(Context context) {
        if (instance == null) {
            synchronized (TaskBase.class) {
                instance = Room.databaseBuilder(context.getApplicationContext(),
                        TaskBase.class, "Task").build();
            }
        }
        return instance;
    }
}

