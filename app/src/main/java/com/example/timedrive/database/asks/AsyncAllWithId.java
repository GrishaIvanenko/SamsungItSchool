package com.example.timedrive.database.asks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.timedrive.database.code.TaskBase;
import com.example.timedrive.database.code.Task;
import com.example.timedrive.database.code.TaskDao;

import java.util.ArrayList;

public class AsyncAllWithId extends AsyncTask<Integer, Void, ArrayList<Task>> {

    private Context mContext;

    private final String tag = "Trying to Select All with id from main base";

    public AsyncAllWithId (Context context){ mContext = context; }

    @Override
    protected ArrayList<Task> doInBackground(Integer... integers) {

        Log.d(tag, "setup");

        TaskBase db = TaskBase.getInstance(mContext);
        TaskDao dao = db.RoomTaskDao();

        Log.d(tag, "setup finished!");

        ArrayList<Task> ans =  (ArrayList<Task>)dao.getTaskWithId(integers[0]);

        Log.d(tag,
                "finished with ans.size() = " + String.valueOf(ans.size()));

        return ans;
    }

    @Override
    protected  void onPostExecute(ArrayList<Task> result) {

        Log.d(tag,
                "Trying to post result of Selecting All from main base");

        super.onPostExecute(result);

        if (result.size() == 1) {
            Log.d(tag, "Finished, found!");
        } else if (result.size() == 0) {
            Log.d(tag, "Finished,  not found!");
        } else {
            Log.d(tag, "Finished with trouble: " + String.valueOf(result.size())
            + ", but size must be 0 or 1!!!");
            assert (false);
        }

    }
}
