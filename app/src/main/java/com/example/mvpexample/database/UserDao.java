package com.example.mvpexample.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/**
 * Created by Viet Hua on 04/25/2020.
 */

@Dao
public abstract class UserDao {
    @Insert
    public abstract void insert(UserModel userModel);

    @Query("SELECT COUNT(*) FROM user_table WHERE email=:email AND password=:password")
    public abstract int isLoginSuccess(String email, String password);
}
