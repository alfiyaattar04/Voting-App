package com.example.votingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomeScreen extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME="MyPref";
    private static final String KEY_NAME="Name";
    private static final String KEY_EMAIL="Email";
    private static final String KEY_NUMBER="Number";
    private static final String KEY_LOCATION="Location";
    Button voterHomeScreen,CandidateHomeScreen,Logout;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);


        sharedPreferences=getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        String frmSignupName=sharedPreferences.getString(KEY_NAME,null);
        String frmSignupEmail=sharedPreferences.getString(KEY_EMAIL,null);
        String frmSignupNumner=sharedPreferences.getString(KEY_NUMBER,null);
        String frmSignupLocation=sharedPreferences.getString(KEY_LOCATION,null);


        voterHomeScreen=findViewById(R.id.voterHomeScreen);
        CandidateHomeScreen=findViewById(R.id.CandidateHomeScreen);
        Logout=findViewById(R.id.Logout);

        voterHomeScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreen.this, VoterView.class);
                startActivity (intent) ;
                finish();
            }
        });

        CandidateHomeScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreen.this, CandidateView.class);
                startActivity (intent) ;
                finish();
            }
        });



        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.clear();
                editor.apply();
                Toast.makeText(HomeScreen.this, "Logging out successful", Toast.LENGTH_SHORT).show();

                HomeScreen.super.onBackPressed();
            }
        });
    }
    @Override
    public void onBackPressed() {
        HomeScreen.super.onBackPressed();
    }
}