package com.example.myapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersViewHolder> {
    private Context myContext;
    private List<User> userList;

    public UsersAdapter(Context myContext,List<User> userList){
        this.myContext = myContext;
        this.userList = userList;
    }

    @Override
    public UsersViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view = LayoutInflater.from(myContext).inflate(R.layout.recyclerview_users,parent,false);
        return new UsersViewHolder(view);
    }
    @Override
    public void onBindViewHolder(UsersViewHolder holder,int position){
        User user = userList.get(position);
        holder.textViewName.setText(user.getName());
        holder.textViewMark.setText(Integer.toString(user.getMark()));
    }
    @Override
    public int getItemCount(){
        return userList.size();
    }

    class UsersViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textViewName, textViewMark;
        public UsersViewHolder(View itemView){
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewMark = itemView.findViewById(R.id.textViewMark);

            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view){
        }

    }
}
