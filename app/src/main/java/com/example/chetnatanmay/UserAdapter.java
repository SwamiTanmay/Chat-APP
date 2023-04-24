package com.example.chetnatanmay;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    List<Users> user_list;
    Context context;

    public UserAdapter(List<Users> user_list, Context context) {
        this.user_list = user_list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_layout,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Users curr_user_item = user_list.get(position);
        holder.mUsername.setText(curr_user_item.getName());
        holder.mEmail.setText(curr_user_item.getEmail());
        holder.mTime.setText(curr_user_item.getReg_on());
    }

    @Override
    public int getItemCount() {
        return user_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView mUsername, mEmail, mTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mUsername = (TextView) itemView.findViewById(R.id.username);
            mEmail = (TextView) itemView.findViewById(R.id.email);
            mTime = (TextView) itemView.findViewById(R.id.time);

        }
    }
}
