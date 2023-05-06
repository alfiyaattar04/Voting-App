package com.example.votingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class CandidateView extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference reference;

    EditText CandidateViewName,CandidateViewEmail,CandidateViewMobNo,CandidateViewAge,CandidateViewAddress;
    Button CandidateViewSubmitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_view);

        CandidateViewName=findViewById(R.id.CandidateViewName);
        CandidateViewEmail=findViewById(R.id.CandidateViewEmail);
        CandidateViewMobNo=findViewById(R.id.CandidateViewMobNo);
        CandidateViewAge=findViewById(R.id.CandidateViewAge);
        CandidateViewAddress=findViewById(R.id.CandidateViewAddress);
        CandidateViewSubmitButton=findViewById(R.id.CandidateViewSubmitButton);

        CandidateViewSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String NameCandidateToUpload = CandidateViewName.getText().toString().trim();
                String EmailCandidateToUpload = CandidateViewEmail.getText().toString().trim();
                String MobNoCandidateToUpload = CandidateViewMobNo.getText().toString().trim();
                String AgeCandidateToUpload = CandidateViewAge.getText().toString().trim();
                String AddressCandidateToUpload = CandidateViewAddress.getText().toString().trim();
                String VoteCandidateToUpload = "0";
                String userIdUsed="Yes";

                database = FirebaseDatabase.getInstance();
                reference = database.getReference("Candidates");
//                String verifyCandidateuserIdUsedOrNot = String.valueOf(reference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(" CandidateuserIdused"));
//                Toast.makeText(CandidateView.this, verifyCandidateuserIdUsedOrNot, Toast.LENGTH_SHORT).show();


                if (NameCandidateToUpload.isEmpty() || EmailCandidateToUpload.isEmpty() || MobNoCandidateToUpload.isEmpty() ||AgeCandidateToUpload.isEmpty() ||AddressCandidateToUpload.isEmpty() ){
                    Toast.makeText(CandidateView.this, "There are empty fields", Toast.LENGTH_SHORT).show();
                }
                else {

                    String uidRTDB=FirebaseAuth.getInstance().getCurrentUser().getUid();

                    HelperAddCandidate uploadDatatoFirebase = new HelperAddCandidate(NameCandidateToUpload, EmailCandidateToUpload,MobNoCandidateToUpload,AgeCandidateToUpload,AddressCandidateToUpload,uidRTDB,VoteCandidateToUpload,userIdUsed);
                    reference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(uploadDatatoFirebase);

                    Toast.makeText(CandidateView.this, "Form Submitted Successfully", Toast.LENGTH_SHORT).show();

                }
            }});
    }
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(CandidateView.this, HomeScreen.class);
        startActivity (intent) ;
        finish();
    }
}