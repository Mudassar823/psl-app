package com.sasteyguru.pslscheduleandlivescores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterForVenues extends RecyclerView.Adapter<AdapterForVenues.holder>{
    ArrayList<venuesHelper> list;
    Context context;

    public AdapterForVenues(ArrayList<venuesHelper> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.stadium_layout, parent, false);
        AdapterForVenues. holder evh = new AdapterForVenues.holder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        holder.cityName.setText(list.get(position).city);
        holder.stadiumName.setText(list.get(position).stname);
        holder.capacity.setText("Capacity :"+list.get(position).capacity);
        Picasso.get().load(list.get(position).image).into(holder.image);
        holder.matches.setText("Matches "+list.get(position).matches);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class holder extends RecyclerView.ViewHolder {
        TextView cityName,stadiumName,capacity,matches;
        ImageView image;
        public holder(@NonNull View itemView) {
            super(itemView);
            cityName=itemView.findViewById(R.id.stNmae);
            stadiumName=itemView.findViewById(R.id.fullName);
            capacity=itemView.findViewById(R.id.cap);
            image=itemView.findViewById(R.id.stimage);
            matches=itemView.findViewById(R.id.played_matches);

        }
    }
}
