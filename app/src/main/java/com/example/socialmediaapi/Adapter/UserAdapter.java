package com.example.socialmediaapi.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialmediaapi.Model.FriendRequest.FriendRequest;
import com.example.socialmediaapi.Model.RegistrationModel.User;
import com.example.socialmediaapi.R;
import com.example.socialmediaapi.Retrofit_Client;
import com.example.socialmediaapi.SharedPrefManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.viewHolder> {

    private SharedPrefManager sharedPrefManager;
    Context context;
    ArrayList<User> list;

    public UserAdapter(Context context, ArrayList<User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.simple_layout,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        User user=list.get(position);

        sharedPrefManager=new SharedPrefManager(context);
        holder.firstName.setText(user.getFirstName());
        holder.lastName.setText(user.getLastName());
        holder.email.setText(user.getEmail());
        holder.add_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Call<FriendRequest> call= Retrofit_Client
                        .getInstance()
                        .getApi()
                        .sendFriendRequ(sharedPrefManager.getToken(), user.get_id());

                call.enqueue(new Callback<FriendRequest>() {
                    @Override
                    public void onResponse(Call<FriendRequest> call, Response<FriendRequest> response) {

                        if (response.isSuccessful())
                        {
                            holder.add_friend.setBackgroundColor(context.getResources().getColor(R.color.white));
                            holder.add_friend.setText("Cancel");
                            holder.add_friend.setTextColor(context.getResources().getColor(R.color.black));
                            Toast.makeText(context, "Send Request", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<FriendRequest> call, Throwable t) {
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
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
        ImageView imageView;
        TextView firstName,lastName,email;
        Button add_friend;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.profile_image);
            firstName=itemView.findViewById(R.id.firstName);
            lastName=itemView.findViewById(R.id.lastName);
            email=itemView.findViewById(R.id.email);
            add_friend=itemView.findViewById(R.id.followBtn);
        }
    }
}
