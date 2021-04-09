package com.example.studentsurvey.fragment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;

import com.example.studentsurvey.MainViewModel;
import com.example.studentsurvey.R;
import com.example.studentsurvey.databinding.FragmentPage1Binding;
import com.example.studentsurvey.model.StudentDetails;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Arrays;
import java.util.Collections;


public class Page1Fragment extends Fragment {
    FragmentPage1Binding mFragmentPage1Binding;
    ArrayAdapter<String> nationalityAdapter;
    ArrayAdapter<String> districtAdapter;
    ArrayAdapter<String> instituteName;
    ArrayAdapter<String> departmentName;
    MainViewModel mMainViewModel;
    //NavController navController;

    public Page1Fragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(getString(R.string.DEBUGING_TAG),"page1 oncreat save:"+(savedInstanceState==null? "null":"not null"));

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d("DEBUGING_TAG","page1 onattach ");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(getString(R.string.DEBUGING_TAG),"FRAGMENT 1 onCreatView: "+(savedInstanceState==null? "null":"not null"));
        mFragmentPage1Binding = FragmentPage1Binding.inflate(inflater, container, false);


        return mFragmentPage1Binding.getRoot();
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(getString(R.string.DEBUGING_TAG),"page1 onViewCreat save:"+(savedInstanceState==null? "null":"not null"));
        super.onViewCreated(view, savedInstanceState);
        initViewModel();
        Log.d(getString(R.string.DEBUGING_TAG),"page 1 onNextObserver bool: "+MainViewModel.callNextButtonClickObserver);
//        if(MainViewModel.callNextButtonClickObserver==false){
//
//            MainViewModel.callNextButtonClickObserver=true;
//        }
        nextButtonClickObserver();

        //initNavController();
        setCurrentSelectedFragment(MainViewModel.FRAGMENT_TAGS.FRAGMENT1);
        initNationality();
        initDistrictDropDown();
        initInstituteNameDropDown();
        initDepartmentDropDown();

        genderGroupOnClick();
        //Log.d(getString(R.string.DEBUGING_TAG),"current frag in view by nav: "+navController.getCurrentDestination());

        nationalityDropDownOnClick();
        placeOfBirthDropDownOnClick();
        instituteNameDropDownOnClick();
        departmentNameDropDownOnClick();


    }

    private void nationalityDropDownOnClick(){
        mFragmentPage1Binding.nationalityDropDown.setOnClickListener( view -> {
            showErrorInTextInputLayout(mFragmentPage1Binding.nationalityTextLayout,null);
            Log.d(getString(R.string.DEBUGING_TAG),"nationality click");
            nationalityAdapter.getFilter().filter(null);
        });
    }
    private void placeOfBirthDropDownOnClick(){
        mFragmentPage1Binding.placeOfBirthDropDown.setOnClickListener(view -> {

            showErrorInTextInputLayout(mFragmentPage1Binding.placeOfBirthTextLayout,null);
            districtAdapter.getFilter().filter(null);
        });
    }


    private void instituteNameDropDownOnClick(){
        mFragmentPage1Binding.instituteNameDropDown.setOnClickListener(view -> {
            showErrorInTextInputLayout(mFragmentPage1Binding.instituteNameTextLayout,null);
            instituteName.getFilter().filter(null);
        });
    }private void departmentNameDropDownOnClick(){
        mFragmentPage1Binding.departmentNameDropDown.setOnClickListener(view -> {
            showErrorInTextInputLayout(mFragmentPage1Binding.departmentNameTextLayout,null);
            departmentName.getFilter().filter(null);
        });
    }


    private void nextButtonClickObserver(){
//        mMainViewModel.nextButtonClick.observe(this.requireActivity(), new Observer<MainViewModel.FRAGMENT_TAGS>() {
//            @Override
//            public void onChanged(MainViewModel.FRAGMENT_TAGS fragment_tags) {
//                if(mMainViewModel.currentFragment.getValue()!=MainViewModel.FRAGMENT_TAGS.FRAGMENT1){
//                    return;
//                }
//                //Log.d(getString(R.string.DEBUGING_TAG),"on change nextclicked: "+mMainViewModel.nextButtonClick.getValue());
//                //Log.d(getString(R.string.DEBUGING_TAG),"current in onchange frag by nav: "+navController.getCurrentDestination());
//                if(mMainViewModel.nextButtonClick.getValue().equals(MainViewModel.FRAGMENT_TAGS.FRAGMENT1)){
//                    checkInputAndShowError();
//                }
//            }
//        });
    }
    private void setCurrentSelectedFragment(MainViewModel.FRAGMENT_TAGS selectedFragment){
        mMainViewModel.currentFragment.setValue(selectedFragment);
    }

    public boolean checkInputAndShowError() {
        //Log.d(getString(R.string.DEBUGING_TAG),"slected gender id: "+getSelectedGenderRadioId());
        if (!isGenderSelected()) {
            changeGenderErrorViewVisibility(View.VISIBLE);
            return false;

        }

        else if(!isNationalitySelected()){
            showErrorInTextInputLayout(mFragmentPage1Binding.nationalityTextLayout,"Required Input");
            return false;
        }

        else if(!isPlaceOfBirthSelected()){
            showErrorInTextInputLayout(mFragmentPage1Binding.placeOfBirthTextLayout,"Required Input");
            return false;
        }
        else if(!isInstituteNameSelected()){
            showErrorInTextInputLayout(mFragmentPage1Binding.instituteNameTextLayout,"Required Input");
            return false;
        }
        else if(!isDepartmentNameSelected()){
            showErrorInTextInputLayout(mFragmentPage1Binding.departmentNameTextLayout,"Required Input");
            return false;
        }

        else {
            mMainViewModel.studentDetails=getInfoFromView();
            //Log.d(getString(R.string.DEBUGING_TAG),"current in check frag by nav: "+mMainViewModel.currentFragment.getValue());
            //showFragment(R.id.action_page1Fragment_to_page2Fragment);
            //getParentFragmentManager().beginTransaction().replace(R.id.fragment_container,new Page2Fragment()).commit();
            setCurrentSelectedFragment(MainViewModel.FRAGMENT_TAGS.FRAGMENT2);
            return true;


        }

    }
    private StudentDetails getInfoFromView(){
        StudentDetails studentDetails = new StudentDetails();
        studentDetails.setGender(getSelectedGenderRadioId()==R.id.radio_button_male? 0:1);
        studentDetails.setNationality(getNationality());
        studentDetails.setPlace_of_birth(getPlaceOfBirth());
        studentDetails.setInstitute(getInstituteName());
        studentDetails.setDepartment(getDepartmentName());


        return studentDetails;
    }

    private void showErrorInTextInputLayout(TextInputLayout textInputLayout, String message){
        textInputLayout.setError(message);
    }

    private void genderGroupOnClick(){
        mFragmentPage1Binding.radioGroupGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.d(getString(R.string.DEBUGING_TAG),"gender group click");
                if(isGenderSelected()){
                    changeGenderErrorViewVisibility(View.GONE);
                }
            }
        });
    }




    private boolean isGenderSelected(){
        if(getSelectedGenderRadioId()==-1){
            return false;
        }
        else {
            return true;
        }
    }
    private int getSelectedGenderRadioId(){
        return mFragmentPage1Binding.radioGroupGender.getCheckedRadioButtonId();
    }
    private void changeGenderErrorViewVisibility(int visibility){
        mFragmentPage1Binding.genderRadioErrorView.setVisibility(visibility);
    }

    private String getNationality(){
        return mFragmentPage1Binding.nationalityDropDown.getText().toString();
    }
    private boolean isNationalitySelected(){
        return !getNationality().equals("Select");
    }
    private String getPlaceOfBirth(){
        return mFragmentPage1Binding.placeOfBirthDropDown.getText().toString();
    }
    private boolean isPlaceOfBirthSelected(){
        return !getPlaceOfBirth().equals("Select");
    }
    private String getInstituteName(){
        return mFragmentPage1Binding.instituteNameDropDown.getText().toString();
    }
    private boolean isInstituteNameSelected(){
        return !(getInstituteName().equals("Select"));
    }
    private String getDepartmentName(){
        return mFragmentPage1Binding.departmentNameDropDown.getText().toString();
    }
    private boolean isDepartmentNameSelected(){
        return !(getDepartmentName().equals("Select"));
    }





    private void initNationality(){
        Collections.sort(mMainViewModel.nationalityArrayList);
        nationalityAdapter = new ArrayAdapter<String>(this.getContext(), R.layout.drop_down_item,mMainViewModel.nationalityArrayList);
        mFragmentPage1Binding.nationalityDropDown.setAdapter(nationalityAdapter);

    }
    private void initDistrictDropDown(){
        String[] dis = getResources().getStringArray(R.array.bd_districts);
        Arrays.sort(dis);
        mMainViewModel.districtArrayList = Arrays.asList(dis);

        districtAdapter = new ArrayAdapter<String>(this.getContext(), R.layout.drop_down_item,mMainViewModel.districtArrayList);
        mFragmentPage1Binding.placeOfBirthDropDown.setAdapter(districtAdapter);

    }
    private void initInstituteNameDropDown(){
        Collections.sort(mMainViewModel.instituteNameArrayList);
        instituteName = new ArrayAdapter<String>(this.getContext(), R.layout.drop_down_item,mMainViewModel.instituteNameArrayList);
        mFragmentPage1Binding.instituteNameDropDown.setAdapter(instituteName);
        //mFragmentPage1Binding.instituteNameDropDown.setText(mMainViewModel.instituteNameArrayList.get(0));

    }
    private void initDepartmentDropDown(){
        Collections.sort(mMainViewModel.departmentNameArrayList);
        departmentName = new ArrayAdapter<String>(this.getContext(), R.layout.drop_down_item,mMainViewModel.departmentNameArrayList);
        mFragmentPage1Binding.departmentNameDropDown.setAdapter(departmentName);

    }
    private void initViewModel(){
        mMainViewModel = new ViewModelProvider(requireActivity())
                .get(MainViewModel.class);
    }
//    private void initNavController(){
//        try {
////            navController = ((NavHostFragment)getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_container))
////                    .getNavController();
//            navController = Navigation.findNavController(getView());
//        }
//        catch (Exception e){
//            Log.d(getString(R.string.DEBUGING_TAG),"exception: "+e.getMessage());
//        }
//
//    }
//    private void showFragment(@IdRes int actionId){
//        navController.navigate(actionId);
//    }
}