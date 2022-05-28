package com.example.socialmediaapi.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import com.example.socialmediaapi.Fragments.AddPostFragment;
import com.example.socialmediaapi.Fragments.HomeFragment;
import com.example.socialmediaapi.Fragments.ProfileFragment;
import com.example.socialmediaapi.Fragments.UsersFragment;
import com.example.socialmediaapi.R;
import com.example.socialmediaapi.SharedPrefManager;
import com.example.socialmediaapi.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    SharedPrefManager sharedPrefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sharedPrefManager=new SharedPrefManager(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new HomeFragment()).commit();

        binding.navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                switch (item.getItemId())
                {
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new HomeFragment()).commit();
                        break;
                    case R.id.friend:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new UsersFragment()).commit();
                        break;
                    case R.id.post:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new AddPostFragment()).commit();
                        break;
                    case R.id.profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new ProfileFragment()).commit();
                        break;
                }

                return true;
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.logout)
        {
            logoutUser();
        }
        return super.onOptionsItemSelected(item);
    }

    public void logoutUser()
    {

        sharedPrefManager.logout();
        startActivity(new Intent(MainActivity.this,SignInActivity.class));
        finish();
        Toast.makeText(this, "You have been logout", Toast.LENGTH_SHORT).show();
    }
}