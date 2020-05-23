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

import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class allResultsAdapter extends RecyclerView.Adapter<allResultsAdapter.resultHolder>{
    ArrayList<matchResultsHelper> list;
    Context context;

    public allResultsAdapter(ArrayList<matchResultsHelper> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public resultHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_resultlayout, null);
        return new allResultsAdapter.resultHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull resultHolder holder, final int position) {

        holder.heading.setText(list.get(position).m_detail);
        holder.team1name.setText(list.get(position).team1_name);
        holder.team2name.setText(list.get(position).team2_name);
        holder.result.setText(list.get(position).m_conclusion);
        holder.team1score.setText(list.get(position).team1total);
        holder.team2score.setText(list.get(position).team2total);
        Picasso.get().load(list.get(position).team1_flag).into(holder.team1image);
        Picasso.get().load(list.get(position).team2_flag).into(holder.team2img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                matchResultsHelper helper=list.get(position);
                Intent intent =new Intent(context,scorecardactivity.class);
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("match_no",helper.match_no);
                context.startActivity(intent );
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class resultHolder extends RecyclerView.ViewHolder {


        TextView team1name,team2name,heading,result,team1score,team2score;
        CircleImageView team1image,team2img;
        ImageView scorecard;


        public resultHolder(@NonNull View itemView) {
            super(itemView);

            team1image= itemView.findViewById(R.id.team1img);
           team2img=itemView.findViewById(R.id.team2img);
            heading=itemView.findViewById(R.id.des);
            result=itemView.findViewById(R.id.matchresult);
            team1name=itemView.findViewById(R.id.teamname1);
            team2name=itemView.findViewById(R.id.teamname2);
            team1score=itemView.findViewById(R.id.team1score);
            team2score=itemView.findViewById(R.id.team2score);
         //   scorecard=itemView.findViewById(R.id.scoreboard);
        }
    }
}
