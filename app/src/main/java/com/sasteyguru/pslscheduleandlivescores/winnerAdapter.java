package com.sasteyguru.pslscheduleandlivescores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class winnerAdapter extends RecyclerView.Adapter<winnerAdapter.holder> {

    ArrayList<winnerHelper> list;

    public winnerAdapter(ArrayList<winnerHelper> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.winner_layout, parent, false);
        winnerAdapter. holder evh = new winnerAdapter.holder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        winnerHelper currentItem = list.get(position);
        holder.year.setText(currentItem.getYear());
        holder.winner.setText(currentItem.getWinner());
        holder.runnerup.setText(currentItem.getRunner());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class holder extends RecyclerView.ViewHolder {

TextView year,winner,runnerup,margin;
        public holder(@NonNull View itemView) {
            super(itemView);
            year=itemView.findViewById(R.id.year);
            winner=itemView.findViewById(R.id.winner);
            runnerup=itemView.findViewById(R.id.runnerup);


        }
    }
}
