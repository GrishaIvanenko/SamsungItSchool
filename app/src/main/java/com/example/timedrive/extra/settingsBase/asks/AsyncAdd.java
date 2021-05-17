package com.example.timedrive.extra.settingsBase.asks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.timedrive.extra.settingsBase.code.StringBase;
import com.example.timedrive.extra.settingsBase.code.StringDao;
import com.example.timedrive.extra.settingsBase.code.StringItem;


public class AsyncAdd extends AsyncTask<StringItem, Void, Void> {

    private Context mContext;

    private final String tag = "Adding to settings base";

    public AsyncAdd (Context context){
        mContext = context;
    }

    @Override
    protected Void doInBackground(StringItem... stringItem) {

        Log.d(tag, "setup");

        StringBase db = StringBase.getInstance(mContext);
        StringDao dao = db.stringDao();

        Log.d(tag,
                "info: " +
                        stringItem[0].infoString());

        dao.Insert(stringItem[0]);

        Log.d(tag, "Done!");

        return null;
    }

}
