package com.example.studentsurvey;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.studentsurvey.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mActivityMainBinding;
    MainViewModel mMainViewModel;
    NavController navController;
    ProgressDialog progressDialog;
    AlertDialog alertDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mActivityMainBinding.getRoot());
        initViewModel();
        initNavController();
        nextButtonOnClick();
        previousOnClick();
        submitButtonOnClick();
    }
    private void nextButtonOnClick(){
        mActivityMainBinding.nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mMainViewModel.currentFragment== MainViewModel.FRAGMENT_TAGS.FRAGMENT1){
                    setCurrentSelectedFragment(MainViewModel.FRAGMENT_TAGS.FRAGMENT2);
                    setButtonVisibility(mActivityMainBinding.previousButton,View.VISIBLE);
                    showFragment(R.id.action_page1Fragment_to_page2Fragment);
                }
                else if(mMainViewModel.currentFragment== MainViewModel.FRAGMENT_TAGS.FRAGMENT2){
                    setCurrentSelectedFragment(MainViewModel.FRAGMENT_TAGS.FRAGMENT3);
                    showFragment(R.id.action_page2Fragment_to_page3Fragment);
                }
                else if(mMainViewModel.currentFragment== MainViewModel.FRAGMENT_TAGS.FRAGMENT3){
                    setCurrentSelectedFragment(MainViewModel.FRAGMENT_TAGS.FRAGMENT4);
                    setButtonVisibility(mActivityMainBinding.nextButton,View.GONE);
                    setButtonVisibility(mActivityMainBinding.submitButton,View.VISIBLE);
                    showFragment(R.id.action_page3Fragment_to_page4Fragment);
                }

            }
        });
    }
    private void setCurrentSelectedFragment(MainViewModel.FRAGMENT_TAGS selectedFragment){
        mMainViewModel.currentFragment= selectedFragment;
    }
    private void previousOnClick(){
        mActivityMainBinding.previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    private void submitButtonOnClick(){
        mActivityMainBinding.submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressDialog("loading......");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dismissProgressDialog();
                        showResultAlertDialog("OK");
                    }
                }, 2000);
            }
        });
    }
    private void showFragment(@IdRes int actionId){
        navController.navigate(actionId);
    }
    private void initViewModel(){
        mMainViewModel = new ViewModelProvider(this)
                .get(MainViewModel.class);
    }
    private void setButtonVisibility(Button button, int visibility){
        button.setVisibility(visibility);
    }

    private void initNavController(){
        navController = ((NavHostFragment)this.getSupportFragmentManager().findFragmentById(R.id.fragment_container))
                .getNavController();
    }
    private void showProgressDialog(String message){
        progressDialog = new ProgressDialog(this,android.app.AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(message);
        progressDialog.show();
    }
    private void dismissProgressDialog(){
        progressDialog.dismiss();
    }
    private void showResultAlertDialog(String resultMessage){
        alertDialog = new AlertDialog.Builder(this)
                .setTitle(resultMessage)
                .setCancelable(false)
                .setPositiveButton(getString(R.string.OK), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        restartActivity();
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
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if(mMainViewModel.currentFragment== MainViewModel.FRAGMENT_TAGS.FRAGMENT2){
            setCurrentSelectedFragment(MainViewModel.FRAGMENT_TAGS.FRAGMENT1);
            setButtonVisibility(mActivityMainBinding.previousButton,View.GONE);

        }
        else if(mMainViewModel.currentFragment== MainViewModel.FRAGMENT_TAGS.FRAGMENT3){
            setCurrentSelectedFragment(MainViewModel.FRAGMENT_TAGS.FRAGMENT2);

        }
        else if(mMainViewModel.currentFragment== MainViewModel.FRAGMENT_TAGS.FRAGMENT4){
            setCurrentSelectedFragment(MainViewModel.FRAGMENT_TAGS.FRAGMENT3);
            setButtonVisibility(mActivityMainBinding.submitButton,View.GONE);
            setButtonVisibility(mActivityMainBinding.nextButton,View.VISIBLE);

        }
        super.onBackPressed();
    }
}