package com.example.busstation.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.busstation.App;
import com.example.busstation.R;
import com.example.busstation.database.entities.Ticket;
import com.example.busstation.database.repositories.Repository;
import com.example.busstation.database.repositories.TicketRepository;

import java.util.ArrayList;

public class PaymentActivity extends AppCompatActivity {
    App app = App.getInstance();
    Repository<Ticket> repository = new TicketRepository(app);
    ArrayList<Ticket> tickets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Bundle arguments = getIntent().getExtras();
        if (arguments != null)
        {
            int totalCost = 0;
            tickets = (ArrayList<Ticket>) arguments.getSerializable("selectedTicketsList");
            for (int i = 0; i < tickets.size(); i++)
                totalCost += tickets.get(i).getPrice();
            TextView finalCost = findViewById(R.id.final_cost);
            finalCost.setText(String.valueOf(totalCost) + " руб.");
        }
    }

    public void payOnClick(View view)
    {
        if (tickets != null)
        {
            for (int i = 0; i < tickets.size(); i++)
            {
                Ticket currentTicket = tickets.get(i);
                currentTicket.setPaid(true);
                repository.update(currentTicket);
            }
            finish();
            Intent fragmentsActivity = new Intent(this, FragmentsActivity.class);
            startActivity(fragmentsActivity);
        }
    }

    public void goBackOnClick(View view)
    {
        Intent fragmentsActivity = new Intent(this, FragmentsActivity.class);
        startActivity(fragmentsActivity);
    }
}