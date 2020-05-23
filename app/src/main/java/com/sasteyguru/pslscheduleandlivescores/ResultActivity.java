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

public class ResultActivity extends AppCompatActivity {
RecyclerView recyclerView;
ArrayList<matchResultsHelper> list =new ArrayList<>();
    String url1="http://quickhighlights.com/psl/matches_result.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        recyclerView=findViewById(R.id.matchesResults_rec);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("All Results");
        allResults();
        connectionCheck();


    }

    private void allResults() {

        StringRequest stringRequest=new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for (int i=0;i<jsonArray.length();i++){

                        JSONObject jsonObject=jsonArray.getJSONObject(i);

                        matchResultsHelper helper=new matchResultsHelper();
                        helper.m_detail=jsonObject.getString("m_detail");
                        helper.team1_name=jsonObject.getString("team1_name");
                        helper.team2_name=jsonObject.getString("team2_name");
                        helper.team1_flag=jsonObject.getString("team1_flag");
                        helper.team2_flag=jsonObject.getString("team2_flag");
                        helper.team1total=jsonObject.getString("team1total");
                        //String  total=jsonObject.getString("team2total");
                      //  Toast.makeText(getApplicationContext(),total,Toast.LENGTH_LONG).show();
                        helper.team2total=jsonObject.getString("team2ndtotal");
                        helper.m_conclusion=jsonObject.getString("m_conclusion");
                        helper.match_no=jsonObject.getInt("match_no");

                        list.add(helper);




                    }
                    //recyclerView.setHasFixedSize(true);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                    linearLayoutManager.setReverseLayout(true);
                    linearLayoutManager.setStackFromEnd(true);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    allResultsAdapter adapter=new allResultsAdapter(list,getApplicationContext());
                    recyclerView.setAdapter(adapter);
                    //adapter.notifyDataSetChanged();



                } catch (JSONException e) {
                    e.printStackTrace();
                    //Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();

            }
        });


        Volley.newRequestQueue(this).add(stringRequest);

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

