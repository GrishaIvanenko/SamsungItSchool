package com.example.timedrive.database.asks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.timedrive.database.code.TaskBase;
import com.example.timedrive.database.code.Task;
import com.example.timedrive.database.code.TaskDao;

import java.util.ArrayList;

public class AsyncAllWithPriority extends AsyncTask<Integer, Void, ArrayList<Task>> {

    private Context mContext;

    private final String tag = "Trying to Select All with priority from main base";

    public AsyncAllWithPriority (Context context){ mContext = context; }

    @Override
    protected ArrayList<Task> doInBackground(Integer... integers) {

        Log.wtf(tag, "setup");

        TaskBase db = TaskBase.getInstance(mContext);
        TaskDao dao = db.RoomTaskDao();

        Log.wtf(tag, "setup finished!");

        ArrayList<Task> ans = dao.getTaskWithPriority(integers[0]);

        Log.wtf(tag,
                "finished with ans.size() = " + String.valueOf(ans.size()));

        return ans;
    }

    @Override
    protected  void onPostExecute(ArrayList<Task> result) {

        Log.wtf(tag,
                "Trying to post result of Selecting All from main base");

        super.onPostExecute(result);

        Log.wtf(tag,
                "Finished with ans.size() = " + String.valueOf(result.size()));
    }
}
