package com.example.timedrive.database.code;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

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
    List<Task> getAllTask();

    @Query("Select * FROM Task WHERE id = :id")
    List<Task> getTaskWithId(int id);

    @Query("Select * FROM Task WHERE Priority = :Priority")
    List<Task> getTaskWithPriority(int Priority);

    @Query("Select * FROM Task WHERE Completed = :Completed")
    List<Task> getTaskWIthCompleted(int Completed);
}