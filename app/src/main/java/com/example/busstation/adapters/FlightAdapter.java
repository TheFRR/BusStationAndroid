package com.example.busstation.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.busstation.App;
import com.example.busstation.R;
import com.example.busstation.callback.RecyclerViewCallback;
import com.example.busstation.database.entities.Ticket;
import com.example.busstation.database.repositories.Repository;
import com.example.busstation.database.repositories.TicketRepository;
import com.example.busstation.models.Flight;

import java.util.List;

public class FlightAdapter extends RecyclerView.Adapter<FlightAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private final List<Flight> flights;

    private RecyclerViewCallback<Integer> recyclerViewCallback;

    public void setRecyclerViewCallback(RecyclerViewCallback<Integer> recyclerViewCallback) {
        this.recyclerViewCallback = recyclerViewCallback;
    }

    public FlightAdapter(Context context, List<Flight> flights) {
        this.flights = flights;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public FlightAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.ticket_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FlightAdapter.ViewHolder holder, int position) {
        Flight flight = flights.get(position);
        Button addToBasket = holder.addToBasket;
        addToBasket.setOnClickListener(v -> recyclerViewCallback.onComplete(position));

        int departureHour = (int) flight.getDepartureTime() / 100;
        int departureMinutes = (int) flight.getDepartureTime() % 100;
        int arrivalHour = (int) flight.getArrivalTime() / 100;
        int arrivalMinutes = (int) flight.getArrivalTime() % 100;

        holder.getStartPointView().setText(flight.getStartPoint() + " - " + flight.getEndPoint());
        holder.getDepartureTimeView().setText(String.valueOf(departureHour + ":" + departureMinutes));
        holder.getArrivalTimeView().setText(String.valueOf(arrivalHour + ":" + arrivalMinutes));
        holder.getTotalTimeView().setText(String.valueOf(arrivalHour - departureHour) + " ч " + String.valueOf(arrivalMinutes - departureMinutes) + " мин");
        holder.getCostView().setText("Цена: " + String.valueOf(flight.getCost()) + " рублей");
    }

    @Override
    public int getItemCount() {
        return flights.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView startPointView, departureTimeView, arrivalTimeView, totalTimeView, costView;
        final Button addToBasket;

        App app = App.getInstance();
        Repository<Ticket> repository = new TicketRepository(app);

        public ViewHolder(View view) {
            super(view);
            addToBasket = view.findViewById(R.id.add_to_basket);
            startPointView = view.findViewById(R.id.start_point_text);
            departureTimeView = view.findViewById(R.id.start_time_text);
            arrivalTimeView = view.findViewById(R.id.end_time_text);
            totalTimeView = view.findViewById(R.id.total_time_text);
            costView = view.findViewById(R.id.cost_text);
        }

        public TextView getStartPointView() {
            return startPointView;
        }

        public TextView getDepartureTimeView() {
            return departureTimeView;
        }

        public TextView getArrivalTimeView() {
            return arrivalTimeView;
        }

        public TextView getCostView() {
            return costView;
        }

        public TextView getTotalTimeView() {
            return totalTimeView;
        }
    }
}