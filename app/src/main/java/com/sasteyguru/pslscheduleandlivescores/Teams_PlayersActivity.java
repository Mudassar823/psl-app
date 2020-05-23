package com.sasteyguru.pslscheduleandlivescores;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Teams_PlayersActivity extends AppCompatActivity {
RecyclerView recyclerView;
    String url="http://quickhighlights.com/psl/teamsPlayers.php";
    ArrayList<TeamsplayerHelper> list=new ArrayList<>();
    InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams__players);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Team Players");
        prepareAd();
        recyclerView=findViewById(R.id.teamsplayers);
        Bundle bundle= getIntent().getExtras();
        int Team_id=bundle.getInt("team_id");
        //Toast.makeText(getApplicationContext(),"jj"+Team_id,Toast.LENGTH_LONG).show();

        StringRequest stringRequest=new StringRequest(Request.Method.GET, url+"?team_id="+Team_id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for (int i=0;i<jsonArray.length();i++){

                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        TeamsplayerHelper helper=new TeamsplayerHelper();
                        helper.player_id=jsonObject.getInt("player_id");
                        helper.player_name=jsonObject.getString("player_name");
                        helper.player_format=jsonObject.getString("player_format");
                    //    helper.player_country=jsonObject.getString("player_country");
                        helper.player_des=jsonObject.getString("player_des");
                        helper.team_id=jsonObject.getInt("main_id");
                        helper.playerimg=jsonObject.getString("player_img");
                        list.add(helper);


                    }


recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
TeamsPlayersAdapter adapter=new TeamsPlayersAdapter(list);
recyclerView.setAdapter(adapter);





                } catch (JSONException e) {
                    e.printStackTrace();
                  //  Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               // Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
            }
        });
        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);
    }
    private void prepareAd(){
        mInterstitialAd=new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.id_for_interstitial));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }
    @Override
    public void onBackPressed() {
        if (mInterstitialAd.isLoaded()){
            mInterstitialAd.show();
            mInterstitialAd.setAdListener(new AdListener(){
                @Override
                public void onAdClosed() {
                    super.onAdClosed();
                    finish();
                }
            });
        }
        else{
            super.onBackPressed();}
    }
}
