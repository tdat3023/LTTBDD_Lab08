package com.example.roomdatabasedemo;

import static androidx.room.OnConflictStrategy.IGNORE;
import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertUser(User employee);


    @Insert(onConflict = IGNORE)
    void insertOrReplaceUser(User... employees);

    @Update(onConflict = REPLACE)
    void updateUser(User employee);

    @Query("DELETE FROM User")
    void deleteAll();

    @Query("SELECT * FROM User")
    public List<User> findAllUserSync();
}