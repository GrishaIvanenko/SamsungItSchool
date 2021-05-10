package com.example.timedrive.database.asks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.timedrive.database.code.TaskBase;
import com.example.timedrive.database.code.TaskDao;

public class AsyncDel extends AsyncTask<Void, Void, Void> {

    private Context mContext;

    private final String tag = "Trying to Del Task from main base";

    public AsyncDel (Context context){
        mContext = context;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        Log.d(tag, "setup");

        TaskBase db = TaskBase.getInstance(mContext);
        TaskDao dao = db.RoomTaskDao();

        Log.d(tag,
                "info: " + "Deleting All!");

        dao.deleteAll();

        Log.d(tag, "Done!");

        return null;
    }

}
