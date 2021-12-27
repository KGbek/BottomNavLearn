package com.example.bottomnavlearn.data.local;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.bottomnavlearn.models.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    LiveData<List<User>> getAllUsers();

    @Insert
    void addUser(User user);

    @Delete
    void deleteUser(User user);

    @Update
    void editUser(User user);
}
