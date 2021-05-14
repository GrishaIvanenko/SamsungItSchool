package com.example.timedrive.extra;

import com.example.timedrive.database.code.Task;

import java.util.Comparator;

public class TaskTimeComparator implements Comparator<Task> {
    @Override
    public int compare(Task fir, Task sec) {
        if (fir.getDate() < sec.getDate())
            return -1;
        if (fir.getDate() > sec.getDate())
            return 1;
        if (fir.getTime() < sec.getTime())
            return -1;
        if (fir.getTime() > sec.getTime())
            return 1;
        return 1;
    }
}
