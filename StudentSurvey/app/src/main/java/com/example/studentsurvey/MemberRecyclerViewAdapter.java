package com.example.studentsurvey;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentsurvey.databinding.TeamReclyclerViewItemBinding;
import com.example.studentsurvey.model.TeamMember;

import java.util.List;

public class MemberRecyclerViewAdapter extends RecyclerView.Adapter<MemberRecyclerViewAdapter.ViewHolder> {
    List<TeamMember> teamMemberList;
    OnItemClick onItemClick;


    public MemberRecyclerViewAdapter(List<TeamMember> teamMemberList, OnItemClick onItemClick) {
        this.teamMemberList = teamMemberList;
        this.onItemClick = onItemClick;
    }

    public interface OnItemClick{
        public void socialItemClick(int position, MainViewModel.SOCIAL_APP socialAppName);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                TeamReclyclerViewItemBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final TeamMember teamMember = teamMemberList.get(position);
        holder.teamReclyclerViewItemBinding.teamMemberName.setText(teamMember.name);
        holder.teamReclyclerViewItemBinding.teamMemberRole.setText(teamMember.role);


        holder.teamReclyclerViewItemBinding.facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.socialItemClick(position, MainViewModel.SOCIAL_APP.FACEBOOK);
            }
        });
        holder.teamReclyclerViewItemBinding.twitterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.socialItemClick(position, MainViewModel.SOCIAL_APP.TWITTER);
            }
        });
        holder.teamReclyclerViewItemBinding.linkedInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.socialItemClick(position, MainViewModel.SOCIAL_APP.LINKED_IN);
            }
        });

    }

    @Override
    public int getItemCount() {
        if(teamMemberList==null) return 0;
        else return teamMemberList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TeamReclyclerViewItemBinding teamReclyclerViewItemBinding;

        public ViewHolder(TeamReclyclerViewItemBinding teamReclyclerViewItemBinding) {
            super(teamReclyclerViewItemBinding.getRoot());
            this.teamReclyclerViewItemBinding = teamReclyclerViewItemBinding;
        }
    }
}
