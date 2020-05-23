package com.sasteyguru.pslscheduleandlivescores;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class LiveScoreFragment extends Fragment {
   CardView high,live;
    String url="http://quickhighlights.com/psl/liveScore.php";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_live_score, container, false);

high=view.findViewById(R.id.hi);
high.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(getContext(),higlightsActivity.class);
        startActivity(intent);

    }
});

live=view.findViewById(R.id.liv);





live.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for (int i=0;i<jsonArray.length();i++){

                        JSONObject jsonObject=jsonArray.getJSONObject(i);

                        int value=jsonObject.getInt("m_value");

                        if (value==1){

                            Intent intent=new Intent(getContext(),livescoreActivity.class);
                            startActivity(intent);
                        }
                    else{
                            Toast.makeText(getActivity(),"Wait for Live matches",Toast.LENGTH_LONG).show();
                        }

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
        Volley.newRequestQueue(getActivity()).add(stringRequest);

    }
});

        return view;
    }
}


