package com.example.busstation.activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.busstation.settings.UserSettings;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent loginActivity = new Intent(this, LoginActivity.class);
        Intent fragmentsActivity = new Intent(this, FragmentsActivity.class);

        if (!UserSettings.getLogin().equals("") && !UserSettings.getPassword().equals(""))
            startActivity(fragmentsActivity);
        else startActivity(loginActivity);
    }
}