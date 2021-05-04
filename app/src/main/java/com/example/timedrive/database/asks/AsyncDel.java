package com.example.timedrive.database.asks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.timedrive.database.code.TaskBase;
import com.example.timedrive.database.code.TaskDao;

public class AsyncDel extends AsyncTask<Integer, Void, Void> {

    private Context mContext;

    private final String tag = "Trying to Del Task from main base";

    public AsyncDel (Context context){
        mContext = context;
    }

    @Override
    protected Void doInBackground(Integer... integers) {

        Log.wtf(tag, "setup");

        TaskBase db = TaskBase.getInstance(mContext);
        TaskDao dao = db.RoomTaskDao();

        Log.wtf(tag,
                "info: " + String.valueOf(integers[0]));

        dao.deleteTashWithId(integers[0]);

        Log.wtf(tag, "Done!");

        return null;
    }

}
