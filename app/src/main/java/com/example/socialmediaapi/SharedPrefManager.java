package com.example.socialmediaapi;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.socialmediaapi.Model.RegistrationModel.User;

public class SharedPrefManager {

   String SHARED_PREF_NAME="shanawaj";
   private SharedPreferences sharedPreferences;
   private Context context;
   private SharedPreferences.Editor editor;

    public SharedPrefManager(Context context) {
        this.context = context;
    }

    public void saveUser(User user,String token)
    {
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        editor.putString("_id",user.get_id());
        editor.putString("firstName",user.getFirstName());
        editor.putString("lastName",user.getLastName());
        editor.putString("email",user.getEmail());
        editor.putString("token",token);
        editor.putBoolean("logged",true);
        editor.apply();


    }
    public boolean isLoggedId()
    {
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("logged",false);

    }

    public User getUser()
    {
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return new User(sharedPreferences.getString("_id",null),
                sharedPreferences.getString("firstName",null),
                sharedPreferences.getString("lastName",null),
                sharedPreferences.getString("email",null));
    }

    public void logout()
    {
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public String getToken()
    {
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString("token","none");

    }

}
