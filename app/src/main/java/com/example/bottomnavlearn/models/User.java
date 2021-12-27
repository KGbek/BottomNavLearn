package com.example.bottomnavlearn.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class User implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name, surename;


    public User(String name, String surename) {
        this.name = name;
        this.surename = surename;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurename() {
        return surename;
    }
}
