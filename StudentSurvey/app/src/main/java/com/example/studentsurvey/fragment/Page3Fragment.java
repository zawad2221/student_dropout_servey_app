package com.example.studentsurvey.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.studentsurvey.MainViewModel;
import com.example.studentsurvey.R;
import com.example.studentsurvey.databinding.FragmentPage3Binding;
import com.google.android.material.textfield.TextInputLayout;

public class Page3Fragment extends Fragment {
    MainViewModel mMainViewModel;
    FragmentPage3Binding mFragmentPage3Binding;
    //NavController navController;
    public Page3Fragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d(getString(R.string.DEBUGING_TAG),"onCreateView FRAGMENT 3");
        mFragmentPage3Binding = FragmentPage3Binding.inflate(inflater, container, false);

        return mFragmentPage3Binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewModel();
        //initNavController();
//        if(MainViewModel.callNextButtonClickObserver3==false){nextButtonClickObserver();
//        MainViewModel.callNextButtonClickObserver3=true;}
        nextButtonClickObserver();
        Log.d(getString(R.string.DEBUGING_TAG),"onViewCreated page 3student info: "+mMainViewModel.studentDetails.getYear());

        
        
        studyMaterialRadioOnClicked();
        resultOnLastSemesterOnKeyChange();
        advisedRadioClicked();
        
        satisfactionChecked();


    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(getString(R.string.DEBUGING_TAG),"on attach page2");
    }
    private void studyMaterialRadioOnClicked(){
        mFragmentPage3Binding.radioGroupUsedAdditionalMaterial.setOnCheckedChangeListener((group, checkedId)->{
            if(isAdditionalResourceSelected())changeVisibilityOfErrorView(mFragmentPage3Binding.additionalMaterialRadioErrorView,View.GONE);
        });
    }
    private void resultOnLastSemesterOnKeyChange(){
        mFragmentPage3Binding.lastSemesterCGPAEditText.setOnKeyListener((v, keyCode, event)->{
            showErrorInTextInputLayout(mFragmentPage3Binding.lastSemesterCGPAOutlinedTextFieldLayout,null);
            return false;
        });
    }
    private void advisedRadioClicked(){
        mFragmentPage3Binding.radioGroupMeetWithAdvisor.setOnCheckedChangeListener((group, checkedId)->{
            if(isMeetWithAdvisorSelected()) changeVisibilityOfErrorView(mFragmentPage3Binding.advisorRadioErrorView,View.GONE);
        });
    }

    private void satisfactionChecked(){
        mFragmentPage3Binding.radioGroupParentSatisfaction.setOnCheckedChangeListener((group, checkedId)->{
            if(isSatisfactionSelected()) changeVisibilityOfErrorView(mFragmentPage3Binding.satisfactionRadioErrorView,View.GONE);
        });
    }

    private void nextButtonClickObserver(){
//        mMainViewModel.nextButtonClick.observe(this.requireActivity(), new Observer<MainViewModel.FRAGMENT_TAGS>() {
//            @Override
//            public void onChanged(MainViewModel.FRAGMENT_TAGS fragment_tags) {
//                if(mMainViewModel.currentFragment.getValue()!= MainViewModel.FRAGMENT_TAGS.FRAGMENT3){
//                    return;
//                }
//                //Log.d(getString(R.string.DEBUGING_TAG),"on change nextclicked: "+mMainViewModel.nextButtonClick.getValue());
//                //Log.d(getString(R.string.DEBUGING_TAG),"current in onchange frag by nav: "+navController.getCurrentDestination());
//                if(mMainViewModel.nextButtonClick.getValue().equals(MainViewModel.FRAGMENT_TAGS.FRAGMENT3)){
//                    checkInputAndShowError();
//                }
//            }
//        });
    }

    public boolean checkInputAndShowError(){
        
        if(!isAdditionalResourceSelected()){
            changeVisibilityOfErrorView(mFragmentPage3Binding.additionalMaterialRadioErrorView,View.VISIBLE);
            return false;
        }
        else if(!isCgpaValid()){
            showErrorInTextInputLayout(mFragmentPage3Binding.lastSemesterCGPAOutlinedTextFieldLayout,"Invalid Input");
            return false;
        }
        else if(!isMeetWithAdvisorSelected()){
            changeVisibilityOfErrorView(mFragmentPage3Binding.advisorRadioErrorView,View.VISIBLE);
            return false;
        }
        else if(!isSatisfactionSelected()){
            changeVisibilityOfErrorView(mFragmentPage3Binding.satisfactionRadioErrorView,View.VISIBLE);
            return false;
        }

        else {
            saveDataInViewModel();
            //showFragment(R.id.action_page3Fragment_to_page4Fragment);
            //getChildFragmentManager().beginTransaction().replace(R.id.fragment_container,new Page4Fragment()).commit();
            setCurrentSelectedFragment(MainViewModel.FRAGMENT_TAGS.FRAGMENT4);
            return true;
        }
    }

    private void saveDataInViewModel(){
        mMainViewModel.studentDetails.setUse_additional_course_material(getAdditionalResourceSelectedId()==R.id.radio_button_used_additional_yes?1:0);
        mMainViewModel.studentDetails.setMeet_with_advisor(getMeetWithAdvisorId()==R.id.radio_button_meet_advisor_yes?1:0);
        mMainViewModel.studentDetails.setResult_of_last_semester(getResultOfLastSemester());
        mMainViewModel.studentDetails.setParent_satisfied(getParentSatisfactionSelectedId()==R.id.radio_button_parent_satisfaction_yes?1:0);

    }
    private int getAdditionalResourceSelectedId(){
        return mFragmentPage3Binding.radioGroupUsedAdditionalMaterial.getCheckedRadioButtonId();
    }
    private boolean isAdditionalResourceSelected(){
        if(getAdditionalResourceSelectedId()==-1){
            return false;
        }
        else {
            return true;
        }
    }
    private String getResultOfLastSemester(){
        return mFragmentPage3Binding.lastSemesterCGPAEditText.getText().toString();
    }
    private boolean isCgpaValid(){
        if(getResultOfLastSemester().isEmpty()){
            Log.d(getString(R.string.DEBUGING_TAG),"if 1 ");
            return false;
        }
        else if(getResultOfLastSemester().length()>1){
            Log.d(getString(R.string.DEBUGING_TAG),"if 2 ");
            try{
                Log.d(getString(R.string.DEBUGING_TAG),"if 3 ");
                String[] c = getResultOfLastSemester().split("\\.");
                if(c[0].length()==1&&Integer.parseInt(c[0])<4){
                    Log.d(getString(R.string.DEBUGING_TAG),"if 4 ");
                    return true;
                }
                else {
                    return false;
                }
            }
            catch (Exception e){
                Log.d(getString(R.string.DEBUGING_TAG),"splite excep: "+e.getMessage());
                return false;
            }
        }
        else if(getResultOfLastSemester().length()==1 && Integer.parseInt(getResultOfLastSemester())>4){
            Log.d(getString(R.string.DEBUGING_TAG),"if 5 ");
            return false;
        }



        return true;
    }
    private int getMeetWithAdvisorId(){
        return mFragmentPage3Binding.radioGroupMeetWithAdvisor.getCheckedRadioButtonId();
    }
    private boolean isMeetWithAdvisorSelected(){
        if(getMeetWithAdvisorId()==-1){
            return false;
        }
        else {
            return true;
        }
    }

    private int getParentSatisfactionSelectedId(){
        return mFragmentPage3Binding.radioGroupParentSatisfaction.getCheckedRadioButtonId();
    }
    private boolean isSatisfactionSelected(){
        if(getParentSatisfactionSelectedId()==-1){
            return false;
        }
        else {
            return true;
        }
    }




    private void changeVisibilityOfErrorView(LinearLayout linearLayout, int visibility){
        linearLayout.setVisibility(visibility);
        initViewModel();
        //initNavController();
        Log.d(getString(R.string.DEBUGING_TAG),"page 3 student info: "+mMainViewModel.studentDetails.getYear());
    }
//    private void showFragment(@IdRes int actionId){
//        navController.navigate(actionId);
//    }
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
    private void initViewModel(){
        mMainViewModel = new ViewModelProvider(getActivity())
                .get(MainViewModel.class);
    }
    private void showErrorInTextInputLayout(TextInputLayout textInputLayout, String message){
        textInputLayout.setError(message);
    }



}