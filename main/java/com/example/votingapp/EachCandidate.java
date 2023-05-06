package com.example.votingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class EachCandidate extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference reference;
    Button VoteEachCandidate;
    TextView NameEachCandidatePage, EmailEachCandidatePage, AgeNoEachCandidatePage, AddressEachCandidatePage, VoteEachCandidatePage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_each_candidate);

        VoteEachCandidate = findViewById(R.id.VoteEachCandidate);
        NameEachCandidatePage = findViewById(R.id.NameEachCandidatePage);
        EmailEachCandidatePage = findViewById(R.id.EmailEachCandidatePage);
        AgeNoEachCandidatePage = findViewById(R.id.AgeNoEachCandidatePage);
        AddressEachCandidatePage = findViewById(R.id.AddressEachCandidatePage);
        VoteEachCandidatePage = findViewById(R.id.VoteEachCandidatePage);

showfirebaseData();
    }

    public void showfirebaseData() {


        FirebaseUser userr = FirebaseAuth.getInstance().getCurrentUser();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        reference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    String activityFromDB = snapshot.child("activity").getValue(String.class);
                    Toast.makeText(EachCandidate.this, activityFromDB, Toast.LENGTH_SHORT).show();
                    if (activityFromDB == "Yes") {
                        Toast.makeText(EachCandidate.this, "You have already submitted your response", Toast.LENGTH_SHORT).show();

                    } else {
                    voting();

                        String nameFromDB = snapshot.child("name").getValue(String.class);
                        String emailFromDB = snapshot.child("email").getValue(String.class);
                        String mobNoFromDB = snapshot.child("mobNo").getValue(String.class);
                        String uidNoFromDB = snapshot.child("uid").getValue(String.class);
                        String passwordFromDB = snapshot.child("password").getValue(String.class);

                        Map<String, Object> map1 = new HashMap<>();
                        map1.put("activity", "No");
                        map1.put("email", emailFromDB);
                        map1.put("mobNo", mobNoFromDB);
                        map1.put("name", nameFromDB);
                        map1.put("password", passwordFromDB);
                        map1.put("uid", uidNoFromDB);

                        FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .updateChildren(map1)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {

                                        Toast.makeText(EachCandidate.this, "Ok", Toast.LENGTH_SHORT).show();

                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(EachCandidate.this, "Not OK", Toast.LENGTH_SHORT).show();

                                    }
                                });
                    }



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void voting()
    {

        Intent intent = getIntent();
        String UIDOFCANDIDATEFROMMYADAPTERVOTERVIEW = intent.getStringExtra("UIDOFCANDIDATEFROMMYADAPTERVOTERVIEW");
        String EMAILOFCANDIDATEFROMMYADAPTERVOTERVIEW = intent.getStringExtra("EMAILOFCANDIDATEFROMMYADAPTERVOTERVIEW");
        String VOTESOFCANDIDATEFROMMYADAPTERVOTERVIEW = intent.getStringExtra("VOTESOFCANDIDATEFROMMYADAPTERVOTERVIEW");
        String AGEOFCANDIDATEFROMMYADAPTERVOTERVIEW = intent.getStringExtra("AGEOFCANDIDATEFROMMYADAPTERVOTERVIEW");
        String MOBNOOFCANDIDATEFROMMYADAPTERVOTERVIEW = intent.getStringExtra("MOBNOOFCANDIDATEFROMMYADAPTERVOTERVIEW");
        String NAMENOOFCANDIDATEFROMMYADAPTERVOTERVIEW = intent.getStringExtra("NAMENOOFCANDIDATEFROMMYADAPTERVOTERVIEW");
        String ADDRESSNOOFCANDIDATEFROMMYADAPTERVOTERVIEW = intent.getStringExtra("ADDRESSNOOFCANDIDATEFROMMYADAPTERVOTERVIEW");

        NameEachCandidatePage.setText(NAMENOOFCANDIDATEFROMMYADAPTERVOTERVIEW);
        EmailEachCandidatePage.setText(EMAILOFCANDIDATEFROMMYADAPTERVOTERVIEW);
        AgeNoEachCandidatePage.setText(AGEOFCANDIDATEFROMMYADAPTERVOTERVIEW);
        AddressEachCandidatePage.setText(ADDRESSNOOFCANDIDATEFROMMYADAPTERVOTERVIEW);
        VoteEachCandidatePage.setText(VOTESOFCANDIDATEFROMMYADAPTERVOTERVIEW);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Candidates").child(UIDOFCANDIDATEFROMMYADAPTERVOTERVIEW);
        Integer voteInFBDB = Integer.valueOf(VOTESOFCANDIDATEFROMMYADAPTERVOTERVIEW) + 1;
        String voteToUploadOnFBDB = String.valueOf(voteInFBDB);

        String uidRTDB = FirebaseAuth.getInstance().getCurrentUser().getUid();

        VoteEachCandidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> map = new HashMap<>();
                map.put("candidateAge", AGEOFCANDIDATEFROMMYADAPTERVOTERVIEW);
                map.put("candidateEmail", EMAILOFCANDIDATEFROMMYADAPTERVOTERVIEW);
                map.put("candidateMobNo", MOBNOOFCANDIDATEFROMMYADAPTERVOTERVIEW);
                map.put("candidateName", NAMENOOFCANDIDATEFROMMYADAPTERVOTERVIEW);
                map.put("candidateVote", voteToUploadOnFBDB);
                map.put("candidateaddress", ADDRESSNOOFCANDIDATEFROMMYADAPTERVOTERVIEW);
                map.put("candidateuserid", UIDOFCANDIDATEFROMMYADAPTERVOTERVIEW);

                FirebaseDatabase.getInstance().getReference("Candidates").child(UIDOFCANDIDATEFROMMYADAPTERVOTERVIEW)
                        .updateChildren(map)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                                Toast.makeText(EachCandidate.this, "Response Submitted Successfully", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(EachCandidate.this, "Response not submitted", Toast.LENGTH_SHORT).show();

                            }
                        });


            }
        });
    }
}