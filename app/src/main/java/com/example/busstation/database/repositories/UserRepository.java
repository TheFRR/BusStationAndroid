package com.example.busstation.database.repositories;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.example.busstation.database.AppDatabase;
import com.example.busstation.database.dao_interfaces.UserDao;
import com.example.busstation.database.entities.User;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import androidx.annotation.Nullable;

public class UserRepository implements Repository<User> {

    private final UserDao userDao;
    private final LiveData<List<User>> allUsers;
    private final LiveData<User> currentUser;

    public UserRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        userDao = db.userDao();
        allUsers = userDao.getAll();
        currentUser = userDao.getCurrentUser();
    }

    public LiveData<List<User>> getAll() {
        return allUsers;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    public LiveData<User> getById(long id) {
        return Transformations.map(allUsers, input -> input.stream()
                .filter(m -> m.getId() == id)
                .findAny()
                .orElse(null)
        );
    }

    public LiveData<User> getCurrentUser() { return currentUser; }

    public void insert(User user) {
        AppDatabase.databaseWriteExecutor.execute(() -> userDao.insert(user));
    }

    public void update(User user) {
        AppDatabase.databaseWriteExecutor.execute(() -> userDao.update(user));
    }

    public void delete(User user) {
        AppDatabase.databaseWriteExecutor.execute(() -> userDao.delete(user));
    }
}

