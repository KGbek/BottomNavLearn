package com.example.bottomnavlearn;

import android.app.Application;

import androidx.room.Room;

import com.example.bottomnavlearn.data.local.AppDatabase;
import com.example.bottomnavlearn.utils.Prefs;

public class App extends Application {

    public static Prefs prefs;
    public static AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        database = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "database")
                .allowMainThreadQueries()
                .build();
        prefs = new Prefs(this);
    }
}
