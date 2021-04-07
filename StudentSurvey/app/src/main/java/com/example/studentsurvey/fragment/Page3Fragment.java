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
        satisfactionChecked();
        parentEduChecked();

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(getString(R.string.DEBUGING_TAG),"on attach page2");
    }

    private void satisfactionChecked(){
        mFragmentPage3Binding.radioGroupParentSatisfaction.setOnCheckedChangeListener((group, checkedId)->{
            if(isSatisfactionSelected()) changeVisibilityOfErrorView(mFragmentPage3Binding.satisfactionRadioErrorView,View.GONE);
        });
    }
    private void parentEduChecked(){
        mFragmentPage3Binding.radioGroupFatherEduStatus.setOnCheckedChangeListener((group, checkedId)->{
            if(isParentEduSelected()) changeVisibilityOfErrorView(mFragmentPage3Binding.fatherRadioErrorView,View.GONE);
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
        if(!isSatisfactionSelected()){
            changeVisibilityOfErrorView(mFragmentPage3Binding.satisfactionRadioErrorView,View.VISIBLE);
            return false;
        }
        else if(!isParentEduSelected()){
            changeVisibilityOfErrorView(mFragmentPage3Binding.fatherRadioErrorView,View.VISIBLE);
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
        mMainViewModel.studentDetails.setParent_satisfied(getParentSatisfactionSelectedId()==R.id.radio_button_parent_satisfaction_yes?1:0);
        mMainViewModel.studentDetails.setParent_education_status(getSelectedParentEduStatus());
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
    private int getParentEduSelectedId(){
        return mFragmentPage3Binding.radioGroupFatherEduStatus.getCheckedRadioButtonId();
    }
    private boolean isParentEduSelected(){
        if(getParentEduSelectedId()==-1){
            return false;
        }
        else {
            return true;
        }
    }
    private Integer getSelectedParentEduStatus(){
        return getParentSatisfactionSelectedId()==R.id.radio_button_father_hsc_or_above? 4:(
                getParentSatisfactionSelectedId()==R.id.radio_button_father_hsc? 3: (
                        getParentSatisfactionSelectedId()==R.id.radio_button_father_ssc?2:(
                                getParentSatisfactionSelectedId()==R.id.radio_button_father_jsc?1:0
                                )
                        )
                );
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



}