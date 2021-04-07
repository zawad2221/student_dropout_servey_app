package com.example.studentsurvey.fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.studentsurvey.MainViewModel;
import com.example.studentsurvey.R;
import com.example.studentsurvey.databinding.FragmentMainBinding;
import com.example.studentsurvey.model.StudentDetails;

public class MainFragment extends Fragment {
    FragmentMainBinding mFragmentMainBinding;
    MainViewModel mMainViewModel;
    NavController navController;
    ProgressDialog progressDialog;
    AlertDialog alertDialog;


    public MainFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mFragmentMainBinding = FragmentMainBinding.inflate(inflater,container,false);
        return mFragmentMainBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(getString(R.string.DEBUGING_TAG),"view :"+view);
        initViewModel();

        initNavController(view);
        nextButtonOnClick();
        previousOnClick();
        submitButtonOnClick();
        selectedFragmentObserver();

    }

    private void resultObserver(){
        mMainViewModel.result.observe(getActivity(), new Observer<StudentDetails>() {
            @Override
            public void onChanged(StudentDetails studentDetails) {
                Log.d(getString(R.string.DEBUGING_TAG),"on change result: "+studentDetails.getResult().toString());
                dismissProgressDialog();
                if(studentDetails.getResult()!=-1){

                    showResultAlertDialog("Result: "+studentDetails.getResult());
                    restartActivity();
                }
                else {
                    showResultAlertDialog("Failed");
                    setCurrentSelectedFragment(MainViewModel.FRAGMENT_TAGS.FRAGMENT4);
                }

            }
        });
    }

    private void selectedFragmentObserver(){
        mMainViewModel.currentFragment.observe(getActivity(), new Observer<MainViewModel.FRAGMENT_TAGS>() {
            @Override
            public void onChanged(MainViewModel.FRAGMENT_TAGS fragment_tags) {
                if(mMainViewModel.currentFragment.getValue()== MainViewModel.FRAGMENT_TAGS.FRAGMENT2){
                    Log.d(getString(R.string.DEBUGING_TAG),"selected fragment on main: "+mMainViewModel.currentFragment.getValue().toString());
                    setButtonVisibility(mFragmentMainBinding.previousButton,View.VISIBLE);
                }
                else if(mMainViewModel.currentFragment.getValue()== MainViewModel.FRAGMENT_TAGS.FRAGMENT4){
                    setButtonVisibility(mFragmentMainBinding.nextButton,View.GONE);
                    setButtonVisibility(mFragmentMainBinding.submitButton,View.VISIBLE);
                }
                else if(mMainViewModel.currentFragment.getValue()== MainViewModel.FRAGMENT_TAGS.FRAGMENT_SUBMIT){
                    showProgressDialog("loading......");
                    Log.d(getString(R.string.DEBUGING_TAG),"all student info: "+mMainViewModel.studentDetails.toString());
                    new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            dismissProgressDialog();
                        }
                        catch (Exception e){

                        }


                    }
                }, 60000);
                    mMainViewModel.saveInfo(getContext(),mMainViewModel.studentDetails);
                    resultObserver();
                }
            }
        });
    }
//    private void nextButtonObserver(){
//        mMainViewModel.nextButtonClick.observe(getActivity(), new Observer<MainViewModel.FRAGMENT_TAGS>() {
//            @Override
//            public void onChanged(MainViewModel.FRAGMENT_TAGS fragment_tags) {
//                Log.d(getString(R.string.DEBUGING_TAG),"next click on main: "+mMainViewModel.currentFragment);
//                if(mMainViewModel.currentFragment.getValue()== MainViewModel.FRAGMENT_TAGS.FRAGMENT2){
//                    setButtonVisibility(mFragmentMainBinding.previousButton,View.VISIBLE);
//                }
//            }
//        });
//    }
    private void nextButtonOnClick(){

        mFragmentMainBinding.nextButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d(getString(R.string.DEBUGING_TAG),"next click: "+mMainViewModel.currentFragment.getValue().toString());

                if(mMainViewModel.currentFragment.getValue()== MainViewModel.FRAGMENT_TAGS.FRAGMENT1){
                    Log.d(getString(R.string.DEBUGING_TAG),"fragment 1");

                    //setButtonVisibility(mFragmentMainBinding.previousButton,View.VISIBLE);
//                    showFragment(R.id.action_page1Fragment_to_page2Fragment);
                    Log.d(getString(R.string.DEBUGING_TAG),"current in main nextclick frag by nav: "+navController.getCurrentDestination());
                    mMainViewModel.nextButtonClick.setValue(MainViewModel.FRAGMENT_TAGS.FRAGMENT1);
                }
                else if(mMainViewModel.currentFragment.getValue()== MainViewModel.FRAGMENT_TAGS.FRAGMENT2){
//                    setCurrentSelectedFragment(MainViewModel.FRAGMENT_TAGS.FRAGMENT3);
//                    showFragment(R.id.action_page2Fragment_to_page3Fragment);

                    mMainViewModel.nextButtonClick.setValue(MainViewModel.FRAGMENT_TAGS.FRAGMENT2);
                }
                else if(mMainViewModel.currentFragment.getValue()== MainViewModel.FRAGMENT_TAGS.FRAGMENT3){
                    //setCurrentSelectedFragment(MainViewModel.FRAGMENT_TAGS.FRAGMENT4);
//                    setButtonVisibility(mFragmentMainBinding.nextButton,View.GONE);
//                    setButtonVisibility(mFragmentMainBinding.submitButton,View.VISIBLE);
                    //showFragment(R.id.action_page3Fragment_to_page4Fragment);
                    mMainViewModel.nextButtonClick.setValue(MainViewModel.FRAGMENT_TAGS.FRAGMENT3);
                }

            }
        });
    }

    private void setCurrentSelectedFragment(MainViewModel.FRAGMENT_TAGS selectedFragment){
        mMainViewModel.currentFragment.setValue(selectedFragment);
    }
    private void previousOnClick(){
        mFragmentMainBinding.previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }
    private void submitButtonOnClick(){
        mFragmentMainBinding.submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        dismissProgressDialog();
//                        showResultAlertDialog("OK");
//                    }
//                }, 2000);
                mMainViewModel.nextButtonClick.setValue(MainViewModel.FRAGMENT_TAGS.FRAGMENT_SUBMIT);
            }
        });
    }
    private void showFragment(@IdRes int actionId){
        navController.navigate(actionId);
    }
    private void initViewModel(){
        mMainViewModel = new ViewModelProvider(requireActivity())
                .get(MainViewModel.class);
    }
    private void setButtonVisibility(Button button, int visibility){
        button.setVisibility(visibility);
    }

    private void initNavController(View view){
        try {
//            navController = ((NavHostFragment)getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_container))
//                    .getNavController();
            navController = Navigation.findNavController(getActivity(), R.id.fragment_container);
        }
        catch (Exception e){
            Log.d(getString(R.string.DEBUGING_TAG),"exception: "+e.getMessage());
        }

    }
    private void showProgressDialog(String message){
        progressDialog = new ProgressDialog(getContext(),android.app.AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(message);
        progressDialog.show();
    }
    private void dismissProgressDialog(){
        progressDialog.dismiss();
    }
    private void showResultAlertDialog(String resultMessage){
        alertDialog = new AlertDialog.Builder(getContext())
                .setTitle(resultMessage)
                .setCancelable(false)
                .setPositiveButton(getString(R.string.OK), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //restartActivity();
                        alertDialog.dismiss();
                    }
                })
                .create();
        alertDialog.show();

        //set positive button in center
        final Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setBackgroundColor(getResources().getColor(R.color.yellow));
        positiveButton.setTextColor(getResources().getColor(R.color.white));
        LinearLayout parent = (LinearLayout) positiveButton.getParent();
        parent.setGravity(Gravity.CENTER_HORIZONTAL);
        View leftSpacer = parent.getChildAt(1);
        leftSpacer.setVisibility(View.GONE);
    }
    private void restartActivity(){
        Intent intent = getActivity().getIntent();
        getActivity().finish();
        startActivity(intent);
    }
    public void onBackPressed()
    {
        if(mMainViewModel.currentFragment.getValue()== MainViewModel.FRAGMENT_TAGS.FRAGMENT2){
            setCurrentSelectedFragment(MainViewModel.FRAGMENT_TAGS.FRAGMENT1);
            setButtonVisibility(mFragmentMainBinding.previousButton,View.GONE);
            navController.popBackStack(R.id.page1Fragment,false);

        }
        else if(mMainViewModel.currentFragment.getValue()== MainViewModel.FRAGMENT_TAGS.FRAGMENT3){
            setCurrentSelectedFragment(MainViewModel.FRAGMENT_TAGS.FRAGMENT2);
            navController.popBackStack(R.id.page2Fragment,false);

        }
        else if(mMainViewModel.currentFragment.getValue()== MainViewModel.FRAGMENT_TAGS.FRAGMENT4||
                mMainViewModel.currentFragment.getValue()== MainViewModel.FRAGMENT_TAGS.FRAGMENT_SUBMIT){
            setCurrentSelectedFragment(MainViewModel.FRAGMENT_TAGS.FRAGMENT3);
            setButtonVisibility(mFragmentMainBinding.submitButton,View.GONE);
            setButtonVisibility(mFragmentMainBinding.nextButton,View.VISIBLE);
            navController.popBackStack(R.id.page3Fragment,false);
        }
        else if(mMainViewModel.currentFragment.getValue()== MainViewModel.FRAGMENT_TAGS.FRAGMENT1){
            getActivity().finish();
        }
        //navController.navigateUp();
//        boolean navResult = navController.popBackStack();
//
//        Log.d("DEBUGING_TAG", "back issuccess: "+navResult);
        //this.onBackPressed();
    }

}