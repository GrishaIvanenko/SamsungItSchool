package com.example.timedrive.database.code;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;

@Dao
public interface TaskDao {
    @Insert
    void Insert(Task roomTask);

    @Insert
    void InsertAll(ArrayList<Task> arr);

    @Delete
    void Delete(Task roomTask);

    @Query("DELETE FROM Task")
    void deleteAll();

    @Query("SELECT * FROM Task")
    ArrayList<Task> getAllTask();

    @Query("Select * FROM Task WHERE id = :id")
    ArrayList<Task> getTaskWithid(int id);

    @Query("Select * FROM Task WHERE Description = :Description")
    ArrayList<Task> getTaskWithDescription(String Description);

    @Query("Select * FROM Task WHERE Time = :Time")
    ArrayList<Task> getTaskWithTime(int Time);

    @Query("Select * FROM Task WHERE Priority = :Priority")
    ArrayList<Task> getTaskWithPriority(int Priority);
}