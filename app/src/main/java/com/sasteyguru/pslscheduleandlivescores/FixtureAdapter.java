package com.sasteyguru.pslscheduleandlivescores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class FixtureAdapter  extends RecyclerView.Adapter<FixtureAdapter.fixholder>{
ArrayList<fixturesHelper> fixlist;
Context context;

    public FixtureAdapter(ArrayList<fixturesHelper> fixlist, Context context) {
        this.fixlist = fixlist;
        this.context = context;
    }

    @NonNull
    @Override
    public fixholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.fixtures_layout, null);
        return new FixtureAdapter.fixholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull fixholder holder, int position) {
        holder.head.setText(fixlist.get(position).f_head);
        holder.datetime.setText(fixlist.get(position).fdatetime);
        holder.team1.setText(fixlist.get(position).f_team1name);
        holder.team2.setText(fixlist.get(position).f_team2name);
        Picasso.get().load(fixlist.get(position).f_teamimg1).into(holder.img1);
        Picasso.get().load(fixlist.get(position).f_teamimg2).into(holder.img2);
    }

    @Override
    public int getItemCount() {
        return fixlist.size() ;
    }

    public static class fixholder extends RecyclerView.ViewHolder {

TextView head,datetime,team1,team2;
CircleImageView img1,img2;
        public fixholder(@NonNull View itemView) {
            super(itemView);

            head=itemView.findViewById(R.id.fix_heading1);
            datetime=itemView.findViewById(R.id.date_time);
            team1=itemView.findViewById(R.id.fix_team1);
            team2=itemView.findViewById(R.id.fix_team2);
            img1=itemView.findViewById(R.id.fix_team1_img);
            img2=itemView.findViewById(R.id.fix_team2_img);


        }
    }
}
