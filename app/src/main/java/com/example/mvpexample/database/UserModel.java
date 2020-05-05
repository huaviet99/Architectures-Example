package com.example.mvpexample.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * Created by Viet Hua on 04/25/2020.
 */

@Entity(tableName = "user_table")
public class UserModel {
    @NonNull
    @PrimaryKey
    private String email;
    @ColumnInfo(name = "password")
    private String password;


    public UserModel() {

    }

    @Ignore
    public UserModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
