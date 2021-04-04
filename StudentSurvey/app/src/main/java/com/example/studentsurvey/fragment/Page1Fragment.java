package com.example.studentsurvey.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.studentsurvey.MainViewModel;
import com.example.studentsurvey.R;
import com.example.studentsurvey.databinding.FragmentPage1Binding;


public class Page1Fragment extends Fragment {
    FragmentPage1Binding mFragmentPage1Binding;
    ArrayAdapter<String> nationalityAdapter;
    ArrayAdapter<String> districtAdapter;
    ArrayAdapter<String> instituteName;
    ArrayAdapter<String> departmentName;
    MainViewModel mMainViewModel;

    public Page1Fragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(getString(R.string.DEBUGING_TAG),"FRAGMENT 1");
        mFragmentPage1Binding = FragmentPage1Binding.inflate(inflater, container, false);

        return mFragmentPage1Binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewModel();
        initNationality();
        initDistrictDropDown();
        initInstituteNameDropDown();
        initDepartmentDropDown();
    }

    private void initNationality(){
        nationalityAdapter = new ArrayAdapter<String>(this.getContext(), R.layout.drop_down_item,mMainViewModel.nationalityArrayList);
        mFragmentPage1Binding.nationalityDropDown.setAdapter(nationalityAdapter);

    }
    private void initDistrictDropDown(){
        districtAdapter = new ArrayAdapter<String>(this.getContext(), R.layout.drop_down_item,mMainViewModel.districtArrayList);
        mFragmentPage1Binding.placeOfBirthDropDown.setAdapter(districtAdapter);

    }
    private void initInstituteNameDropDown(){
        instituteName = new ArrayAdapter<String>(this.getContext(), R.layout.drop_down_item,mMainViewModel.instituteNameArrayList);
        mFragmentPage1Binding.instituteNameDropDown.setAdapter(instituteName);

    }
    private void initDepartmentDropDown(){
        departmentName = new ArrayAdapter<String>(this.getContext(), R.layout.drop_down_item,mMainViewModel.departmentNameArrayList);
        mFragmentPage1Binding.departmentNameDropDown.setAdapter(departmentName);

    }
    private void initViewModel(){
        mMainViewModel = new ViewModelProvider(this)
                .get(MainViewModel.class);
    }
}