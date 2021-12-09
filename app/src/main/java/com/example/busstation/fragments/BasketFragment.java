package com.example.busstation.fragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.busstation.App;
import com.example.busstation.activities.MainActivity;
import com.example.busstation.activities.PaymentActivity;
import com.example.busstation.R;
import com.example.busstation.database.entities.Ticket;
import com.example.busstation.database.repositories.Repository;
import com.example.busstation.database.repositories.TicketRepository;
import com.example.busstation.managers.UserManager;
import com.example.busstation.adapters.TicketAdapter;
import com.example.busstation.settings.UserSettings;

import java.util.ArrayList;
import java.util.List;

public class BasketFragment extends Fragment implements View.OnClickListener {
    public BasketFragment() { }

    public static BasketFragment newInstance() {
        return new BasketFragment();
    }

    App app = App.getInstance();
    Repository<Ticket> repositoryTicket = new TicketRepository(app);
    UserManager userManager = new UserManager(this);
    ArrayList<Ticket> selectedTickets = new ArrayList<Ticket>();
    private RecyclerView recyclerView;
    private TicketAdapter ticketAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.basket, container, false);
        final Button paymentButton = view.findViewById(R.id.payment_button);
        paymentButton.setEnabled(false);
        TextView buyedTicketsText = view.findViewById(R.id.buyed_tickets_text);
        buyedTicketsText.setVisibility(View.GONE);
        recyclerView = view.findViewById(R.id.orders_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        repositoryTicket.getAll().observe(getViewLifecycleOwner(), new Observer<List<Ticket>>() {
            @Override
            public void onChanged(List<Ticket> tickets) {
                for (int i = 0; i < tickets.size(); i++)
                {
                    Ticket currentTicket = tickets.get(i);
                    if (currentTicket.getUserId() == Integer.parseInt(UserSettings.getId()) && !currentTicket.isPaid())
                    {
                        selectedTickets.add(currentTicket);
                        paymentButton.setEnabled(true);
                        buyedTicketsText.setVisibility(View.VISIBLE);
                    }
                }
                ticketAdapter = new TicketAdapter(getContext(), selectedTickets);
                recyclerView.setAdapter(ticketAdapter);
            }
        });
        final Button logOutButton = view.findViewById(R.id.logout_button_basket);
        logOutButton.setOnClickListener(this);
        paymentButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.logout_button_basket: {
                userManager.LogOut();
                getActivity().finish();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.payment_button: {
                Intent paymentActivity = new Intent(getActivity(), PaymentActivity.class);
                paymentActivity.putExtra("selectedTicketsList", selectedTickets);
                startActivity(paymentActivity);
                break;
            }
        }
    }
}

