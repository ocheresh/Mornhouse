package com.ocheresh.mornhouse;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity(tableName="tables")
public class Mornhouse {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "dNumber")
    private int number;
    @ColumnInfo(name = "dInfo")
    private String info;

    public Mornhouse (int number, String info){
        this.number = number;
        this.info = info;
    }

    public int getNumber(){
        return this.number;
    }

    public String getInfo(){
        return this.info;
    }

    public void setNumber(int number){
        this.number = number;
    }

    public void setInfo(String info){
        this.info = info;
    }
}
