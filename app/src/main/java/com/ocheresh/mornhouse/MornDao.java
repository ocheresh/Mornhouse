package com.ocheresh.mornhouse;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Delete;
import androidx.room.Update;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MornDao {
    @Query("Select * from tables")
    List<Mornhouse> getMornhouseList();
    @Insert
    void insertMornhouse(Mornhouse mornhouse);
    @Update
    void updateMornhouse(Mornhouse mornhouse);
    @Delete
    void deleteMornhouse(Mornhouse mornhouse);
}
