package com.example.timedrive.extra;

import android.content.Context;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.timedrive.R;
import com.example.timedrive.database.code.Task;
import com.example.timedrive.extra.settingsBase.asks.AsyncGetByName;
import com.example.timedrive.extra.settingsBase.code.StringItem;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;

public class Helper {

    public static Integer fromStringTimeToIntegerTime(String s) {
        String[] strings = s.split(":");
        Integer hours = Integer.parseInt(strings[0]);
        Integer minutes = Integer.parseInt(strings[1]);
        Integer result = hours * 60 + minutes;
        return result;
    }

    public static long getLongToday() {
        return (long) ((Calendar.getInstance().get(Calendar.YEAR)) * 1000 +
                Calendar.getInstance().get(Calendar.DAY_OF_YEAR));
    }

    public static String parce_time(Integer time) {
        String TAG = "parcing time";
        if (time < 0 | time >= 60 * 24) {
            Log.d(TAG, "parce_time Error: time = " + time.toString() + " BUT time must be [0; 24 * 60 * 60)!!!");
            assert false;
        }
        Integer hours = time / 60;
        Integer minutes = time % 60;
        String ans = hours.toString() + ":" + with_nul(minutes.toString());
        Log.d(TAG, "parce_time: " + ans + "; -OK!");
        return ans;
    }

    public static String with_nul(String s) {
        if (s.length() == 1)
            return "0" + s;
        return s;
    }

    public static String getStringDateToday() {
        String year1 = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
        String month = String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1);
        String date = String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        String ans = with_nul(date) + "." + with_nul(month) + "." + year1;
        return ans;
    }

    public static String parceDate(Long data) {
        Integer year = (int)(data / 1000);
        Integer day = (int)(data % 1000);
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.DAY_OF_YEAR, day);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        String ans = new DateFormatSymbols().getShortWeekdays()[dayOfWeek];
        return ans;
    }

    public static String getStringDate(Long data) {
        Integer year = (int)(data / 1000);
        Integer day = (int)(data % 1000);
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.DAY_OF_YEAR, day);
        String ans = Helper.with_nul(String.valueOf(c.get(Calendar.DAY_OF_MONTH))) +
                "." + Helper.with_nul(String.valueOf(c.get(Calendar.MONTH) + 1)) +
                "." + String.valueOf(c.get(Calendar.YEAR));
        return ans;
    }

    public static String getStringDateWithFeatures(Long date) {
        if (date == getLongToday())
            return "Сегодня";
        if (getLongWeekBegin() <= date && date <= getLongWeekEnd())
            return parceDate(date);
        return getStringDate(date);
    }

    public static Long LongDataFromStringData(String input) {
        Log.d("DITCH", "LongDataFromStringData: " + input);
        String[] cash = input.split("\\.");
        Log.d("DITCH", "LongDataFromStringData: " + cash[0] + " " + cash[1] + " " + cash[2]);
        Calendar c = Calendar.getInstance();
        Log.d("DITCH", "LongDataFromStringData: " + cash[0] + " " + cash[1] + " " + cash[2]);
        c.set(Calendar.YEAR, Integer.valueOf(cash[2]));
        c.set(Calendar.MONTH, Integer.valueOf(cash[1]) - 1);
        c.set(Calendar.DAY_OF_MONTH, Integer.valueOf(cash[0]));
        return Long.valueOf(Integer.valueOf(cash[2]) * 1000 + c.get(Calendar.DAY_OF_YEAR));

    }

    public static Long getLongWeekBegin() {
        return getLongToday() - Calendar.getInstance().get(Calendar.DAY_OF_WEEK) + Long.valueOf(1);
    }

    public static Long getLongWeekEnd() {
        return getLongToday() - Calendar.getInstance().get(Calendar.DAY_OF_WEEK) + Long.valueOf(8);
    }

    public static void setup_progress(ArrayList<Task> cash, ProgressBar progressBar, TextView progress, Context context) {
        if (cash == null || cash.size() == 0) {
            progressBar.setProgress(0);
            progress.setText(getByName(context, "no_result"));
        } else {
            int kolv = 0;
            for (int i = 0; i < cash.size(); ++i)
                if (cash.get(i).getDone() == true)
                    ++kolv;
            Integer pers = (100 * kolv) / cash.size();
            progressBar.setProgress(pers);
            String reaction = getres(context, pers);
            String score = "Выполнено " + pers.toString() + "% задач\n" + reaction;
            progress.setText(score);
        }
    }

    public static String getres(Context context, int pers) {
        if (0 <= pers && pers < 20)
            return getByName(context, "resultfrom0to20");
        if (20 <= pers && pers < 40)
            return getByName(context, "resultfrom20to40");
        if (40 <= pers && pers < 60)
            return getByName(context, "resultfrom40to60");
        if (60 <= pers && pers < 80)
            return getByName(context, "resultfrom60to80");
        if (80 <= pers && pers < 100)
            return getByName(context, "resultfrom80to100");
        if (pers == 100)
            return getByName(context, "resultfrom100to100");
        return "Кривой процент!";
    }


    public static String getByName(Context context, String title) {
        AsyncGetByName rab = new AsyncGetByName(context);
        rab.execute(title);
        ArrayList<StringItem>  ans = new ArrayList<StringItem>();
        try {
            ans = rab.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (ans == null || ans.size() != 1) {
            Log.d("WTF", "хрень в запросе по имени");
            assert (false);
        }
        return ans.get(0).getValue();
    }

    public static int iconById(int id) {
        if (id == 0) return R.drawable.ic_homework;
        if (id == 1) return R.drawable.ic_gym;
        if (id == 2) return R.drawable.ic_phone;
        if (id == 3) return R.drawable.ic_train;
        return R.drawable.ic_settings;
    }

}
