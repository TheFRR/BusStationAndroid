package com.example.busstation.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.busstation.App;
import com.example.busstation.R;
import com.example.busstation.activities.MainActivity;
import com.example.busstation.database.entities.User;
import com.example.busstation.database.repositories.Repository;
import com.example.busstation.database.repositories.UserRepository;
import com.example.busstation.settings.UserSettings;

public class RegisterActivity extends AppCompatActivity {
    App app = App.getInstance();
    Repository<User> repository = new UserRepository(app);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
    }

    public void registerOnClick(View view) {
        EditText login = findViewById(R.id.login_register);
        EditText password = findViewById(R.id.password_register);
        User user = new User(login.getText().toString(), password.getText().toString(), true);
        repository.insert(user);
        Intent intent = new Intent(this,  LoginActivity.class);
        finish();
        startActivity(intent);
    }
}