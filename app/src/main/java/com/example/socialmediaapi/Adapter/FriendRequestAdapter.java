package com.example.socialmediaapi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialmediaapi.Model.FriendRequest.AcceptFriendRequestResponse;
import com.example.socialmediaapi.Model.FriendRequest.DeleteFriendRequest;
import com.example.socialmediaapi.Model.FriendRequest.FriendRequest;

import com.example.socialmediaapi.Model.FriendRequest.FriendRequests;
import com.example.socialmediaapi.Model.FriendRequest.Sender;
import com.example.socialmediaapi.R;
import com.example.socialmediaapi.Retrofit_Client;
import com.example.socialmediaapi.SharedPrefManager;
import com.example.socialmediaapi.databinding.SimpleAcceptFriendBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FriendRequestAdapter extends RecyclerView.Adapter<FriendRequestAdapter.viewHolder>{

    Context context;
    ArrayList<FriendRequests> list;
    SharedPrefManager sharedPrefManager;

    public FriendRequestAdapter(Context context, ArrayList<FriendRequests> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.simple_accept_friend,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

      FriendRequests friendRequest=list.get(position);
      Sender sender=friendRequest.getSender();
      sharedPrefManager=new SharedPrefManager(context);


        holder.binding.firstName.setText(friendRequest.getSender().getFirstName());
        holder.binding.lastName.setText(friendRequest.getSender().getLastName());
        holder.binding.email.setText(sender.getEmail());


        holder.binding.acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, "You Click accept button", Toast.LENGTH_SHORT).show();

                Call<AcceptFriendRequestResponse> call= Retrofit_Client
                        .getInstance()
                        .getApi()
                        .AcceptFriendRequ(sharedPrefManager.getToken(),sender.get_id());


                call.enqueue(new Callback<AcceptFriendRequestResponse>() {
                    @Override
                    public void onResponse(Call<AcceptFriendRequestResponse> call, Response<AcceptFriendRequestResponse> response) {

                        if (response.isSuccessful())
                        {
                            list.remove(list.size());
                            Toast.makeText(context, "Accepted", Toast.LENGTH_SHORT).show();

                        }else {

                        }
                    }

                    @Override
                    public void onFailure(Call<AcceptFriendRequestResponse> call, Throwable t) {

                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        holder.binding.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "You Click delete button", Toast.LENGTH_SHORT).show();

                Call<DeleteFriendRequest> call=Retrofit_Client
                        .getInstance()
                        .getApi()
                        .DeleteFriendRequest(sharedPrefManager.getToken(),sender.get_id());

                call.enqueue(new Callback<DeleteFriendRequest>() {
                    @Override
                    public void onResponse(Call<DeleteFriendRequest> call, Response<DeleteFriendRequest> response) {

                        if (response.isSuccessful())
                        {
                            Toast.makeText(context,"Cancel Request",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<DeleteFriendRequest> call, Throwable t) {

                        Toast.makeText(context,t.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        SimpleAcceptFriendBinding binding;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            binding=SimpleAcceptFriendBinding.bind(itemView);
        }
    }
}
