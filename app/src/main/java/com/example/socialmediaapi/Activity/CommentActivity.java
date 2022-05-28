package com.example.socialmediaapi.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.socialmediaapi.Adapter.CommentAdapter;
import com.example.socialmediaapi.Model.Comment.CommentResponse;
import com.example.socialmediaapi.Model.PostModel.Comment;
import com.example.socialmediaapi.Model.PostModel.Post;
import com.example.socialmediaapi.Model.PostModel.PostList;
import com.example.socialmediaapi.Model.PostModel.SinglePost;
import com.example.socialmediaapi.R;
import com.example.socialmediaapi.Retrofit_Client;
import com.example.socialmediaapi.SharedPrefManager;
import com.example.socialmediaapi.databinding.ActivityCommentBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentActivity extends AppCompatActivity {

    ActivityCommentBinding binding;
    SharedPrefManager sharedPrefManager;
    List<Comment> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCommentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sharedPrefManager=new SharedPrefManager(this);
        list=new ArrayList<>();
        Intent intent=getIntent();

        String postId=intent.getStringExtra("postId");




        binding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String comment=binding.commentEditText.getText().toString();

                Call<CommentResponse> call= Retrofit_Client
                        .getInstance()
                        .getApi()
                        .Comment(sharedPrefManager.getToken(),postId,comment);

                call.enqueue(new Callback<CommentResponse>() {
                    @Override
                    public void onResponse(Call<CommentResponse> call, Response<CommentResponse> response) {


                        if (response.isSuccessful())
                        {
                            Toast.makeText(CommentActivity.this, "comment is success", Toast.LENGTH_SHORT).show();
                            binding.commentEditText.setText("");
                        }else {
                            Toast.makeText(CommentActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<CommentResponse> call, Throwable t) {

                        Toast.makeText(CommentActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });


        binding.commentRecyclerview.setLayoutManager(new LinearLayoutManager(this));


        Call<SinglePost> call=Retrofit_Client
                .getInstance()
                .getApi()
                .getSinglePost(sharedPrefManager.getToken(),postId);

        call.enqueue(new Callback<SinglePost>() {
            @Override
            public void onResponse(Call<SinglePost> call, Response<SinglePost> response) {



                if (response.isSuccessful())
                {
                    list= (ArrayList<Comment>) response.body().getComments();
                    binding.commentRecyclerview.setAdapter(new CommentAdapter(CommentActivity.this,list));
                    binding.name.setText(response.body().getUser().getFirstName()+" "+response.body().getUser().getLastName());
                    Picasso.get().load(String.valueOf(response.body().getUser().getProfileImage())).placeholder(R.drawable.saju).into(binding.profileImage);
                    binding.liketext.setText(response.body().getLikes().size()+"");
                    binding.commentText.setText(response.body().getComments().size()+"");

                    if (response.body().getPostType().equals("text")) {
                        binding.postImage.setVisibility(View.GONE);
                        binding.postText.setText(response.body().getDescription());
                    }else if (response.body().getPostType().equals("image")) {

                        Picasso.get().load(String.valueOf(response.body().getPostImage())).placeholder(R.drawable.saju).into(binding.postImage);
                        binding.postText.setText(response.body().getDescription());

                    }

                }else {
                    Toast.makeText(CommentActivity.this,"Failed",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SinglePost> call, Throwable t) {

                Toast.makeText(CommentActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });




    }



    }
