package com.sasteyguru.pslscheduleandlivescores;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class viewPagerAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;
    ArrayList<sliderHelper> List;

    public viewPagerAdapter(Context context, ArrayList<sliderHelper> list) {
        this.context = context;
        List = list;
    }

    @Override
    public int getCount() {
        return List.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view= layoutInflater.inflate(R.layout.custom_layout,null);
        ImageView imageView=view.findViewById(R.id.custom_image);
        Picasso.get().load(List.get(position).slider_image).into(imageView);
        TextView  textView=view.findViewById(R.id.t1);
        textView.setText(List.get(position).slider_head);
        TextView textView1=view.findViewById(R.id.t2);
        textView1.setText(List.get(position).news_des);
        TextView textView2=view.findViewById(R.id.date);
        textView2.setText(List.get(position).news_date);



        view.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                sliderHelper slider=List.get(position);
                //this will log the page number that was click
                Log.i("TAG", "This page was clicked: " + position);
                Intent intent=new Intent(context,NewsdetailActivity.class);
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("img",slider.slider_image);
                intent.putExtra("head",slider.slider_head);
                intent.putExtra("date",slider.news_date);
                intent.putExtra("des",slider.news_des);
                intent.putExtra("ref",slider.sub_title);

                context.startActivity(intent );


            }
        });
        ViewPager vp=(ViewPager) container;
        vp.addView(view,0);
        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager vp=(ViewPager) container;
        View view=(View)object;
        vp.removeView(view);
    }
}
