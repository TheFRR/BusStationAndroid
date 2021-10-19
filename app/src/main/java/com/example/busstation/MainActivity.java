package com.example.busstation;

import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_search:
                    loadFragment(SearchActivity.newInstance());
                    return true;
                case R.id.navigation_tickets:
                    loadFragment(TicketsActivity.newInstance());
                    return true;
                case R.id.navigation_basket:
                    loadFragment(BasketActivity.newInstance());
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

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnItemSelectedListener(mOnNavigationItemSelectedListener);

        loadFragment(SearchActivity.newInstance());
    }
}