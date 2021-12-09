package com.example.busstation.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.widget.Button;
import android.widget.TimePicker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.busstation.App;
import com.example.busstation.R;
import com.example.busstation.activities.FragmentsActivity;
import com.example.busstation.database.entities.Ticket;
import com.example.busstation.database.repositories.Repository;
import com.example.busstation.database.repositories.TicketRepository;

import java.util.Calendar;

public class SearchFragment extends Fragment  {
    View view;

    public SearchFragment() { }

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.search, container, false);
        return view;
    }

    @Override
    public void onStart()
    {
        super.onStart();
        TimePicker startTimePicker = (TimePicker) view.findViewById(R.id.first_time_picker);
        TimePicker endTimePicker = (TimePicker) view.findViewById(R.id.second_time_picker);
        startTimePicker.setIs24HourView(true);
        startTimePicker.setCurrentHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
        endTimePicker.setIs24HourView(true);
        endTimePicker.setCurrentHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
    }

}
