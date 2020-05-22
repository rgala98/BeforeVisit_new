package com.beforevisit.beforevisit;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class FeedbackFragment extends Fragment {
    public static final String TAG = "ProfileFragment";
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_feedback, container, false);
        Log.i(TAG,"In Profile Fragment");
        return view;
    }
}
