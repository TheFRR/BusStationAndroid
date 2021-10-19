package com.example.busstation;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ArrayAdapter;

public class BasketActivity extends Fragment {

    public BasketActivity() {
    }

    public static BasketActivity newInstance() {
        return new BasketActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.basket, container, false);

        String[] orderList = { "Заказ №1", "Заказ №2", "Заказ №3" };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getContext(),
                android.R.layout.simple_expandable_list_item_1,
                orderList
        );

        ListView listView_aac = (ListView) view.findViewById(R.id.orders_list);
        listView_aac.setAdapter(adapter);
        return view;
    }

}
