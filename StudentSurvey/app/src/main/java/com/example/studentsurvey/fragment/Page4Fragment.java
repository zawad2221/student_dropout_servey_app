package com.example.studentsurvey.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.studentsurvey.MainViewModel;
import com.example.studentsurvey.R;
import com.example.studentsurvey.databinding.FragmentPage4Binding;

public class Page4Fragment extends Fragment {
    FragmentPage4Binding mFragmentPage4Binding;
    MainViewModel mMainViewModel;
    ArrayAdapter<String> dropReasonAdapter;
    public Page4Fragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d(getString(R.string.DEBUGING_TAG),"FRAGMENT 4");
        mFragmentPage4Binding = FragmentPage4Binding.inflate(inflater, container, false);
        return mFragmentPage4Binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewModel();
        initDropReasonDropDown();
    }

    private void initDropReasonDropDown(){
        dropReasonAdapter = new ArrayAdapter<String>(this.getContext(), R.layout.drop_down_item,mMainViewModel.dropReasonArrayList);
        mFragmentPage4Binding.dropReasonDropDown.setAdapter(dropReasonAdapter);

    }
    private void initViewModel(){
        mMainViewModel = new ViewModelProvider(this)
                .get(MainViewModel.class);
    }
}