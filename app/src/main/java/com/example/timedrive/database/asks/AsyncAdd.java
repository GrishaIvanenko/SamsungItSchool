package com.example.timedrive.database.asks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.timedrive.database.code.TaskBase;
import com.example.timedrive.database.code.Task;
import com.example.timedrive.database.code.TaskDao;

public class AsyncAdd extends AsyncTask<Task, Void, Void> {

    private Context mContext;

    private final String tag = "Adding to main base";

    public AsyncAdd (Context context){
        mContext = context;
    }

    @Override
    protected Void doInBackground(Task... roomTasks) {

        Log.d(tag, "setup");

        TaskBase db = TaskBase.getInstance(mContext);
        TaskDao dao = db.RoomTaskDao();

        Log.d(tag,
                "info: " +
                roomTasks[0].infoString());

        dao.Insert(roomTasks[0]);

        Log.d(tag, "Done!");

        return null;
    }

}
