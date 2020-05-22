package com.beforevisit.beforevisit;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class SavedPlacesFragment extends Fragment {
    public static final String TAG = "SavedPlacesFragment";
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_saved_places, container, false);
        return view;
    }
}
