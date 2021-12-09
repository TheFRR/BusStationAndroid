package com.example.busstation.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.busstation.App;
import com.example.busstation.R;
import com.example.busstation.database.entities.User;
import com.example.busstation.database.repositories.Repository;
import com.example.busstation.database.repositories.UserRepository;
import com.example.busstation.managers.UserManager;
import com.example.busstation.settings.UserSettings;

import java.util.List;

public class LoginActivity extends AppCompatActivity  {
    App app = App.getInstance();
    UserManager userManager = new UserManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    public void loginOnClick(View view) {
        EditText _login = findViewById(R.id.login);
        EditText _password = findViewById(R.id.password);
        String login = _login.getText().toString();
        String password = _password.getText().toString();
        userManager.Login(login, password);
        Intent intent = new Intent(this, MainActivity.class);
        finish();
        startActivity(intent);
    }

    public void registerOnClick(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        finish();
        startActivity(intent);
    }
}