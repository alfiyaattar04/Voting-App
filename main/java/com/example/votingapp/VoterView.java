package com.example.votingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class VoterView extends AppCompatActivity {

    EditText searchView;
    RecyclerView recyclerView;
    FirebaseDatabase database;
    DatabaseReference reference;
    myAdapterVoterView myAdapter;
    ArrayList<HelperAddCandidate> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voter_view);

        searchView=findViewById(R.id.searchingRecyclerview);
        recyclerView = findViewById(R.id.recyclerView1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Candidates");

        recyclerView.setHasFixedSize(true);

        list = new ArrayList<>();
        myAdapter = new myAdapterVoterView(this,list);
        recyclerView.setAdapter(myAdapter);

        reference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    HelperAddCandidate user = dataSnapshot.getValue(HelperAddCandidate.class);
                    list.add(user);

                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
    }

    private void filter(String text) {
        ArrayList<HelperAddCandidate> filteredList = new ArrayList<>();

        for (HelperAddCandidate item : list) {
            if (item.getCandidateName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        myAdapter.searchDataList(filteredList);
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(VoterView.this, HomeScreen.class);
        startActivity (intent) ;
        finish();
    }
    }
