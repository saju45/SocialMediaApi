package com.example.socialmediaapi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialmediaapi.Model.FriendRequest.FriendRequests;
import com.example.socialmediaapi.Model.FriendRequest.Friends;
import com.example.socialmediaapi.Model.FriendRequest.MyFriendResponse;
import com.example.socialmediaapi.Model.FriendRequest.Sender;
import com.example.socialmediaapi.Model.RegistrationModel.ProfileImage;
import com.example.socialmediaapi.R;
import com.example.socialmediaapi.databinding.FirendRvSimpleBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyFriendAdapter extends RecyclerView.Adapter<MyFriendAdapter.viewHolder> {


    Context context;
    List<Friends> list;

    public MyFriendAdapter(Context context, List<Friends> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.firend_rv_simple,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {


        Friends friends=list.get(position);

        Picasso.get().load(String.valueOf(friends.getUser().getProfileImage())).placeholder(R.drawable.saju).into(holder.binding.fProfile);



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        FirendRvSimpleBinding binding;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            binding=FirendRvSimpleBinding.bind(itemView);
        }
    }
}
