package com.example.timedrive.extra.settingsBase.asks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.timedrive.extra.settingsBase.code.StringBase;
import com.example.timedrive.extra.settingsBase.code.StringDao;
import com.example.timedrive.extra.settingsBase.code.StringItem;

import java.util.ArrayList;

public class AsyncGetByName extends AsyncTask<String, Void, ArrayList<StringItem>> {

    private Context mContext;

    private final String tag = "Trying to Select by title";

    public AsyncGetByName (Context context){ mContext = context; }

    @Override
    protected ArrayList<StringItem> doInBackground(String... strings) {

        Log.d(tag, "setup");

        StringBase db = StringBase.getInstance(mContext);
        StringDao dao = db.stringDao();

        Log.d(tag, "setup finished!");

        ArrayList<StringItem> ans = (ArrayList<StringItem>)dao.SelectAllByTitle(strings[0]);

        Log.d(tag,
                "finished with ans.size() = " + String.valueOf(ans.size()));

        return ans;
    }

    @Override
    protected  void onPostExecute(ArrayList<StringItem> result) {

        Log.d(tag,
                "Trying to post result of Selecting by name");

        super.onPostExecute(result);

        Log.d(tag,
                "Finished with ans.size() = " + String.valueOf(result.size()));
    }
}
