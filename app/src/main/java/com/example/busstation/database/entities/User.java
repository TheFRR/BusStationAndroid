package com.example.busstation.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String login;
    private String password;
    private boolean RememberMe;

    public User(String login, String password, boolean RememberMe)
    {
        this.login = login;
        this.password = password;
        this.RememberMe = RememberMe;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRememberMe() { return RememberMe; }

    public void setRememberMe(boolean RememberMe) { this.RememberMe = RememberMe; }
}
