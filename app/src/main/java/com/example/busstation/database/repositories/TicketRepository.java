package com.example.busstation.database.repositories;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.example.busstation.database.AppDatabase;
import com.example.busstation.database.dao_interfaces.TicketDao;
import com.example.busstation.database.entities.Ticket;

import java.util.List;

import androidx.annotation.Nullable;

public class TicketRepository implements Repository<Ticket> {

    private final TicketDao ticketDao;
    private final LiveData<List<Ticket>> allTickets;

    public TicketRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        ticketDao = db.ticketDao();
        allTickets = ticketDao.getAll();
    }

    public LiveData<List<Ticket>> getAll() {
        return allTickets;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    public LiveData<Ticket> getById(long id) {
        return Transformations.map(allTickets, input -> input.stream()
                .filter(m -> m.getId() == id)
                .findAny()
                .orElse(null)
        );
    }

    public void insert(Ticket ticket) {
        AppDatabase.databaseWriteExecutor.execute(() -> ticketDao.insert(ticket));
    }

    public void update(Ticket ticket) {
        AppDatabase.databaseWriteExecutor.execute(() -> ticketDao.update(ticket));
    }

    public void delete(Ticket ticket) {
        AppDatabase.databaseWriteExecutor.execute(() -> ticketDao.delete(ticket));
    }
}
