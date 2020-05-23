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

public class videoAdapter extends RecyclerView.Adapter<videoAdapter.videoHolder>{
ArrayList<videoHelper> list;
Context context;

    public videoAdapter(ArrayList<videoHelper> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public videoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_layout, null);
        return new videoAdapter.videoHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull videoHolder holder, final int position) {
    holder.textView.setText(list.get(position).v_title);
        Picasso.get().load(list.get(position).v_img).into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoHelper helper=list.get(position);

                Intent intent =new Intent(context,videoActivity.class);
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("link",helper.v_link);
                context.startActivity(intent );

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static  class videoHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;
        public videoHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.vimg);
         textView=itemView.findViewById(R.id.vtitile);

        }
    }
}
