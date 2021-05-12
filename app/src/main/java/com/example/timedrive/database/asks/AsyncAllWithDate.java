package com.example.timedrive.database.asks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.timedrive.database.code.Task;
import com.example.timedrive.database.code.TaskBase;
import com.example.timedrive.database.code.TaskDao;

import java.util.ArrayList;

public class AsyncAllWithDate extends AsyncTask<Long, Void, ArrayList<Task>> {

    private Context mContext;

    private final String tag = "Trying to Select All with data in [first, last] from main base";

    public AsyncAllWithDate (Context context){ mContext = context; }

    @Override
    protected ArrayList<Task> doInBackground(Long... integer) {

        Log.d(tag, "setup");

        TaskBase db = TaskBase.getInstance(mContext);
        TaskDao dao = db.RoomTaskDao();

        Log.d(tag, "setup finished!");

        ArrayList<Task> ans = (ArrayList<Task>)dao.getTaskWIthDate(integer[0], integer[1]);

        Log.d(tag,
                "finished with ans.size() = " + String.valueOf(ans.size()));

        return ans;
    }

    @Override
    protected  void onPostExecute(ArrayList<Task> result) {

        Log.d(tag,
                "Trying to post result of Selecting All from main base");

        super.onPostExecute(result);

        Log.d(tag,
                "Finished with ans.size() = " + String.valueOf(result.size()));
    }
}
