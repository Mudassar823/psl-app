package com.sasteyguru.pslscheduleandlivescores;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import java.util.ArrayList;


public class TeamsFragment extends Fragment {
    String Url="http://quickhighlights.com/psl/teams.php";
    RecyclerView recyclerView;
ArrayList<TeamsHelper> list=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_teams, container, false);
        recyclerView=view.findViewById(R.id.teamsPslrec);
        Teams();
        connectionCheck();
        return view;
    }

    private void Teams() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        TeamsHelper team=new TeamsHelper();
                        team.team_id=jsonObject.getInt("team_id");
                        team.team_name=jsonObject.getString("team_name");
                        team.team_image=jsonObject.getString("team_image");
                        list.add(team);



                    }

                    recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
                    TeamsInfoAdapter adapter=new TeamsInfoAdapter(list,getActivity());
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

        Volley.newRequestQueue(getContext()).add(stringRequest);
    }
    public void connectionCheck(){

        ConnectivityManager connectivityManager=(ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        if (null!=networkInfo){
            if (networkInfo.getType()==ConnectivityManager.TYPE_WIFI){
            }
            else if (networkInfo.getType()==ConnectivityManager.TYPE_MOBILE){
            }
        }



        else{
            Toast.makeText(getContext(),"No Internet Connection",Toast.LENGTH_LONG).show();

        }





    }

}
