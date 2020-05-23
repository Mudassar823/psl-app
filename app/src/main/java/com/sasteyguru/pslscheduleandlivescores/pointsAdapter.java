package com.sasteyguru.pslscheduleandlivescores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class pointsAdapter extends RecyclerView.Adapter<pointsAdapter.pointHolder> {
    ArrayList<pointsHelper> pointslist;

    public pointsAdapter(ArrayList<pointsHelper> pointslist) {
        this.pointslist = pointslist;
    }

    @NonNull
    @Override
    public pointHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.points_layout, parent, false);
        pointsAdapter. pointHolder evh = new pointsAdapter.pointHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull pointHolder holder, int position) {
        holder.pos.setText(""+pointslist.get(position).team_position);
        holder.team.setText(pointslist.get(position).p_teamname);
        holder.match.setText(""+pointslist.get(position).toalplayed);
        holder.won.setText(""+pointslist.get(position).win);
        holder.loss.setText(""+pointslist.get(position).lost);
        holder.points.setText(pointslist.get(position).points);
    }

    @Override
    public int getItemCount() {
        return pointslist.size();
    }

    public static class pointHolder extends RecyclerView.ViewHolder {
        TextView pos,team,match,won,loss,points;


        public pointHolder(@NonNull View itemView) {
            super(itemView);
            pos=itemView.findViewById(R .id.pos);
            team=itemView.findViewById(R.id.team);
            match=itemView.findViewById(R.id.match);
            won=itemView.findViewById(R.id.won);
            loss=itemView.findViewById(R.id.lost);
            points=itemView.findViewById(R.id.point);

        }
    }
}
