package com.example.votingapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.text.BreakIterator;
import java.util.ArrayList;

public class myAdapterVoterView extends RecyclerView.Adapter<myAdapterVoterView.MyViewHolder> {

    Context context;

    ArrayList<HelperAddCandidate> list;


    public myAdapterVoterView(Context context, ArrayList<HelperAddCandidate> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.candidateviewtovoter,parent,false);
        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        HelperAddCandidate user1 = list.get(position);

        holder.recCandidateName.setText(user1.getCandidateName());
        holder.recCandidateAge.setText(user1.getCandidateEmail());
        holder.recrecCandidateaddress.setText(user1.getCandidateaddress());
        holder.recrecCandidateButtuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void searchDataList(ArrayList<HelperAddCandidate> searchList){
        list = searchList;
        notifyDataSetChanged();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder  implements  View.OnClickListener{

        TextView recCandidateName,recCandidateAge,recrecCandidateaddress;
        Button recrecCandidateButtuon;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            recCandidateName = itemView.findViewById(R.id.recCandidateName);
            recCandidateAge = itemView.findViewById(R.id.recCandidateAge);
            recrecCandidateaddress = itemView.findViewById(R.id.recrecCandidateaddress);
            recrecCandidateButtuon = itemView.findViewById(R.id.recrecCandidateButtuon);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int postion = getAdapterPosition();
            Intent intent = new Intent(context , EachCandidate.class);
            intent.putExtra("UIDOFCANDIDATEFROMMYADAPTERVOTERVIEW" , list.get(postion).getCandidateuserid());
            intent.putExtra("EMAILOFCANDIDATEFROMMYADAPTERVOTERVIEW" , list.get(postion).getCandidateEmail());
            intent.putExtra("VOTESOFCANDIDATEFROMMYADAPTERVOTERVIEW" , list.get(postion).getCandidateVote());
            intent.putExtra("AGEOFCANDIDATEFROMMYADAPTERVOTERVIEW" , list.get(postion).getCandidateAge());
            intent.putExtra("MOBNOOFCANDIDATEFROMMYADAPTERVOTERVIEW" , list.get(postion).getCandidateMobNo());
            intent.putExtra("NAMENOOFCANDIDATEFROMMYADAPTERVOTERVIEW" , list.get(postion).getCandidateName());
            intent.putExtra("ADDRESSNOOFCANDIDATEFROMMYADAPTERVOTERVIEW" , list.get(postion).getCandidateaddress());
            context.startActivity(intent);
          //  Toast.makeText(context.getApplicationContext(), "SignUp Successful", Toast.LENGTH_SHORT).show();

        }
    }
}