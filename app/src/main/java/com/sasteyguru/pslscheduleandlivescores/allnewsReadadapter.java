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

public class allnewsReadadapter  extends RecyclerView.Adapter<allnewsReadadapter.newsHolder>{

    ArrayList<newsHelper> newsList;
    Context context;

    public allnewsReadadapter(ArrayList<newsHelper> newsList, Context context) {
        this.newsList = newsList;
        this.context = context;
    }

    @NonNull
    @Override
    public newsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.readallnews, null);
        return new newsHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull newsHolder holder, final int position) {
        holder.title.setText(newsList.get(position).news_subtitle);
        holder.desc.setText(newsList.get(position).news_desc);
        Picasso.get().load(newsList.get(position).news_image).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newsHelper helper=newsList.get(position);
                Intent intent=new Intent(context,AllnewsDetaiActivity.class);
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("img",helper.news_image);
                intent.putExtra("head",helper.news_head);
                intent.putExtra("des",helper.news_desc);
                intent.putExtra("date",helper.news_date);
                intent.putExtra("ref",helper.news_subtitle);

                context.startActivity(intent );
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public static class newsHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title,desc;

        public newsHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.readallnewsimg);
            title=itemView.findViewById(R.id.allreadhead);
            desc=itemView.findViewById(R.id.desall);
        }
    }

}

