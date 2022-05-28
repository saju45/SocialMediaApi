package com.example.socialmediaapi.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.socialmediaapi.Adapter.MyFriendAdapter;
import com.example.socialmediaapi.Adapter.MyPostAdapter;
import com.example.socialmediaapi.Adapter.PostAdapter;
import com.example.socialmediaapi.Model.CurrentUser;
import com.example.socialmediaapi.Model.FriendRequest.Friends;
import com.example.socialmediaapi.Model.FriendRequest.MyFriendResponse;
import com.example.socialmediaapi.Model.FriendRequest.RequestResponse;
import com.example.socialmediaapi.Model.PostModel.Post;
import com.example.socialmediaapi.Model.PostModel.PostList;
import com.example.socialmediaapi.R;
import com.example.socialmediaapi.Retrofit_Client;
import com.example.socialmediaapi.SharedPrefManager;
import com.example.socialmediaapi.databinding.FragmentProfileBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileFragment extends Fragment {

    FragmentProfileBinding binding;
    SharedPrefManager sharedPrefManager;
    List<Friends> list;
    ArrayList<Post> list2;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentProfileBinding.inflate(inflater, container, false);

        list=new ArrayList<>();
        list2=new ArrayList<>();
        sharedPrefManager=new SharedPrefManager(getContext());

         Call<CurrentUser> call= Retrofit_Client
                .getInstance()
                .getApi()
                .fetchCurrentUser(sharedPrefManager.getToken());


         call.enqueue(new Callback<CurrentUser>() {
             @Override
             public void onResponse(Call<CurrentUser> call, Response<CurrentUser> response) {

                 if (response.isSuccessful())
                 {
                     binding.nameTextView.setText(response.body().getFirstName()+" "+response.body().getLastName());
                     binding.pabout.setText(response.body().getEmail());
                     Picasso.get().load(String.valueOf(response.body().getProfileImage())).placeholder(R.drawable.programmer).into(binding.pProfile);
                   // Picasso.get().load(String.valueOf(response.body().getCoverImage())).placeholder(R.drawable.saju).into(binding.coverImg);

                 }else {

                     Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();

                 }

             }

             @Override
             public void onFailure(Call<CurrentUser> call, Throwable t) {

                 Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
             }
         });

         LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
         layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
         binding.friendRecyclerView.setLayoutManager(layoutManager);


         Call<MyFriendResponse> call1=Retrofit_Client
                 .getInstance()
                 .getApi()
                 .getAllFriend(sharedPrefManager.getToken());

         call1.enqueue(new Callback<MyFriendResponse>() {
             @Override
             public void onResponse(Call<MyFriendResponse> call, Response<MyFriendResponse> response) {

                 if (response.isSuccessful())
                 {


                     list= (List<Friends>) response.body().getFriends();
                     binding.friendCound.setText(list.size()+"");
                     binding.friendRecyclerView.setAdapter(new MyFriendAdapter(getContext(),list));

                 }else {
                     Toast.makeText(getContext(),"Failed",Toast.LENGTH_SHORT).show();
                 }
             }

             @Override
             public void onFailure(Call<MyFriendResponse> call, Throwable t) {

             }
         });

         binding.myAllPostRv.setLayoutManager(new LinearLayoutManager(getContext()));


        Call<PostList> call2= Retrofit_Client
                .getInstance()
                .getApi()
                .fetchAllPost(sharedPrefManager.getToken());


        call2.enqueue(new Callback<PostList>() {
            @Override
            public void onResponse(Call<PostList> call, Response<PostList> response) {



                if (response.isSuccessful())
                {

                 /*   if (response.body().getPosts().get(list2.size()).getUser().get_id().equals(sharedPrefManager.getUser().get_id()))
                    {
                        list2= (ArrayList<Post>) response.body().getPosts();
                        binding.myAllPostRv.setAdapter(new PostAdapter(getContext(),list2));
                        Toast.makeText(getContext(), "Your Posts", Toast.LENGTH_SHORT).show();
                    }*/

                    list2= (ArrayList<Post>) response.body().getPosts();
                    binding.myAllPostRv.setAdapter(new MyPostAdapter(getContext(),list2));
                  //  Toast.makeText(getContext(), "Your Posts", Toast.LENGTH_SHORT).show();


                }else {
                    Toast.makeText(getContext(), "Failed ", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<PostList> call, Throwable t) {

                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



        return binding.getRoot();
    }
}