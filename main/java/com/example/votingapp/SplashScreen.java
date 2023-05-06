package com.example.votingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

public class SplashScreen extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    private static final String SHARED_PREF_NAME="MyPref";
    private static final String KEY_NAME="Name";
    private static final String KEY_EMAIL="Email";
    private static final String LKEY_EMAIL="Email";
    private static final String KEY_NUMBER="Number";


    Handler handler=new Handler(Looper.getMainLooper());
    public static int SPLASH_TIMER = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        sharedPreferences=getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);

        String frmSignupName=sharedPreferences.getString(KEY_NAME,null);

        String frmLoginEmail=sharedPreferences.getString(LKEY_EMAIL,null);

        String frmSignupEmail=sharedPreferences.getString(KEY_EMAIL,null);
        String frmSignupNumner=sharedPreferences.getString(KEY_NUMBER,null);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if(frmSignupEmail!=null || frmLoginEmail!=null)
                {
                    Intent intent = new Intent(SplashScreen.this, HomeScreen.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Intent intent = new Intent(SplashScreen.this, Signup.class);
                    startActivity(intent);
                    finish();
                }
                finish();
            }
        }, SPLASH_TIMER);
    }
}