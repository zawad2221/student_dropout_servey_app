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
import com.example.studentsurvey.databinding.FragmentPage2Binding;
import com.google.android.material.textfield.TextInputLayout;

public class Page2Fragment extends Fragment {
    MainViewModel mMainViewModel;

    //NavController navController;
    FragmentPage2Binding mFragmentPage2Binding;
    public Page2Fragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d(getString(R.string.DEBUGING_TAG),"onCreateView FRAGMENT 2");
        mFragmentPage2Binding = FragmentPage2Binding.inflate(inflater, container, false);
        return mFragmentPage2Binding.getRoot();
    }
    private void setCurrentSelectedFragment(MainViewModel.FRAGMENT_TAGS selectedFragment){
        mMainViewModel.currentFragment.setValue(selectedFragment);
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(getString(R.string.DEBUGING_TAG),"on attach page2");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewModel();
        //initNavController();
//        if(MainViewModel.callNextButtonClickObserver2==false){nextButtonClickObserver();
//        MainViewModel.callNextButtonClickObserver2=true;}
        nextButtonClickObserver();
        Log.d(getString(R.string.DEBUGING_TAG),"onViewCreated page 2student info: "+mMainViewModel.studentDetails.getYear());

        studyHourOnKeyListener();
        advisedRadioClicked();
        resultOnLastSemesterOnKeyChange();
        studyMaterialRadioOnClicked();
        questionAskedRadioClicked();
        absentOkKeyListener();


    }
    private void studyHourOnKeyListener(){
        mFragmentPage2Binding.groupStudyTimeEditText.setOnKeyListener( (v, keyCode, event) -> {
            showErrorInTextInputLayout(mFragmentPage2Binding.groupStudyTimeOutlinedTextFieldLayout,null);
            return false;
        });
    }
    private void absentOkKeyListener(){
        mFragmentPage2Binding.absentInSemesterEditText.setOnKeyListener((v, keyCode, event) ->{
           showErrorInTextInputLayout( mFragmentPage2Binding.absentInSemesterOutlinedTextFieldLayout,null);
            return false;
        });
    }
    private void questionAskedRadioClicked(){
        mFragmentPage2Binding.radioGroupAskedQuestionFrequently.setOnCheckedChangeListener((group, checkedId)->{
            if(isAskedFrequentlySelected())changeVisibilityOfErrorView(mFragmentPage2Binding.askedQuestionRadioErrorView,View.GONE);
        });
    }
    private void studyMaterialRadioOnClicked(){
        mFragmentPage2Binding.radioGroupUsedAdditionalMaterial.setOnCheckedChangeListener((group, checkedId)->{
            if(isAdditionalResourceSelected())changeVisibilityOfErrorView(mFragmentPage2Binding.additionalMaterialRadioErrorView,View.GONE);
        });
    }
    private void resultOnLastSemesterOnKeyChange(){
        mFragmentPage2Binding.lastSemesterCGPAEditText.setOnKeyListener((v, keyCode, event)->{
            showErrorInTextInputLayout(mFragmentPage2Binding.lastSemesterCGPAOutlinedTextFieldLayout,null);
            return false;
        });
    }
    private void advisedRadioClicked(){
        mFragmentPage2Binding.radioGroupMeetWithAdvisor.setOnCheckedChangeListener((group, checkedId)->{
            if(isMeetWithAdvisorSelected()) changeVisibilityOfErrorView(mFragmentPage2Binding.advisorRadioErrorView,View.GONE);
        });
    }

    private void nextButtonClickObserver(){
//        mMainViewModel.nextButtonClick.observe(this.requireActivity(), new Observer<MainViewModel.FRAGMENT_TAGS>() {
//            @Override
//            public void onChanged(MainViewModel.FRAGMENT_TAGS fragment_tags) {
//                if(mMainViewModel.currentFragment.getValue()!= MainViewModel.FRAGMENT_TAGS.FRAGMENT2){
//                    return;
//                }
//                //Log.d(getString(R.string.DEBUGING_TAG),"on change nextclicked: "+mMainViewModel.nextButtonClick.getValue());
//                //Log.d(getString(R.string.DEBUGING_TAG),"current in onchange frag by nav: "+navController.getCurrentDestination());
//                if(mMainViewModel.nextButtonClick.getValue().equals(MainViewModel.FRAGMENT_TAGS.FRAGMENT2)){
//                    checkInputAndShowError();
//                }
//            }
//        });
    }

    private void initViewModel(){
        mMainViewModel = new ViewModelProvider(requireActivity())
                .get(MainViewModel.class);
    }

    public boolean checkInputAndShowError(){
        Log.d("DEBUGING_TAG","page2 :gpa: "+getGrpStudyTime());

        if(!isGprStudyValid()){
            showErrorInTextInputLayout(mFragmentPage2Binding.groupStudyTimeOutlinedTextFieldLayout,"Required Input");
            return false;
        }
        else if(!isAbsentTimeValid()){
            showErrorInTextInputLayout(mFragmentPage2Binding.absentInSemesterOutlinedTextFieldLayout,"Required Input");
            return false;
        }
        else if(!isAskedFrequentlySelected()){
            changeVisibilityOfErrorView(mFragmentPage2Binding.askedQuestionRadioErrorView,View.VISIBLE);
            return false;
        }
        else if(!isAdditionalResourceSelected()){
            changeVisibilityOfErrorView(mFragmentPage2Binding.additionalMaterialRadioErrorView,View.VISIBLE);
            return false;
        }
        else if(!isCgpaValid()){
            showErrorInTextInputLayout(mFragmentPage2Binding.lastSemesterCGPAOutlinedTextFieldLayout,"Invalid Input");
            return false;
        }
        else if(!isMeetWithAdvisorSelected()){
            changeVisibilityOfErrorView(mFragmentPage2Binding.advisorRadioErrorView,View.VISIBLE);
            return false;
        }
        else {
            //Log.d(getString(R.string.DEBUGING_TAG),"showing page3");
            saveDataInViewModel();
            //showFragment(R.id.action_page2Fragment_to_page3Fragment);
            //getChildFragmentManager().beginTransaction().replace(R.id.fragment_container,new Page3Fragment()).commit();
            setCurrentSelectedFragment(MainViewModel.FRAGMENT_TAGS.FRAGMENT3);
            return true;
        }
    }
//    private void showFragment(@IdRes int actionId){
//        navController.navigate(actionId);
//    }
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

    private void saveDataInViewModel(){
        mMainViewModel.studentDetails.setTime_of_group_study((int)Float.parseFloat(getGrpStudyTime()));
        mMainViewModel.studentDetails.setAbsent_in_a_semester((int)Float.parseFloat(getAbsentTime()));
        mMainViewModel.studentDetails.setAsk_question_frequently(getAskedQuestionSelectedRadioId()==R.id.radio_button_asked_question_yes?1:0);
        mMainViewModel.studentDetails.setUse_additional_course_material(getAdditionalResourceSelectedId()==R.id.radio_button_used_additional_yes?1:0);
        mMainViewModel.studentDetails.setMeet_with_advisor(getMeetWithAdvisorId()==R.id.radio_button_meet_advisor_yes?1:0);
        mMainViewModel.studentDetails.setResult_of_last_semester(getResultOfLastSemester());
    }

    private String getAbsentTime(){
        return mFragmentPage2Binding.absentInSemesterEditText.getText().toString();
    }
    private boolean isAbsentTimeValid(){
        return !getAbsentTime().isEmpty();
    }
    private String getGrpStudyTime(){
        return mFragmentPage2Binding.groupStudyTimeEditText.getText().toString();
    }
    private boolean isGprStudyValid(){
        return !getGrpStudyTime().isEmpty();
    }
    private String getResultOfLastSemester(){
        return mFragmentPage2Binding.lastSemesterCGPAEditText.getText().toString();
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
                String[] c = getResultOfLastSemester().split(".");
                if(c[0].length()==1&&Integer.parseInt(c[0])<4){
                    Log.d(getString(R.string.DEBUGING_TAG),"if 4 ");
                    return true;
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
    private int getAskedQuestionSelectedRadioId(){
        return mFragmentPage2Binding.radioGroupAskedQuestionFrequently.getCheckedRadioButtonId();
    }
    private boolean isAskedFrequentlySelected(){
        if(getAskedQuestionSelectedRadioId()==-1){
            return false;
        }
        else {
            return true;
        }
    }
    private int getAdditionalResourceSelectedId(){
        return mFragmentPage2Binding.radioGroupUsedAdditionalMaterial.getCheckedRadioButtonId();
    }
    private boolean isAdditionalResourceSelected(){
        if(getAdditionalResourceSelectedId()==-1){
            return false;
        }
        else {
            return true;
        }
    }
    private int getMeetWithAdvisorId(){
        return mFragmentPage2Binding.radioGroupMeetWithAdvisor.getCheckedRadioButtonId();
    }
    private boolean isMeetWithAdvisorSelected(){
        if(getMeetWithAdvisorId()==-1){
            return false;
        }
        else {
            return true;
        }
    }
    private void showErrorInTextInputLayout(TextInputLayout textInputLayout, String message){
        textInputLayout.setError(message);
    }
    private void changeVisibilityOfErrorView(LinearLayout linearLayout, int visibility){
        linearLayout.setVisibility(visibility);
    }


}