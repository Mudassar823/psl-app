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
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class allFixturesActivity extends AppCompatActivity  {
    RecyclerView recyclerView;
    InterstitialAd mInterstitialAd;
    ArrayList<fixturesHelper> list=new ArrayList<>();
    String url3="http://quickhighlights.com/psl/fixtures.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_fixtures);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("All Fixtures");
        recyclerView=findViewById(R.id.allfix_rec);
        showaAllfixtures();
        connectionCheck();
        prepareAd();
    }

    private void showaAllfixtures() {

        StringRequest stringRequest=new StringRequest(Request.Method.POST, url3, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        fixturesHelper fix=new fixturesHelper();
                        fix.f_head=jsonObject.getString("f_head");
                        fix.f_teamimg1=jsonObject.getString("f_teamimg1");
                        fix.f_teamimg2=jsonObject.getString("f_teamimg2");
                        fix.f_team1name=jsonObject.getString("f_team1name");
                        fix.f_team2name=jsonObject.getString("f_team2name");
                        fix.fdatetime=jsonObject.getString("date");
                        fix.city=jsonObject.getString("city");
                        fix.time=jsonObject.getString("time");


                            list.add(fix);
                        }

                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    allFixtureAdapter adapter = new allFixtureAdapter(list,getApplicationContext());
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
    private void prepareAd(){
        mInterstitialAd=new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.id_for_interstitial));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }
}