package com.sasteyguru.pslscheduleandlivescores;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


public class Main2Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("SELECT TEAM");
        setContentView(R.layout.activity_main2);


    }
    public void start1(View view){
        Intent intent=new Intent(this,lahore.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slideinright, R.anim.slideoutleft);
    }

    public void start2(View view){
        Intent intent=new Intent(this,karachi.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slideinright, R.anim.slideoutleft);
    }
    public void start3(View view){
        Intent intent=new Intent(this,peshawar.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slideinright, R.anim.slideoutleft);
    }
    public void start4(View view){
        Intent intent=new Intent(this,islamabad.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slideinright, R.anim.slideoutleft);
    }
    public void start5(View view){
        Intent intent=new Intent(this,quetta.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slideinright, R.anim.slideoutleft);
    }
    public void start6(View view){
        Intent intent=new Intent(this,multan.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slideinright, R.anim.slideoutleft);
    }


}
