package com.example.busstation.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.busstation.database.dao_interfaces.TicketDao;
import com.example.busstation.database.dao_interfaces.UserDao;
import com.example.busstation.database.entities.Ticket;
import com.example.busstation.database.entities.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Ticket.class, User.class }, version = 23)
public abstract class AppDatabase extends RoomDatabase {
    private static final int NUMBER_OF_THREADS = 8;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newScheduledThreadPool(NUMBER_OF_THREADS);
    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "app5_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract TicketDao ticketDao();

    public abstract UserDao userDao();
}