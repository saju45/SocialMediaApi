package com.example.socialmediaapi.Fragments;

import static android.app.Activity.RESULT_OK;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import com.example.socialmediaapi.Model.CreatePost.CreatePost;
import com.example.socialmediaapi.Model.CreatePost.NewPost;
import com.example.socialmediaapi.Model.PostModel.Post;
import com.example.socialmediaapi.R;
import com.example.socialmediaapi.Retrofit_Client;
import com.example.socialmediaapi.SharedPrefManager;
import com.example.socialmediaapi.databinding.FragmentAddPostBinding;
import com.google.android.material.tabs.TabLayout;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddPostFragment extends Fragment {


    FragmentAddPostBinding binding;
    int IMAGE_REQUEST_CODE = 101;
    private Bitmap bitmap;
    private Uri imageUri;
    String createdAt;
    private SharedPrefManager sharedPrefManager;
    public AddPostFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentAddPostBinding.inflate(inflater, container, false);

        sharedPrefManager=new SharedPrefManager(getContext());


        binding.addPostDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String description = binding.addPostDescription.getText().toString();

                if (!description.isEmpty()) {
                    binding.postBtn.setEnabled(true);
                    binding.postBtn.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.follow_btn));
                    binding.postBtn.setTextColor(getContext().getResources().getColor(R.color.white));
                } else {
                    binding.postBtn.setEnabled(false);
                    binding.postBtn.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.follow_bg_btn));
                    binding.postBtn.setTextColor(getContext().getResources().getColor(R.color.black));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        binding.galleryimgId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, IMAGE_REQUEST_CODE);
            }
        });


        binding.postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              String postType="";
                String image=ImageToString();
                String postText=binding.addPostDescription.getText().toString();



                Call<CreatePost> call=Retrofit_Client
                        .getInstance()
                        .getApi()
                        .CreatePost(sharedPrefManager.getToken(),image,postText);


                call.enqueue(new Callback<CreatePost>() {
                    @Override
                    public void onResponse(Call<CreatePost> call, Response<CreatePost> response) {


                        if (response.isSuccessful())
                        {
                            Toast.makeText(getContext(), "Post is success", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<CreatePost> call, Throwable t) {

                     Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                if(postText.isEmpty() && imageUri==null){
                    Toast.makeText(getContext(), "At Least Image Or Text Need To Add", Toast.LENGTH_SHORT).show();
                }else{
                    if(postText.isEmpty() && imageUri!=null){
                        postType= NewPost.POST_TYPE_IMAGE;
                    }else if(!postText.isEmpty() && imageUri==null){
                        postType=NewPost.POST_TYPE_TEXT;
                    }else if(!postText.isEmpty() && imageUri!=null){
                        postType=NewPost.POST_TYPE_IMAGEANDTEXT;
                    }
                    saveData(postType);
                }

            }
        });


        return binding.getRoot();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            try {
                bitmap= MediaStore.Images.Media.getBitmap(getContext().getContentResolver(),imageUri);
                binding.postImg.setImageBitmap(bitmap);
                binding.postImg.setVisibility(View.VISIBLE);
                binding.addPostDescription.setVisibility(View.VISIBLE);
                binding.postBtn.setEnabled(true);
                binding.postBtn.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.follow_btn));
                binding.postBtn.setTextColor(getContext().getResources().getColor(R.color.white));
            } catch (IOException e) {
                e.printStackTrace();
            }

         //   binding.postImg.setImageURI(imageUri);
        }
    }

    public void saveData(String postType)
    {

        String image= ImageToString();
        String message=binding.addPostDescription.getText().toString();

        if (postType.equals(NewPost.POST_TYPE_TEXT))
        {
            Call<CreatePost> call= Retrofit_Client
                    .getInstance()
                    .getApi()
                    .CreatePost(sharedPrefManager.getToken(),"",message);

            /*call.enqueue(new Callback<CreatePost>() {
                @Override
                public void onResponse(Call<CreatePost> call, Response<CreatePost> response) {

                    if (response.isSuccessful())
                    {
                        Toast.makeText(getContext(), "your text is uploaded", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<CreatePost> call, Throwable t) {

                    Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });*/

        }else if (postType.equals(NewPost.POST_TYPE_IMAGE))
        {

            Call<CreatePost> call=Retrofit_Client
                    .getInstance()
                    .getApi()
                    .CreatePost(sharedPrefManager.getToken(),image,"");


            call.enqueue(new Callback<CreatePost>() {
                @Override
                public void onResponse(Call<CreatePost> call, Response<CreatePost> response) {

                    if (response.isSuccessful())
                    {
                        Toast.makeText(getContext(), "Your image is uploaded", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getContext(), "image upload failed", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<CreatePost> call, Throwable t) {

                    Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });

        }else if (postType.equals(NewPost.POST_TYPE_IMAGEANDTEXT)){

            Call<CreatePost> call=
                    Retrofit_Client
                    .getInstance()
                    .getApi()
                    .CreatePost(sharedPrefManager.getToken(),image,message);

            call.enqueue(new Callback<CreatePost>() {
                @Override
                public void onResponse(Call<CreatePost> call, Response<CreatePost> response) {

                    if (response.isSuccessful())
                    {
                        Toast.makeText(getContext(), "Your image and text is uploaded", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getContext(), "Your image and text upload failed", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<CreatePost> call, Throwable t) {

                    Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    public String ImageToString()
    {
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imageByte=byteArrayOutputStream.toByteArray();
      return Base64.encodeToString(imageByte, Base64.DEFAULT);
    }


}