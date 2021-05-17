package com.example.timedrive.extra.settingsBase.asks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.timedrive.extra.settingsBase.code.StringBase;
import com.example.timedrive.extra.settingsBase.code.StringDao;

public class AsyncDeleteAll extends AsyncTask<Void, Void, Void> {

    private Context mContext;

    private final String tag = "Trying to Del All from strings ";

    public AsyncDeleteAll (Context context){
        mContext = context;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        Log.d(tag, "setup");

        StringBase db = StringBase.getInstance(mContext);
        StringDao dao = db.stringDao();

        Log.d(tag,
                "info: " + "Deleting All!");

        dao.deleteAll();

        Log.d(tag, "Done!");

        return null;
    }

}
