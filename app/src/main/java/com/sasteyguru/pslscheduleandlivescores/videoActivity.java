package com.sasteyguru.pslscheduleandlivescores;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class videoActivity extends YouTubeBaseActivity {
YouTubePlayerView youTubePlayerView;
YouTubePlayer.OnInitializedListener onInitializedListener;
RecyclerView recyclerView;
    String vrl="http://quickhighlights.com/psl/videos.php";
    ArrayList<videoHelper> videolist=new ArrayList<>();
     String Url;
     String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        recyclerView=findViewById(R.id.relatedVideos);
        youTubePlayerView=findViewById(R.id.player);
        Bundle bundle=getIntent().getExtras();
        Url=bundle.getString("link");
onInitializedListener=new YouTubePlayer.OnInitializedListener() {
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
youTubePlayer.loadVideo(Url);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }
};
youTubePlayerView.initialize("AIzaSyDRhF2v-k-dy8yw9MoG72GuELxXagpQ55M",onInitializedListener);

RelatedVideos();
    }


    private  void RelatedVideos(){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, vrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for (int i=0;i<jsonArray.length();i++){

                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        videoHelper helper=new videoHelper();
                        helper.v_title=jsonObject.getString("v_title");
                        helper.v_img=jsonObject.getString("v_img");
                        helper.v_link=jsonObject.getString("v_link");
                       url=jsonObject.getString("v_link");
                       if (url.equals(Url)){

                       }
                       else
                            videolist.add(helper);




                    }

                    GridLayoutManager linearLayoutManager = new GridLayoutManager(getApplicationContext(),2);

                    recyclerView.setLayoutManager(linearLayoutManager);
                    videoAdapter adapter = new videoAdapter(videolist,getApplicationContext());
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


}
