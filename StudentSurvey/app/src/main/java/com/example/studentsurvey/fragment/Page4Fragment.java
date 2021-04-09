package com.example.studentsurvey.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.studentsurvey.MainViewModel;
import com.example.studentsurvey.R;
import com.example.studentsurvey.databinding.FragmentPage4Binding;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Collections;

public class Page4Fragment extends Fragment {
    FragmentPage4Binding mFragmentPage4Binding;
    MainViewModel mMainViewModel;
    ArrayAdapter<String> dropReasonAdapter;
    //NavController navController;
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
        //initNavController();
        initDropReasonDropDown();
//        if(MainViewModel.callNextButtonClickObserver4==false){nextButtonClickObserver();
//        MainViewModel.callNextButtonClickObserver4=false;}
        nextButtonClickObserver();

        fatherEduChecked();
        motherEduChecked();

        dueAmountKeyChange();
        dropReasonSelect();
        numOfDropSemesterKeyChange();
    }

    private void fatherEduChecked(){
        mFragmentPage4Binding.radioGroupFatherEduStatus.setOnCheckedChangeListener((group, checkedId)->{
            if(isFatherEduSelected()) changeVisibilityOfErrorView(mFragmentPage4Binding.fatherRadioErrorView,View.GONE);
        });
    }
    private void motherEduChecked(){
        mFragmentPage4Binding.radioGroupFatherEduStatus.setOnCheckedChangeListener((group, checkedId)->{
            if(isFatherEduSelected()) changeVisibilityOfErrorView(mFragmentPage4Binding.fatherRadioErrorView,View.GONE);
        });
    }

   private void numOfDropSemesterKeyChange(){
        mFragmentPage4Binding.numOfDropSemesterEditText.setOnKeyListener((v, keyCode, keyEvent)->{
            showErrorInTextInputLayout(mFragmentPage4Binding.numOfDropSemesterOutLineedTextFieldLayout,null);
            return false;
        });
   }
   private void dropReasonSelect(){
        mFragmentPage4Binding.dropReasonDropDown.setOnClickListener(v -> {
            showErrorInTextInputLayout(mFragmentPage4Binding.dropReasonTextLayout,null);
            dropReasonAdapter.getFilter().filter(null);
        });
   }
   private void dueAmountKeyChange(){
        mFragmentPage4Binding.dueAmountEditText.setOnKeyListener((v, ketCode, keyEvent)->{
            showErrorInTextInputLayout(mFragmentPage4Binding.dueAmountOutlinedTextFieldLayout,null);
            return false;
        });
   }

    private void nextButtonClickObserver(){
//        mMainViewModel.nextButtonClick.observe(this.requireActivity(), new Observer<MainViewModel.FRAGMENT_TAGS>() {
//            @Override
//            public void onChanged(MainViewModel.FRAGMENT_TAGS fragment_tags) {
//                if(mMainViewModel.currentFragment.getValue()!= MainViewModel.FRAGMENT_TAGS.FRAGMENT4){
//                    return;
//                }
//                //Log.d(getString(R.string.DEBUGING_TAG),"on change nextclicked: "+mMainViewModel.nextButtonClick.getValue());
//                //Log.d(getString(R.string.DEBUGING_TAG),"current in onchange frag by nav: "+navController.getCurrentDestination());
//                if(mMainViewModel.nextButtonClick.getValue().equals(MainViewModel.FRAGMENT_TAGS.FRAGMENT_SUBMIT)){
//                    checkInputAndShowError();
//                }
//            }
//        });
    }
    public boolean checkInputAndShowError(){
        if(!isFatherEduSelected()){
            changeVisibilityOfErrorView(mFragmentPage4Binding.fatherRadioErrorView,View.VISIBLE);
            return false;
        }
        else if(!isMotherEduSelected()){
            changeVisibilityOfErrorView(mFragmentPage4Binding.motherRadioErrorView,View.VISIBLE);
            return false;
        }
        else if(!isDropSemesterNumValid()){
            showErrorInTextInputLayout(mFragmentPage4Binding.numOfDropSemesterOutLineedTextFieldLayout,"Invalid Input");
            return false;
        }
        else if(!isDropReasonSelected()){
            showErrorInTextInputLayout(mFragmentPage4Binding.dropReasonTextLayout,"Invalid Input");
            return false;
        }
        else if(!isDueAmountValid()){
            showErrorInTextInputLayout(mFragmentPage4Binding.dueAmountOutlinedTextFieldLayout,"Invalid Input");
            return false;
        }
        else {
            saveDataInViewModel();
            setCurrentSelectedFragment(MainViewModel.FRAGMENT_TAGS.FRAGMENT_SUBMIT);
            return true;
        }
    }
    private void saveDataInViewModel(){
        mMainViewModel.studentDetails.setFather_education_status(getSelectedFatherEduStatus());
        mMainViewModel.studentDetails.setMother_education_status(getSelectedMotherEduStatus());
        mMainViewModel.studentDetails.setAmount_of_drop_semester((int)Float.parseFloat(getDropSemesterNumber()));
        mMainViewModel.studentDetails.setDrop_reason(getReasonOfDrop());
        mMainViewModel.studentDetails.setDue_amount(getDueAmount());
    }
    private String getDropSemesterNumber(){
        return mFragmentPage4Binding.numOfDropSemesterEditText.getText().toString();
    }
    private boolean isDropSemesterNumValid(){
        if(getDropSemesterNumber().isEmpty()){
            return false;
        }
        try {
            int val = Integer.parseInt(getDropSemesterNumber());
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    private String getReasonOfDrop(){
        return mFragmentPage4Binding.dropReasonDropDown.getText().toString();
    }
    private boolean isDropReasonSelected(){
        return !getReasonOfDrop().equals("Select");

    }
    private String getDueAmount(){
        return mFragmentPage4Binding.dueAmountEditText.getText().toString();
    }

    private int getFatherEduSelectedId(){
        return mFragmentPage4Binding.radioGroupFatherEduStatus.getCheckedRadioButtonId();
    }
    private boolean isFatherEduSelected(){
        if(getFatherEduSelectedId()==-1){
            return false;
        }
        else {
            return true;
        }
    }
    private int getMotherEduSelectedId(){
        return mFragmentPage4Binding.radioGroupMotherEduStatus.getCheckedRadioButtonId();
    }
    private boolean isMotherEduSelected(){
        if(getFatherEduSelectedId()==-1){
            return false;
        }
        else {
            return true;
        }
    }
    private Integer getSelectedMotherEduStatus(){
        return getMotherEduSelectedId()==R.id.radio_button_mother_graduation? 2:(
                getMotherEduSelectedId()==R.id.radio_button_mother_hsc? 1:0
        );
    }
    private Integer getSelectedFatherEduStatus(){
        return getFatherEduSelectedId()==R.id.radio_button_father_graduation? 2:(
                getFatherEduSelectedId()==R.id.radio_button_father_hsc? 1:0
        );
    }
    private boolean isDueAmountValid(){
        return !getDueAmount().isEmpty();
    }

    private void initDropReasonDropDown(){
        Collections.sort(mMainViewModel.dropReasonArrayList);
        dropReasonAdapter = new ArrayAdapter<String>(this.getContext(), R.layout.drop_down_item,mMainViewModel.dropReasonArrayList);
        mFragmentPage4Binding.dropReasonDropDown.setAdapter(dropReasonAdapter);

    }
    private void initViewModel(){
        mMainViewModel = new ViewModelProvider(getActivity())
                .get(MainViewModel.class);
    }


    private void setCurrentSelectedFragment(MainViewModel.FRAGMENT_TAGS selectedFragment){
        mMainViewModel.currentFragment.setValue(selectedFragment);
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
    private void showErrorInTextInputLayout(TextInputLayout textInputLayout, String message){
        textInputLayout.setError(message);
    }
    private void changeVisibilityOfErrorView(LinearLayout linearLayout, int visibility){
        linearLayout.setVisibility(visibility);
        initViewModel();
        //initNavController();
        Log.d(getString(R.string.DEBUGING_TAG),"page 3 student info: "+mMainViewModel.studentDetails.getYear());
    }
}