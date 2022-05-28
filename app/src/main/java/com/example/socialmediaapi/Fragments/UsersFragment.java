package com.example.socialmediaapi.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.socialmediaapi.Adapter.FriendRequestAdapter;
import com.example.socialmediaapi.Adapter.UserAdapter;
import com.example.socialmediaapi.Api;
import com.example.socialmediaapi.Model.AllUsers;
import com.example.socialmediaapi.Model.FriendRequest.FriendRequests;
import com.example.socialmediaapi.Model.FriendRequest.RequestResponse;
import com.example.socialmediaapi.Model.FriendRequest.Sender;
import com.example.socialmediaapi.Model.RegistrationModel.User;
import com.example.socialmediaapi.R;
import com.example.socialmediaapi.Retrofit_Client;
import com.example.socialmediaapi.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class UsersFragment extends Fragment {

    RecyclerView recyclerView,friendRv;
    private SharedPrefManager sharedPrefManager;
    ArrayList<User> list;
    ArrayList<FriendRequests> senderArrayList;

    private Api api;

    public UsersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_users, container, false);


        sharedPrefManager=new SharedPrefManager(getContext());
        list=new ArrayList<>();
        senderArrayList=new ArrayList<>();

        friendRv=view.findViewById(R.id.You_rv_Id);
        friendRv.setHasFixedSize(true);
        friendRv.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView=view.findViewById(R.id.rv_Id);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Call<AllUsers> call= Retrofit_Client
                .getInstance()
                .getApi()
                .fetchAllUser(sharedPrefManager.getToken());

  /*      Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://192.168.0.103:4001/api/user/login")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api=retrofit.create(Api.class);

        Call<AllUsers> call=api.fetchAllUser();*/

        call.enqueue(new Callback<AllUsers>() {
            @Override
            public void onResponse(Call<AllUsers> call, Response<AllUsers> response) {

                if (response.isSuccessful())
                {
                    list= (ArrayList<User>) response.body().getUsers();
                    recyclerView.setAdapter(new UserAdapter(getContext(),list));

                }else {

                }
            }

            @Override
            public void onFailure(Call<AllUsers> call, Throwable t) {

              Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        Call<RequestResponse> call1=Retrofit_Client
                .getInstance()
                .getApi()
                .getFriendRequest(sharedPrefManager.getToken());


        call1.enqueue(new Callback<RequestResponse>() {
            @Override
            public void onResponse(Call<RequestResponse> call, Response<RequestResponse> response) {

                if (response.isSuccessful())
                {
                  senderArrayList= (ArrayList<FriendRequests>) response.body().getFriendRequests();
                   // senderArrayList.add((Sender) response.body().getFriendRequests());
                    friendRv.setAdapter(new FriendRequestAdapter(getContext(), senderArrayList));

                }
            }

            @Override
            public void onFailure(Call<RequestResponse> call, Throwable t) {

            }
        });


    return view;
    }
}