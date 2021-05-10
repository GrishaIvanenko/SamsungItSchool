package com.example.timedrive.database.code;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskDao {

    @Insert
    void Insert(Task task);

    @Update
    void Update(Task task);

    @Query("DELETE FROM Task")
    void deleteAll();

    @Query("DELETE FROM Task WHERE id = :id")
    void deleteById(int id);

    @Query("SELECT * FROM Task")
    List<Task> getAllTask();

    @Query("Select * FROM Task WHERE id = :id")
    List<Task> getTaskWithId(int id);

    @Query("Select * FROM Task WHERE Done = :done")
    List<Task> getTaskWithDone(boolean done);

}