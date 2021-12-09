package com.example.busstation.settings;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.busstation.App;

public class UserSettings {
    private static final String USERS_SETTINGS_PREFERENCES = "users_settings_preferences";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String ID = "id";

    private static final SharedPreferences preferences;

    static
    {
        preferences = App.getInstance().getSharedPreferences(USERS_SETTINGS_PREFERENCES, Context.MODE_PRIVATE);
    }

    public static void setLogin(String login)
    {
        preferences.edit().putString(LOGIN, login).apply();
    }

    public static String getLogin()
    {
        return preferences.getString(LOGIN, "");
    }

    public static void setPassword(String password)
    {
        preferences.edit().putString(PASSWORD, password).apply();
    }

    public static String getPassword()
    {
        return  preferences.getString(PASSWORD, "");
    }

    public static String getId()
    {
        return  preferences.getString(ID, "");
    }

    public static void setId(String id)
    {
        preferences.edit().putString(ID, id).apply();
    }

    public static void start() {
    }
}
