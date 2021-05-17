package com.example.timedrive.extra.settingsBase.code;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StringDao {

    @Insert
    void Insert(StringItem str);

    @Update
    void Update(StringItem str);

    @Query("SELECT * FROM StringBase WHERE Title = :title")
    List<StringItem> SelectAllByTitle(String title);

    @Query("DELETE FROM StringBase")
    void deleteAll();

}