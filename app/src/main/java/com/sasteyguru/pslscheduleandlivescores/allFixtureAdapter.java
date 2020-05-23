package com.sasteyguru.pslscheduleandlivescores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;



public class allFixtureAdapter  extends RecyclerView.Adapter<allFixtureAdapter.fixholder>{
    ArrayList<fixturesHelper> fixlist;
    Context context;

    public allFixtureAdapter(ArrayList<fixturesHelper> fixlist, Context context) {
        this.fixlist = fixlist;
        this.context = context;
    }

    @NonNull
    @Override
    public fixholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_fix_layout, null);
        return new allFixtureAdapter.fixholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull fixholder holder, int position) {
        holder.fixdate.setText(fixlist.get(position).fdatetime);
        holder.fixtime.setText(fixlist.get(position).time);
        holder.fixdes.setText(fixlist.get(position).f_head);

    }

    @Override
    public int getItemCount() {
        return fixlist.size() ;
    }

    public static class fixholder extends RecyclerView.ViewHolder {

        TextView fixdate,fixdes,fixtime;

        public fixholder(@NonNull View itemView) {
            super(itemView);

           fixdate=itemView.findViewById(R.id.fixdate);
           fixdes=itemView.findViewById(R.id.fixdetail);
           fixtime=itemView.findViewById(R.id.fixtime);



        }
    }
}

