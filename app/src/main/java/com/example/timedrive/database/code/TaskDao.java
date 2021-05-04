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

    @Query("DELETE FROM Task WHERE id = :id")
    void deleteTashWithId(int id);

    @Query("SELECT * FROM Task")
    ArrayList<Task> getAllTask();

    @Query("Select * FROM Task WHERE id = :id")
    ArrayList<Task> getTaskWithId(int id);

    @Query("Select * FROM Task WHERE Priority = :Priority")
    ArrayList<Task> getTaskWithPriority(int Priority);
}