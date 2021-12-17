package com.example.bottomnavlearn;

import android.app.Application;

import com.example.bottomnavlearn.Utils.Prefs;

public class App extends Application {

    public static Prefs prefs;

    @Override
    public void onCreate() {
        super.onCreate();
        prefs = new Prefs(this);
    }
}
