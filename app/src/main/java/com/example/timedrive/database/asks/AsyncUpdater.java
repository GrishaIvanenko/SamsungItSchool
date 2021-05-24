package com.example.timedrive.database.asks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.timedrive.database.code.Task;
import com.example.timedrive.database.code.TaskBase;
import com.example.timedrive.database.code.TaskDao;
import com.example.timedrive.extra.Helper;

import java.util.ArrayList;

public class AsyncUpdater  extends AsyncTask<Void, Void, Void> {

    private Context mContext;

    private final String tag = "ChangeData";

    public AsyncUpdater (Context context){
            mContext = context;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            Log.d(tag, "setup");

            TaskBase db = TaskBase.getInstance(mContext);
            TaskDao dao = db.RoomTaskDao();

            ArrayList<Task> cash = (ArrayList<Task>)dao.getAllTask();
            for (int i = 0; i < cash.size(); ++i) {
                if (cash.get(i).getDone() == false) {
                    if (cash.get(i).getDate() < Helper.getLongToday()) {
                        cash.get(i).setDate(Helper.getLongToday());
                        dao.update(cash.get(i));
                    }
                }
            }

            Log.d(tag, "Done!");

            return null;
        }

}
