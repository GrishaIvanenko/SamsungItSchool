package com.example.timedrive.database.asks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.timedrive.database.code.TaskBase;
import com.example.timedrive.database.code.TaskDao;

public class AsyncDelById extends AsyncTask<Integer, Void, Void> {

    private Context mContext;

    private final String tag = "Trying to Del Task by id from main base";

    public AsyncDelById (Context context){
        mContext = context;
    }

    @Override
    protected Void doInBackground(Integer... integers) {

        Log.d(tag, "setup");

        TaskBase db = TaskBase.getInstance(mContext);
        TaskDao dao = db.RoomTaskDao();

        Log.d(tag,
                "info: " + "Deleting All!");

        dao.deleteById(integers[0]);

        Log.d(tag, "Done!");

        return null;
    }

}
