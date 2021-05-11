package com.example.timedrive.database.code;

import java.util.Comparator;

public class TaskTimeComparator implements Comparator<Task> {
    @Override
    public int compare(Task fir, Task sec) {
        if (fir.getTime() == sec.getTime())
            return 0;
        if (fir.getTime() < sec.getTime())
            return -1;
        return 1;
    }
}
