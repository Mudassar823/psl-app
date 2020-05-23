package com.sasteyguru.pslscheduleandlivescores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class TeamsInfoAdapter extends RecyclerView.Adapter<TeamsInfoAdapter.teamholder>{
    ArrayList<TeamsHelper> TeamsNamelist;
    Context context;

    public TeamsInfoAdapter(ArrayList<TeamsHelper> teamsNamelist) {
        TeamsNamelist = teamsNamelist;
    }

    public TeamsInfoAdapter(ArrayList<TeamsHelper> teamsNamelist, Context context) {
        TeamsNamelist = teamsNamelist;
        this.context = context;
    }

    @NonNull
    @Override
    public teamholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.teams_layout, parent, false);
        TeamsInfoAdapter. teamholder evh = new TeamsInfoAdapter.teamholder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull teamholder holder, final int position) {
        holder.teanName.setText(TeamsNamelist.get(position).team_name);
        Picasso.get().load(TeamsNamelist.get(position).team_image).into(holder.teamImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TeamsHelper helper=TeamsNamelist.get(position);
                Intent intent =new Intent(context,Teams_PlayersActivity.class);
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("team_id",helper.team_id);
                context.startActivity(intent );
            }
        });

    }

    @Override
    public int getItemCount() {
        return TeamsNamelist.size();
    }

    public static class teamholder extends RecyclerView.ViewHolder {

        TextView teanName;
        ImageView teamImage;

        public teamholder(@NonNull View itemView) {
            super(itemView);
            teanName=itemView.findViewById(R.id.psl_teams);
            teamImage=itemView.findViewById(R.id.psl_image);
        }
    }
}
