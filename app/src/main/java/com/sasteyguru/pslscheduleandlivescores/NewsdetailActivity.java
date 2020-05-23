package com.sasteyguru.pslscheduleandlivescores;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class NewsdetailActivity extends AppCompatActivity {

ImageView imageView;
TextView head,des,date,refrence;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsdetail);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("News Detail");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle bundle=getIntent().getExtras();


        String image=bundle.getString("img");
        String heading=bundle.getString("head");
        String Des=bundle.getString("des");
        String Date=bundle.getString("date");
        String Ref=bundle.getString("ref");
        imageView=findViewById(R.id.sliderimage);
        head=findViewById(R.id.news_heading);
        des=findViewById(R.id.newsdec);
        date=findViewById(R.id.newsdate);
        refrence=findViewById(R.id.newsref);
        Picasso.get().load(image).into(imageView);
       head.setText(heading);
       date.setText(Date);
       des.setText(Des);
       refrence.setText(Ref);

    }
}
