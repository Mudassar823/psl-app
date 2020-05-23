package com.sasteyguru.pslscheduleandlivescores;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PointsActivity extends AppCompatActivity {
RecyclerView recyclerView;
ArrayList<pointsHelper> list=new ArrayList();
String url="http://quickhighlights.com/psl/points.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_points);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle(" Teams Points");
        recyclerView=findViewById(R.id.pntsrec);

        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);

                    for (int i=0;i<jsonArray.length();i++){

                        JSONObject j=jsonArray.getJSONObject(i);
                        pointsHelper helper=new pointsHelper();

                        helper.p_teamname=j.getString("p_teamname");
                        helper.toalplayed=j.getInt("toalplayed");
                        helper.win=j.getInt("win");
                        helper.lost=j.getInt("lost");
                        helper.points=j.getString("points");
                       helper.team_position=j.getInt("team_position");

                        list.add(helper);
                    }
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    pointsAdapter adapter=new pointsAdapter(list);
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
}
