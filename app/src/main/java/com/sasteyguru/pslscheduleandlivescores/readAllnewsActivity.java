package com.sasteyguru.pslscheduleandlivescores;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class readAllnewsActivity extends AppCompatActivity {
    ArrayList<newsHelper> list=new ArrayList<>();
    RecyclerView recyclerView;
    String url2="http://quickhighlights.com/psl/news_portion.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_allnews);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("All News");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView=findViewById(R.id.readallnewsrec);

        readAllnewsData();
connectionCheck();


    }
    private void readAllnewsData(){




        StringRequest stringRequest=new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for (int i=0;i<jsonArray.length();i++){

                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        newsHelper newsHelper=new newsHelper();
                        newsHelper.news_image=jsonObject.getString("news_image");
                        newsHelper.news_head=jsonObject.getString("news_head");
                        newsHelper.news_desc=jsonObject.getString("news_desc");
                        newsHelper.news_date=jsonObject.getString("news_date");
                        newsHelper.news_subtitle=jsonObject.getString("news_subtitle");
                        String type=jsonObject.getString("news_type");
                        newsHelper.news_type=jsonObject.getString("news_type");


                            list.add(newsHelper);
                        }

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                    linearLayoutManager.setReverseLayout(true);
                    linearLayoutManager.setStackFromEnd(true);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    allnewsReadadapter adapter = new allnewsReadadapter(list,getApplicationContext());
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

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
