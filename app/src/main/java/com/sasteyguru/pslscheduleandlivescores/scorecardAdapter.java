package com.sasteyguru.pslscheduleandlivescores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class scorecardAdapter extends RecyclerView.Adapter<scorecardAdapter.scoreholder>{

ArrayList<scorecardHelper> list;
Context context;

    public scorecardAdapter(ArrayList<scorecardHelper> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public scoreholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.scorecard_layout, null);
        return new scorecardAdapter.scoreholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull scoreholder holder, int position) {
        Picasso.get().load(list.get(position).team1_img_ining_1).into(holder.in11);
        Picasso.get().load(list.get(position).team2_img_ining_1).into(holder.in12);
        Picasso.get().load(list.get(position).team1_img_ining_2).into(holder.in21);
        Picasso.get().load(list.get(position).team2_img_ining_2).into(holder.in22);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class scoreholder extends RecyclerView.ViewHolder{
        ImageView in11,in12,in21,in22;
        public scoreholder(@NonNull View itemView) {
            super(itemView);
            in11=itemView.findViewById(R.id.in11);
            in12=itemView.findViewById(R.id.in12);
            in21=itemView.findViewById(R.id.in21);
            in22=itemView.findViewById(R.id.in22);
        }
    }
}
