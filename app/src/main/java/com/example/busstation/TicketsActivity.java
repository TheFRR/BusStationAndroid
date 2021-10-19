package com.example.busstation;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TicketsActivity extends Fragment {
    public TicketsActivity() {
    }

    public static TicketsActivity newInstance() {
        return new TicketsActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tickets, container, false);
    }
}
