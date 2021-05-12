package com.example.timedrive.extra;

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

}
