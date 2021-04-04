package com.example.studentsurvey.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.studentsurvey.R;

public class Page3Fragment extends Fragment {
    public Page3Fragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d(getString(R.string.DEBUGING_TAG),"FRAGMENT 3");
        return inflater.inflate(R.layout.fragment_page3, container, false);
    }
}