package com.example.busstation.activities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.example.busstation.R;
import com.example.busstation.fragments.BasketFragment;
import com.example.busstation.fragments.SearchFragment;
import com.example.busstation.fragments.TicketsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FragmentsActivity extends AppCompatActivity {

    private BottomNavigationView.OnItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnItemSelectedListener() {

        Fragment currentFragment;

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_search:
                    loadFragment(SearchFragment.newInstance());
                    currentFragment = SearchFragment.newInstance();
                    return true;
                case R.id.navigation_tickets:
                    Bundle bundle = new Bundle();
                    String startPoint = "", endPoint = "";
                    Spinner StartSpinner = findViewById(R.id.start_point_spinner);
                    if (StartSpinner != null ) startPoint = StartSpinner.getSelectedItem().toString();
                    bundle.putString("startPoint", startPoint);

                    Spinner EndSpinner = findViewById(R.id.end_point_spinner);
                    if (EndSpinner != null) endPoint = EndSpinner.getSelectedItem().toString();
                    bundle.putString("endPoint", endPoint);

                    int startHours, startMinutes, endHours, endMinutes;
                    startHours = startMinutes = endHours = endMinutes = 0;
                    TimePicker firstTimePicker = findViewById(R.id.first_time_picker);
                    TimePicker secondTimePicker = findViewById(R.id.second_time_picker);
                    if (firstTimePicker != null && secondTimePicker != null)
                    {
                        startHours = firstTimePicker.getHour();
                        startMinutes = firstTimePicker.getMinute();
                        endHours = secondTimePicker.getHour();
                        endMinutes = secondTimePicker.getMinute();
                    }

                    bundle.putInt("startHours", startHours);
                    bundle.putInt("startMinutes", startMinutes);
                    bundle.putInt("endHours", endHours);
                    bundle.putInt("endMinutes", endMinutes);

                    TicketsFragment ticketsFragment = new TicketsFragment();
                    ticketsFragment.setArguments(bundle);

                    loadFragment(ticketsFragment);
                    return true;
                case R.id.navigation_basket:
                    loadFragment(BasketFragment.newInstance());
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fl_content, fragment);
        ft.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnItemSelectedListener(mOnNavigationItemSelectedListener);

        loadFragment(SearchFragment.newInstance());
    }
}