package com.example.timedrive.extra;

import android.util.Log;

import java.text.DateFormatSymbols;
import java.util.Calendar;

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
        String month = String.valueOf(Calendar.getInstance().get(Calendar.MONTH));
        String date = String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_WEEK));
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

    public static Long LongDataFromStringData(String input) {
        Log.d("DITCH", "LongDataFromStringData: " + input);
        String[] cash = input.split("\\.");
        Log.d("DITCH", "LongDataFromStringData: " + cash[0] + " " + cash[1] + " " + cash[2]);
        Calendar c = Calendar.getInstance();
        Log.d("DITCH", "LongDataFromStringData: " + cash[0] + " " + cash[1] + " " + cash[2]);
        c.set(Calendar.YEAR, Integer.valueOf(cash[2]));
        c.set(Calendar.MONTH, Integer.valueOf(cash[1]));
        c.set(Calendar.DAY_OF_MONTH, Integer.valueOf(cash[0]));
        return Long.valueOf(Integer.valueOf(cash[2]) * 1000 + c.get(Calendar.DAY_OF_YEAR));

    }

}
