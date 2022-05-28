package com.example.socialmediaapi.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.socialmediaapi.Model.LoginModel.LoginResponse;
import com.example.socialmediaapi.Model.RegistrationModel.RegistrationResponse;
import com.example.socialmediaapi.Model.RegistrationModel.User;
import com.example.socialmediaapi.R;
import com.example.socialmediaapi.Retrofit_Client;
import com.example.socialmediaapi.SharedPrefManager;
import com.example.socialmediaapi.databinding.ActivitySignUpBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignUpActivity extends AppCompatActivity {


    ActivitySignUpBinding binding;
    SharedPrefManager sharedPrefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        sharedPrefManager=new SharedPrefManager(this);

        binding.gotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this,SignInActivity.class));
                finish();
            }
        });


        binding.registarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email=binding.email.getText().toString();
                String firstName=binding.firstName.getText().toString();
                String lastName=binding.lastName.getText().toString();
                String password=binding.password.getText().toString();
                String dateOfbirth=binding.dateOfBirth.getText().toString();

                if (email.isEmpty())
                {
                    binding.email.setError("Please enter your email");
                    binding.email.requestFocus();
                }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    binding.email.setError("Please enter valid email");
                    binding.email.requestFocus();
                }else if (firstName.isEmpty())
                {
                    binding.firstName.setError("Please enter first name");
                    binding.firstName.requestFocus();
                }else if (lastName.isEmpty())
                {
                    binding.lastName.setError("Please enter last name");
                    binding.lastName.requestFocus();
                }else if (password.isEmpty())
                {
                    binding.password.setError("Please enter your password");
                    binding.password.requestFocus();
                }else if (password.length()<6)
                {
                    binding.password.setError("Please enter your password length minimum 6");
                    binding.password.requestFocus();

                }else if (dateOfbirth.isEmpty())
                {
                    binding.dateOfBirth.setError("please enter your dateOfBirth");
                    binding.dateOfBirth.requestFocus();
                }else
                {
                    Toast.makeText(SignUpActivity.this, "Click Register button", Toast.LENGTH_SHORT).show();

                    Call<RegistrationResponse> call= Retrofit_Client
                            .getInstance()
                            .getApi()
                            .register(email,firstName,lastName,password,dateOfbirth);

                    call.enqueue(new Callback<RegistrationResponse>() {
                        @Override
                        public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {

                            RegistrationResponse registrationResponse=response.body();
                            if (response.isSuccessful())
                            {
                                Toast.makeText(SignUpActivity.this, "Registration is successfully", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(SignUpActivity.this, "something is wrong", Toast.LENGTH_SHORT).show();
                             }
                        }

                        @Override
                        public void onFailure(Call<RegistrationResponse> call, Throwable t) {

                            Toast.makeText(SignUpActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });


    }
}