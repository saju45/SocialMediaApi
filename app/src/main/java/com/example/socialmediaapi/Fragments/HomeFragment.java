package com.example.socialmediaapi.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.socialmediaapi.Adapter.PostAdapter;
import com.example.socialmediaapi.Adapter.UserAdapter;
import com.example.socialmediaapi.Model.PostModel.Post;
import com.example.socialmediaapi.Model.PostModel.PostList;
import com.example.socialmediaapi.Model.RegistrationModel.User;
import com.example.socialmediaapi.R;
import com.example.socialmediaapi.Retrofit_Client;
import com.example.socialmediaapi.SharedPrefManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<Post> list;
    SharedPrefManager sharedPrefManager;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);

        list=new ArrayList<>();
        sharedPrefManager=new SharedPrefManager(getContext());
        recyclerView=view.findViewById(R.id.home_rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        Call<PostList> call= Retrofit_Client
                .getInstance()
                .getApi()
                .fetchAllPost(sharedPrefManager.getToken());


        call.enqueue(new Callback<PostList>() {
            @Override
            public void onResponse(Call<PostList> call, Response<PostList> response) {

                if (response.isSuccessful())
                {
                    list= (ArrayList<Post>) response.body().getPosts();
                    recyclerView.setAdapter(new PostAdapter(getContext(),list));

                }else {
                   // Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PostList> call, Throwable t) {

                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return  view;
    }
}