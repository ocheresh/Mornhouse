package com.ocheresh.mornhouse;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = Mornhouse.class, exportSchema = false, version = 1)
public abstract class MornRoomDatabase extends RoomDatabase {
    private static final String DB_NAME = "mornhouse_db";
    private static MornRoomDatabase instance;

    public static synchronized MornRoomDatabase getInstance(Context context)
    {
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), MornRoomDatabase.class,
                    DB_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract MornDao mornDao();

}
