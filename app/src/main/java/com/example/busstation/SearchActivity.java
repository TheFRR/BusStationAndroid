package com.example.busstation;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SearchActivity extends Fragment {
    public SearchActivity() {
    }

    public static SearchActivity newInstance() {
        return new SearchActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.search, container, false);
    }
}
