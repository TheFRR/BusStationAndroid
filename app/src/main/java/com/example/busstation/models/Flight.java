package com.example.busstation.models;

public class Flight
{
    private String StartPoint;
    private String EndPoint;
    private long DepartureTime;
    private long ArrivalTime;
    private int Cost;

    public Flight(String StartPoint, String EndPoint, long DepartureTime, long ArrivalTime, int Cost)
    {
        this.StartPoint = StartPoint;
        this.EndPoint = EndPoint;
        this.DepartureTime = DepartureTime;
        this.ArrivalTime = ArrivalTime;
        this.Cost = Cost;
    }

    public String getStartPoint() {
        return StartPoint;
    }

    public void setStartPoint(String startPoint) {
        StartPoint = startPoint;
    }

    public String getEndPoint() {
        return EndPoint;
    }

    public void setEndPoint(String endPoint) {
        EndPoint = endPoint;
    }

    public long getDepartureTime() {
        return DepartureTime;
    }

    public void setDepartureTime(long departureTime) {
        DepartureTime = departureTime;
    }

    public long getArrivalTime() {
        return ArrivalTime;
    }

    public void setArrivalTime(long arrivalTime) {
        ArrivalTime = arrivalTime;
    }

    public int getCost() {
        return Cost;
    }

    public void setCost(int cost) {
        Cost = cost;
    }
}
