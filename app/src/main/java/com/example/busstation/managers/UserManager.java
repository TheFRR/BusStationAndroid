package com.example.busstation.managers;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.example.busstation.App;;
import com.example.busstation.callback.RecyclerViewCallback;
import com.example.busstation.database.entities.User;
import com.example.busstation.database.repositories.Repository;
import com.example.busstation.database.repositories.UserRepository;
import com.example.busstation.settings.UserSettings;

import java.util.List;

public class UserManager
{
    App app = App.getInstance();
    Repository<User> repository = new UserRepository(app);
    LifecycleOwner lifecycleOwner;
    private RecyclerViewCallback<User> recyclerViewCallback;

    public void setRecyclerViewCallback(RecyclerViewCallback<User> recyclerViewCallback) {
        this.recyclerViewCallback = recyclerViewCallback;
    }

    public UserManager(LifecycleOwner lifecycleOwner)
    {
        this.lifecycleOwner = lifecycleOwner;
    }

    public void Login(String login, String password)
    {
        repository.getAll().observe(lifecycleOwner, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> user) {
                for (int i = 0; i < user.size(); i++)
                {
                    if (user.get(i).getLogin().equals(login) && user.get(i).getPassword().equals(password))
                    {
                        UserSettings.setLogin(login);
                        UserSettings.setPassword(password);
                        UserSettings.setId(String.valueOf(user.get(i).getId()));
                    }
                }
            }
        });
    }

    public void LogOut()
    {
        UserSettings.setLogin("");
        UserSettings.setPassword("");
        UserSettings.setId("");
    }
}
