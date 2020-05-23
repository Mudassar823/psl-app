package com.sasteyguru.pslscheduleandlivescores;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AboutActivity extends AppCompatActivity {
RecyclerView recyclerView;
ImageView imageView;
TextView textView;
    private RecyclerView.Adapter mAdapter;
    String url="http://quickhighlights.com/psl/about.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("About PSl");
        connectionCheck();
        imageView=findViewById(R.id.aboutimg);
        textView=findViewById(R.id.aboutdes);
        recyclerView=findViewById(R.id.resultsrec);
        ArrayList<winnerHelper> exampleList = new ArrayList<>();
        exampleList.add(new winnerHelper("2019","Quetta Gladiators","Peshawar Zalmi"));
        exampleList.add(new winnerHelper("2018","Islamabad United","Peshawar Zalmi"));
        exampleList.add(new winnerHelper("2017","Peshawar Zalmi","Quetta Gladiators"));
        exampleList.add(new winnerHelper("2016","Islamabad United","Quetta Gladiators"));




        recyclerView.setHasFixedSize(true);
      LinearLayoutManager  mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mAdapter = new winnerAdapter(exampleList);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

    info();
    }

    private void info() {


        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);

                    for (int i=0;i<jsonArray.length();i++){


                        JSONObject jsonObject=jsonArray.getJSONObject(i);


                        String image=jsonObject.getString("about_img");
                        String des=jsonObject.getString("about_des");

                        textView.setText(des);
                        Picasso.get().load(image).into(imageView);






                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);
















    }
    public void connectionCheck(){

        ConnectivityManager connectivityManager=(ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        if (null!=networkInfo){
            if (networkInfo.getType()==ConnectivityManager.TYPE_WIFI){
            }
            else if (networkInfo.getType()==ConnectivityManager.TYPE_MOBILE){
            }
        }



        else{
            Toast.makeText(getApplicationContext(),"No Internet Connection",Toast.LENGTH_LONG).show();

        }





    }
}
