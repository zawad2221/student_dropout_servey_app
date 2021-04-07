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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.studentsurvey.MainViewModel;
import com.example.studentsurvey.R;
import com.example.studentsurvey.databinding.FragmentPage4Binding;
import com.google.android.material.textfield.TextInputLayout;

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

        dueAmountKeyChange();
        dropReasonSelect();
        numOfDropSemesterKeyChange();
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
        if(!isDropSemesterNumValid()){
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
    private boolean isDueAmountValid(){
        return !getDueAmount().isEmpty();
    }

    private void initDropReasonDropDown(){
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
}