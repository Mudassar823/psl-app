package com.sasteyguru.pslscheduleandlivescores;

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

public class scorecardactivity extends AppCompatActivity {
ArrayList<scorecardHelper> list=new ArrayList();
    String url1="http://quickhighlights.com/psl/summary.php";
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorecardactivity);
        recyclerView=findViewById(R.id.scorerec);
        connectionCheck();
        Bundle bundle=getIntent().getExtras();
        int match_no=bundle.getInt("match_no");
       // Toast.makeText(getApplicationContext(),""+match_no,Toast.LENGTH_LONG).show();
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url1 + "?match_no=" + match_no, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for (int i=0;i<jsonArray.length();i++){


                    JSONObject jsonObject=jsonArray.getJSONObject(i);
                            scorecardHelper helper=new scorecardHelper();
                            helper.team1_img_ining_1=jsonObject.getString("team1_img_ining_1");
                            helper.team2_img_ining_1=jsonObject.getString("team2_img_ining_1");
                            helper.team1_img_ining_2=jsonObject.getString("team1_img_ining_2");
                            helper.team2_img_ining_2=jsonObject.getString("team2_img_ining_2");
                            list.add(helper);

                    }

recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    scorecardAdapter adapter=new scorecardAdapter(list,getApplicationContext());
                    recyclerView.setAdapter(adapter);

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
