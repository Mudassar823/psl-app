package com.sasteyguru.pslscheduleandlivescores;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class AllnewsDetaiActivity extends AppCompatActivity {
    ImageView imageView;
    TextView head,des,date,refrence;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allnews_detai);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("News Detail");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle bundle=getIntent().getExtras();
        String image=bundle.getString("img");
        String heading=bundle.getString("head");
        String Des=bundle.getString("des");
        String Date=bundle.getString("date");
        String Ref=bundle.getString("ref");
        imageView=findViewById(R.id.allnewsimage);
        head=findViewById(R.id.all);
        des=findViewById(R.id.allnewsdec);
        date=findViewById(R.id.allnewsdate);
        refrence=findViewById(R.id.allnewsref);
        Picasso.get().load(image).into(imageView);
        head.setText(heading);
        date.setText(Date);
        des.setText(Des);
        refrence.setText(Ref);
    }
}
