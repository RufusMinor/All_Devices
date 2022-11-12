package com.example.alldevices;

import static com.example.alldevices.RegistrationActivity.APP_PREFERENCE;
import static com.example.alldevices.RegistrationActivity.APP_PREFERENCE_UID;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {
private  static final int SPLASH_SCREEN=3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences = getSharedPreferences(APP_PREFERENCE, Context.MODE_PRIVATE);
                String login1=sharedPreferences.getString(APP_PREFERENCE_UID,"");
                if (login1!=null){
                Intent intent=new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);}
                else{
                    Intent intent=new Intent(SplashScreen.this, SingInActivity.class);
                    startActivity(intent);
                }
            }
        },SPLASH_SCREEN);
    }


}