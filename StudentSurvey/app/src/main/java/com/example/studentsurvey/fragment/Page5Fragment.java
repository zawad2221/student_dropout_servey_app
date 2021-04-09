package com.example.studentsurvey.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.studentsurvey.MainViewModel;
import com.example.studentsurvey.R;
import com.example.studentsurvey.databinding.FragmentPage5Binding;


public class Page5Fragment extends Fragment {
    private FragmentPage5Binding mFragmentPage5Binding;
    private MainViewModel mMainViewModel;

    public Page5Fragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       mFragmentPage5Binding = FragmentPage5Binding
               .inflate(inflater, container, false);
       initViewModel();
        return mFragmentPage5Binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void initViewModel(){
        mMainViewModel = new ViewModelProvider(getActivity())
                .get(MainViewModel.class);
    }
}