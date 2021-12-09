package com.example.busstation.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.busstation.R;
import com.example.busstation.database.entities.Ticket;

import java.util.List;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private final List<Ticket> tickets;

    public TicketAdapter(Context context, List<Ticket> tickets) {
        this.tickets = tickets;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public TicketAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.basket_list_item, parent, false);
        return new TicketAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TicketAdapter.ViewHolder holder, int position) {
        Ticket ticket = tickets.get(position);
        int departureHour = (int) ticket.getStartTime() / 100;
        int departureMinutes = (int) ticket.getStartTime() % 100;
        holder.startPointView.setText(ticket.getStartPoint() + " - " + ticket.getEndPoint());
        holder.departureTimeView.setText(String.valueOf(departureHour + ":" + departureMinutes));
        holder.costView.setText("Цена: " + String.valueOf(ticket.getPrice()) + " рублей");
    }

    @Override
    public int getItemCount() {
        return tickets.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder  {
        final TextView startPointView, departureTimeView, costView;

        public ViewHolder(View view) {
            super(view);
            startPointView = view.findViewById(R.id.start_point_text_basket);
            departureTimeView = view.findViewById(R.id.date_basket);
            costView = view.findViewById(R.id.cost_text_basket);
        }

        public TextView getStartPointView() {
            return startPointView;
        }

        public TextView getDepartureTimeView() {
            return departureTimeView;
        }

        public TextView getCostView() {
            return costView;
        }
    }
}
