package com.example.votingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText signupEmail, signupPassword,signupName,signupMobNo,signupLocation;
    private Button signupButton;
    private TextView loginRedirectText;
    FirebaseDatabase database;
    DatabaseReference reference;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME="MyPref";
    private static final String KEY_NAME="Name";
    private static final String KEY_EMAIL="Email";
    private static final String KEY_NUMBER="Number";
    private static final String KEY_USERID="UserID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        sharedPreferences=getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();


        auth = FirebaseAuth.getInstance();
        signupName=findViewById(R.id.signuppersonname);
        signupEmail = findViewById(R.id.signup_email);
        signupPassword = findViewById(R.id.signup_password);
        signupButton = findViewById(R.id.signup_button);
        loginRedirectText = findViewById(R.id.loginRedirectText);
        signupMobNo=findViewById(R.id.signup_mobileNumber);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = signupName.getText().toString().trim();
                String user = signupEmail.getText().toString().trim();
                String pass = signupPassword.getText().toString().trim();
                String userMobNo = signupMobNo.getText().toString().trim();

                if (userName.isEmpty()){
                    signupName.setError("Name cannot be empty");
                }

                if (user.isEmpty()){
                    signupEmail.setError("Email cannot be empty");
                }

                if (userMobNo.isEmpty()){
                    signupMobNo.setError("Mobile Number cannot be empty");
                }

                if (pass.isEmpty()){
                    signupPassword.setError("Password cannot be empty");
                }
                else{

                    auth.createUserWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                database = FirebaseDatabase.getInstance();
                                reference = database.getReference("Users");

                                String nameRTDB = signupName.getText().toString().trim();
                                String emailRTDB = signupEmail.getText().toString().trim();
                                String passwordRTDB = signupPassword.getText().toString().trim();
                                String mobnoRTDB= signupMobNo.getText().toString().trim();
                                String activityRTDB ="No";
                                String uidRTDB=FirebaseAuth.getInstance().getCurrentUser().getUid();
                                HelperRTDB helperReference = new  HelperRTDB (nameRTDB, emailRTDB, passwordRTDB,mobnoRTDB,activityRTDB,uidRTDB );
                                reference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(helperReference);

                                editor.putString(KEY_NAME,userName);
                                editor.putString(KEY_EMAIL,user);
                                editor.putString(KEY_NUMBER,userMobNo);
                                editor.apply();

                                Toast.makeText(Signup.this, "SignUp Successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), HomeScreen.class);
                                startActivity (intent) ;
                                finish();

                            } else {
                                Toast.makeText(Signup.this, "SignUp Failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity (intent) ;
                finish();
            }
        });

    }
}