package com.example.timedrive.extra.settingsBase.code;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "StringBase")
public class StringItem {

    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "Value")
    private String value;
    @ColumnInfo(name = "Title")
    private String title;


    public StringItem(String title, String value) {
        this.title = title;
        this.value = value;
    }

    public int getidId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getValue() {
        return value;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String infoString() {
        return "info::: id:" + String.valueOf(id) + "; title: " + title +
                "; value: " + value;
    }

}