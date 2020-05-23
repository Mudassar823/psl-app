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


public class VenuesFragment extends Fragment {
    String Url="http://quickhighlights.com/psl/Venues.php";
    RecyclerView recyclerView;
    ArrayList<venuesHelper> list=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_venues, container, false);


        recyclerView=view.findViewById(R.id.venuerec);
        StadiumInfo();
        connectionCheck();
        return view;
    }
private void StadiumInfo(){
    StringRequest stringRequest=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            try {
                JSONArray jsonArray=new JSONArray(response);
                for (int i=0;i<jsonArray.length();i++){

                    JSONObject jsonObject=jsonArray.getJSONObject(i);

                    venuesHelper p=new venuesHelper();
                    p.city=jsonObject.getString("city_name");
                    p.stname=jsonObject.getString("st_name");
                    p.image=jsonObject.getString("image");
                    p.capacity=jsonObject.getString("capacity");
                    p.matches=jsonObject.getInt("matches");
                    list.add(p);
                }
                recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
                AdapterForVenues adapter = new AdapterForVenues(list, getActivity());
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
               // Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();

            }

        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
          //  Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();

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
