package com.example.busstation.database.dao_interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.busstation.database.entities.Ticket;
import java.util.List;

@Dao
public interface TicketDao {
    @Query("SELECT * FROM ticket")
    LiveData<List<Ticket>> getAll();

    @Query("SELECT * FROM ticket WHERE id = :id")
    LiveData<Ticket> getById(long id);

    @Query("SELECT COUNT(*) FROM ticket")
    int getCount();

    @Insert
    void insert(Ticket ticket);

    @Update
    void update(Ticket ticket);

    @Delete
    void delete(Ticket ticket);
}
