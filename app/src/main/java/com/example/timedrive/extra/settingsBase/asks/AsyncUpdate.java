package com.example.timedrive.extra.settingsBase.asks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.timedrive.extra.settingsBase.code.StringBase;
import com.example.timedrive.extra.settingsBase.code.StringDao;
import com.example.timedrive.extra.settingsBase.code.StringItem;


public class AsyncUpdate extends AsyncTask<StringItem, Void, Void> {

    private Context mContext;

    private final String tag = "Updating settings base";

    public AsyncUpdate (Context context){
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

        dao.Update(stringItem[0]);

        Log.d(tag, "Done!");

        return null;
    }

}
