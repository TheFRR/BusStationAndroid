package com.example.busstation.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.busstation.App;
import com.example.busstation.R;
import com.example.busstation.database.entities.Ticket;
import com.example.busstation.database.repositories.Repository;
import com.example.busstation.database.repositories.TicketRepository;
import com.example.busstation.managers.UserManager;
import com.example.busstation.models.Flight;
import com.example.busstation.adapters.FlightAdapter;
import com.example.busstation.settings.UserSettings;

import java.util.ArrayList;

public class TicketsFragment extends Fragment {
    ArrayList<Flight> flights = new ArrayList<Flight>();
    private RecyclerView recyclerView;
    App app = App.getInstance();
    Repository<Ticket> repository = new TicketRepository(app);
    String[] startPoints;
    String[] endPoints;

    public TicketsFragment() { }

    public static TicketsFragment newInstance() {
        return new TicketsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tickets, container, false);
        TextView mockText = view.findViewById(R.id.mock_tickets);
        startPoints = getResources().getStringArray(R.array.startCitiesNames);
        endPoints = getResources().getStringArray(R.array.endCitiesNames);
        String startPoint, endPoint;
        int startHours, startMinutes, endHours, endMinutes;
        if (getArguments() != null) {
            startPoint = getArguments().getString("startPoint");
            endPoint = getArguments().getString("endPoint");

            startHours = getArguments().getInt("startHours");
            startMinutes = getArguments().getInt("startMinutes");
            endHours = getArguments().getInt("endHours");
            endMinutes =  getArguments().getInt("endMinutes");
        }
        else
        {
            startPoint = endPoint = "";
            startHours = startMinutes = endHours = endMinutes = 0;
        }
        setInitialData();
        ArrayList<Flight> selectedFlights = new ArrayList<Flight>();
        for (int i = 0; i < flights.size(); i++)
        {
            Flight currentFlight = flights.get(i);
            int departureHour = (int) currentFlight.getDepartureTime() / 100;
            int departureMinutes = (int) currentFlight.getDepartureTime() % 100;
            int arrivalHour = (int) currentFlight.getArrivalTime() / 100;
            int arrivalMinutes = (int) currentFlight.getArrivalTime() % 100;
            boolean isStartTimeOK = (startHours ==  departureHour) ||
                    (departureHour == (startHours - 1) % 24 && startMinutes <= departureMinutes) ||
                    (departureHour == (startHours + 1) % 24 && startMinutes >= departureMinutes);
            boolean isEndTimeOK = (endHours ==  arrivalHour) ||
                    (arrivalHour == (endHours - 1) % 24 && endMinutes <= arrivalMinutes) ||
                    (arrivalHour == (endHours + 1) % 24 && endMinutes >= arrivalMinutes);
            if (currentFlight.getStartPoint().equals(startPoint) && currentFlight.getEndPoint().equals(endPoint) && isStartTimeOK && isEndTimeOK)
            {
                selectedFlights.add(currentFlight);
                mockText.setVisibility(View.GONE);
            }
        }
        recyclerView = view.findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        FlightAdapter flightAdapter = new FlightAdapter(this.getContext(), selectedFlights);
        flightAdapter.setRecyclerViewCallback(position ->
        {
            Flight flight = selectedFlights.get(position);
            int currentUserId = Integer.parseInt(UserSettings.getId());
            repository.insert(new Ticket(flight.getStartPoint(), flight.getEndPoint(), flight.getArrivalTime(), flight.getDepartureTime(), flight.getCost(), false, currentUserId));
        });
        recyclerView.setAdapter(flightAdapter);
        return view;
    }

    private void setInitialData() {
        for (int i = 0; i < startPoints.length; i++)
        {
            for (int j = 0; j < endPoints.length; j++)
            {
                long departureTime = j % 2 == 0 ? 1030 : 1230;
                long arrivalTime = j % 3 == 0 ? 1530 : 1730;
                int cost = 100 * (j + 1);
                Flight newFlight = new Flight(startPoints[i], endPoints[j], departureTime, arrivalTime, cost);
                Log.i("myLogs", newFlight.getStartPoint() + newFlight.getEndPoint() + String.valueOf(newFlight.getDepartureTime()) + String.valueOf(newFlight.getArrivalTime()));
                flights.add(newFlight);
            }
        }
    }
}
