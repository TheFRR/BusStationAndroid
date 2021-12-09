package com.example.busstation.database.dao_interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.busstation.database.entities.User;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    LiveData<List<User>> getAll();

    @Query("SELECT * FROM user WHERE id = :id")
    LiveData<User> getById(long id);

    @Query("SELECT * FROM user WHERE RememberMe")
    LiveData<User> getCurrentUser();

    @Query("SELECT COUNT(*) FROM user")
    int getCount();

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);
}