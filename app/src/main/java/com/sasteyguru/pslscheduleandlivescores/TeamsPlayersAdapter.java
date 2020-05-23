package com.sasteyguru.pslscheduleandlivescores;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class TeamsPlayersAdapter extends RecyclerView.Adapter<TeamsPlayersAdapter.playerholder>{
    Dialog dialog;
    ArrayList<TeamsplayerHelper> list;


    public TeamsPlayersAdapter(ArrayList<TeamsplayerHelper> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public playerholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.teams_players_layout, parent, false);
        final TeamsPlayersAdapter. playerholder evh = new TeamsPlayersAdapter.playerholder(v);
        dialog=new Dialog(parent.getContext());
        dialog.setContentView(R.layout.custom_player_detail);
        evh.clickItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView playerName=(TextView)dialog.findViewById(R.id.playername);
                TextView format=(TextView)dialog.findViewById(R.id.playerformat);
                TextView des=(TextView)dialog.findViewById(R.id.playerdes);
                ImageView img=(ImageView) dialog.findViewById(R.id.playerImage);

                playerName.setText(list.get(evh.getAdapterPosition()).player_name);
                format.setText(list.get(evh.getAdapterPosition()).player_format);
                des.setText(list.get(evh.getAdapterPosition()).player_des);
                Picasso.get().load(list.get(evh.getAdapterPosition()).playerimg).into(img);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull playerholder holder, int position) {
holder.name.setText(list.get(position).player_name);
holder.des.setText(list.get(position).player_des);
holder.role.setText(list.get(position).player_format);
        Picasso.get().load(list.get(position).playerimg).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class playerholder extends RecyclerView.ViewHolder {

        TextView name,des,role;
        CircleImageView image;
        CardView clickItem;


        public playerholder(@NonNull View itemView) {
            super(itemView);
           clickItem=itemView.findViewById(R.id.card);
            name=itemView.findViewById(R.id.teamplayername);
            des=itemView.findViewById(R.id.teamplayerdes);
            role=itemView.findViewById(R.id.role);
            image=itemView.findViewById(R.id.teamplayerimg);
        }
    }
}
