package com.example.socialmediaapi.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.util.Printer;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.socialmediaapi.Api;
import com.example.socialmediaapi.Model.LoginModel.LoginResponse;
import com.example.socialmediaapi.R;
import com.example.socialmediaapi.Retrofit_Client;
import com.example.socialmediaapi.SharedPrefManager;
import com.example.socialmediaapi.databinding.ActivitySignInBinding;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignInActivity extends AppCompatActivity {


    ActivitySignInBinding binding;
    SharedPrefManager sharedPrefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        sharedPrefManager=new SharedPrefManager(this);

        binding.createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(SignInActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });

        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email=binding.email.getText().toString();
                String password=binding.password.getText().toString();

                if (email.isEmpty())
                {
                    binding.email.setError("please enter your email");
                    binding.email.requestFocus();
                }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    binding.email.setError("Please enter valid email");
                    binding.email.requestFocus();
                }else if (password.isEmpty())
                {
                    binding.password.setError("Please enter your password");
                    binding.password.requestFocus();
                }else if (password.length()<6)
                {
                    binding.password.setError("Please enter password length should be6");
                    binding.password.requestFocus();
                }
                else {


                    Call<LoginResponse> call= Retrofit_Client
                            .getInstance()
                            .getApi()
                            .login(email,password);

                    call.enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                            LoginResponse loginResponse=response.body();

                            if (response.isSuccessful())
                            {
                                Toast.makeText(SignInActivity.this, "Login success", Toast.LENGTH_SHORT).show();
                                sharedPrefManager.saveUser(loginResponse.getUser(),loginResponse.getToken());
                                startActivity(new Intent(SignInActivity.this,MainActivity.class));
                                finish();
                            }else {
                                Toast.makeText(SignInActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {

                            Toast.makeText(SignInActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();

        if (sharedPrefManager.isLoggedId())
        {
            startActivity(new Intent(SignInActivity.this,MainActivity.class));
            finish();
        }
    }

}