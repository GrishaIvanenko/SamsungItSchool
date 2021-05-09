package com.example.timedrive.database.asks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.timedrive.database.code.TaskBase;
import com.example.timedrive.database.code.Task;
import com.example.timedrive.database.code.TaskDao;

public class AsyncAdd extends AsyncTask<Task, Void, Void> {

    private Context mContext;

    private final String tag = "Trying to Add to main base";

    public AsyncAdd (Context context){
        mContext = context;
    }

    @Override
    protected Void doInBackground(Task... roomTasks) {

        Log.wtf(tag, "setup");

        TaskBase db = TaskBase.getInstance(mContext);
        TaskDao dao = db.RoomTaskDao();

        Log.wtf(tag,
                "info: " +
                roomTasks[0].getDescription() + " "
                +String.valueOf(roomTasks[0].getidId()) + " "
                + String.valueOf(roomTasks[0].getPriority()) + " "
                + String.valueOf(roomTasks[0].getTime()));

        dao.Insert(roomTasks[0]);

        Log.wtf(tag, "Done!");

        return null;
    }

}
