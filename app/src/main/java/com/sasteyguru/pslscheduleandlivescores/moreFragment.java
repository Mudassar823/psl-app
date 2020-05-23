package com.sasteyguru.pslscheduleandlivescores;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class moreFragment extends Fragment {

CardView cardView,frame,about,fixture,result,points;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_more, container, false);
        cardView=view.findViewById(R.id.venu);
        frame=view.findViewById(R.id.frame);
        about=view.findViewById(R.id.about);
        fixture=view.findViewById(R.id.fixmore);
        result=view.findViewById(R.id.fixresult);
        points=view.findViewById(R.id.pinttable);

        points.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),PointsActivity.class);
                startActivity(intent);
            }
        });
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),ResultActivity.class);
                startActivity(intent);
            }
        });

        fixture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),allFixturesActivity.class);
                startActivity(intent);
            }
        });



        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),venuesActivity.class);
                startActivity(intent);
            }
        });
        frame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),Main2Activity.class);
                startActivity(intent);
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),AboutActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
