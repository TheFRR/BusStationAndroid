package com.example.busstation.database.entities;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "ticket")
public class Ticket implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String startPoint;
    private String endPoint;
    private long startTime;
    private long endTime;
    private int price;
    private boolean isPaid;
    private long userId;

    @Ignore
    public Ticket(String startPoint, String endPoint, long startTime, long endTime, int price, boolean isPaid, long userId)
    {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
        this.isPaid = isPaid;
        this.userId = userId;
    }

    public Ticket(long id, String startPoint, String endPoint, long startTime, long endTime, int price, boolean isPaid, long userId)
    {
        this.id = id;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
        this.isPaid = isPaid;
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }
}

