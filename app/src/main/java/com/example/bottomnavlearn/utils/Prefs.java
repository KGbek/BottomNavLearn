package com.example.bottomnavlearn.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {

    private SharedPreferences preferences;
    private static Prefs instance;

    public static Prefs getInstance() {
        return instance;
    }

    public Prefs(Context context) {
        instance = this;
        preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
    }

    public void saveBoardState(){
        preferences.edit().putBoolean("isShown", true).apply();
    }

    public boolean isBoardShown(){
        return preferences.getBoolean("isShown", false);
    }

    public void saveNote(int position, String text){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(position+"", text);
        editor.apply();
    }

    public void editNote(){
        preferences.getString("saveNote", "");
    }

}
