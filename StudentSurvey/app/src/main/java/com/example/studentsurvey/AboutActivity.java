package com.example.studentsurvey;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.example.studentsurvey.databinding.ActivityAboutBinding;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.View;
import android.widget.LinearLayout;

public class AboutActivity extends AppCompatActivity {
    ActivityAboutBinding mActivityAboutBinding;
    MemberRecyclerViewAdapter memberRecyclerViewAdapter;
    MainViewModel mMainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityAboutBinding = ActivityAboutBinding.inflate(getLayoutInflater());
        setContentView(mActivityAboutBinding.getRoot());
        initViewModel();
        mMainViewModel.initTeamMember();
        initRecyclerView();
        activityCloseButtonListener();

    }

    private void activityCloseButtonListener(){
        mActivityAboutBinding.activityClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    private void initViewModel(){
        mMainViewModel = new ViewModelProvider(this)
                .get(MainViewModel.class);
    }
    private void initRecyclerView(){
        memberRecyclerViewAdapter = new MemberRecyclerViewAdapter(mMainViewModel.teamMembers, new MemberRecyclerViewAdapter.OnItemClick() {
            @Override
            public void socialItemClick(int position, MainViewModel.SOCIAL_APP socialAppName) {
                redirectToSocialAccount(position,socialAppName);
            }
        });
        mActivityAboutBinding.teamMemberRecyclerView.setHasFixedSize(true);
        mActivityAboutBinding.teamMemberRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL,false));
        mActivityAboutBinding.teamMemberRecyclerView.setAdapter(memberRecyclerViewAdapter);
    }
    private void redirectToSocialAccount(int position, MainViewModel.SOCIAL_APP socialAppName){
        switch (socialAppName){
            case TWITTER:
                launchTwitter(position);
                break;
            case FACEBOOK:
                launchFacebook(position);
                break;
            case LINKED_IN:
                launchLinkedIn(position);
                break;
        }
    }

    private void launchLinkedIn(int position) {
        if(mMainViewModel.teamMembers.get(position).linkedInIdLink==null) return;
        Intent viewIntent =
                new Intent("android.intent.action.VIEW",
                        Uri.parse(mMainViewModel.teamMembers.get(position).linkedInIdLink));
        startActivity(viewIntent);
    }

    private void launchFacebook(int position) {
        if(mMainViewModel.teamMembers.get(position).facebookIdLink==null) return;
        Intent viewIntent =
                new Intent("android.intent.action.VIEW",
                        Uri.parse(mMainViewModel.teamMembers.get(position).facebookIdLink));
        startActivity(viewIntent);
    }

    private void launchTwitter(int position) {
        if(mMainViewModel.teamMembers.get(position).twitterIdLink==null) return;
        Intent viewIntent =
                new Intent("android.intent.action.VIEW",
                        Uri.parse(mMainViewModel.teamMembers.get(position).twitterIdLink));
        startActivity(viewIntent);
    }


}