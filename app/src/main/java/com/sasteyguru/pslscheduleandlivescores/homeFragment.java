package com.sasteyguru.pslscheduleandlivescores;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class homeFragment extends Fragment {
    private AdView mAdView;
    Button button;
    InterstitialAd mInterstitialAd;
    TextView textView;
    TextView matchesHeading,team1Name,team2Name,matchResult,NoInternetTxt,NoInternetTxt1,totalforTeam1,totalforTeam2;
    ImageView team1Image,team2Image;
    RecyclerView recyclerView,recyclerView1,recyclerViewvideo,winnerRec;
    ArrayList<newsHelper> newsList=new ArrayList<>();
    ArrayList<videoHelper> videolist=new ArrayList<>();
    ArrayList<fixturesHelper> fixlist=new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ViewPager viewPager;
    String url="http://quickhighlights.com/psl/slider.php";
    String url1="http://quickhighlights.com/psl/matches_result.php";
    String url2="http://quickhighlights.com/psl/news_portion.php";
    String url3="http://quickhighlights.com/psl/fixtures.php";
    String vrl="http://quickhighlights.com/psl/videos.php";
    ArrayList<sliderHelper> list=new ArrayList<>();
    CardView allResultsShow,readMore,gotoframe,pointtable,viewmore;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home, container, false);


        MobileAds.initialize(getContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        button=view.findViewById(R.id.all_fixtures);
        readMore=view.findViewById(R.id.readmore);
        viewPager = view.findViewById(R.id.pager);
        viewmore=view.findViewById(R.id.viewmore);
        //textView=view.findViewById(R.id.name);
        gotoframe=view.findViewById(R.id.framehome);
        pointtable=view.findViewById(R.id.homepoint);
        recyclerView=view.findViewById(R.id.newsrec);
        recyclerViewvideo=view.findViewById(R.id.videorec);
        recyclerView1=view.findViewById(R.id.fixrec);
        totalforTeam1=view.findViewById(R.id.totalteam1);
        totalforTeam2=view.findViewById(R.id.totalteam2);
        NoInternetTxt=view.findViewById(R.id.nointernet);
        NoInternetTxt1=view.findViewById(R.id.nointernet);
        matchesHeading=view.findViewById(R.id.matches_title);
        team1Name=view.findViewById(R.id.team1_name);
        team2Name=view.findViewById(R.id.team2_name);
        matchResult=view.findViewById(R.id.result);
        team1Image=view.findViewById(R.id.team1);
        team2Image=view.findViewById(R.id.team2);
        allResultsShow=view.findViewById(R.id.all_results);
        winnerRec=view.findViewById(R.id.winner_rec);


        slider();
        slide();
        matchesResult();
        winnerInfo();
        Fixtures();
        latestNews();
        LatestVideo();
        connectionCheck();
      //  prepareAd();
        viewmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),AllvideosActivity.class);
                startActivity(intent);
            }
        });
        pointtable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),PointsActivity.class);
                startActivity(intent);
            }
        });
        allResultsShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),ResultActivity.class);
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),allFixturesActivity.class);
                startActivity(intent);
            }
        });
        readMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),readAllnewsActivity.class);
                startActivity(intent);
            }
        });
        gotoframe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),Main2Activity.class);
                startActivity(intent);
            }
        });
        return view;
    }
    private void slider() {



        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for (int i=0;i<jsonArray.length();i++){

                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        sliderHelper sliderHelper=new sliderHelper();
                        sliderHelper.slider_image=jsonObject.getString("slider_image");
                        sliderHelper.slider_head=jsonObject.getString("slider_head");
                        sliderHelper.news_des=jsonObject.getString("news_des");
                        sliderHelper.news_date=jsonObject.getString("news_date");
                        sliderHelper.sub_title=jsonObject.getString("sub_title");
                        //   String pic=jsonObject.getString("image_link");
                        // Toast.makeText(getContext(),pic,Toast.LENGTH_LONG).show();
                        list.add(sliderHelper);



                    }
                    viewPagerAdapter viewPagerAdapter=new viewPagerAdapter(getContext(),list);
                    viewPager.setAdapter(viewPagerAdapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                   // Toast.makeText(getContext(),"error"+e,Toast.LENGTH_LONG).show();
                }

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getContext(),"error"+error,Toast.LENGTH_LONG).show();

            }
        });
        Volley.newRequestQueue(getActivity()).add(stringRequest);





    }
    public  void  slide() {


        final Handler handler=new Handler();
        final Runnable runnable=new Runnable() {
            @Override
            public void run() {
                if (viewPager.getCurrentItem() < list.size() - 1) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                } else {
                    viewPager.setCurrentItem(0);
                }
            }
        };
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);

            }
        },5000,5000);
    }


    private void matchesResult() {

        StringRequest stringRequest=new StringRequest(Request.Method.POST, url1, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray=new JSONArray(response);
                    if (jsonArray.length()==0){
                        matchesHeading.setText("No result found");
                        // Toast.makeText(getContext(),  matchesHeading.toString(),Toast.LENGTH_LONG).show();
                        team1Name.setVisibility(View.GONE);
                        allResultsShow.setVisibility(View.GONE);
                        team2Name.setVisibility(View.GONE);
                        team1Image.setVisibility(View.GONE);
                        team2Image.setVisibility(View.GONE);
                        matchResult.setText("As the matches start we will update the information ");


                    }


                    else{
                        for (int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject=jsonArray.getJSONObject(i);
                            String team1total=jsonObject.getString("team1total");
                            String team2total=jsonObject.getString("team2ndtotal");
                            String m_detail=jsonObject.getString("m_detail");
                            String team1_name=jsonObject.getString("team1_name");
                            String 	team2_name=jsonObject.getString("team2_name");
                            String team1_flag=jsonObject.getString("team1_flag");
                            String team2_flag=jsonObject.getString("team2_flag");
                            String m_conclusion=jsonObject.getString("m_conclusion");
                            matchesHeading.setText(m_detail);
                            team1Name.setText(team1_name);
                            team2Name.setText(team2_name);
                            Picasso.get().load(team1_flag).into(team1Image);
                            Picasso.get().load(team2_flag).into(team2Image);
                            matchResult.setText(m_conclusion);
                            totalforTeam1.setText(team1total);
                            totalforTeam2.setText(team2total);





                        }}


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
    private void winnerInfo(){


        ArrayList<winnerHelper> exampleList = new ArrayList<>();
        exampleList.add(new winnerHelper("2019","Quetta Gladiators","Peshawar Zalmi"));
        exampleList.add(new winnerHelper("2018","Islamabad United","Peshawar Zalmi"));
        exampleList.add(new winnerHelper("2017","Peshawar Zalmi","Quetta Gladiators"));
        exampleList.add(new winnerHelper("2016","Islamabad United","Quetta Gladiators"));




        winnerRec.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new winnerAdapter(exampleList);

        winnerRec.setLayoutManager(mLayoutManager);
        winnerRec.setAdapter(mAdapter);
    }
    private void Fixtures() {
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

                        String type=jsonObject.getString("type");
                        fix.type=jsonObject.getString("type");
                        if (type.equals("latest")){

                            fixlist.add(fix);
                        } }

                    recyclerView1.setLayoutManager(new GridLayoutManager(getContext(),1));
                    FixtureAdapter adapter = new FixtureAdapter(fixlist,getContext());
                    recyclerView1.setAdapter(adapter);
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
        Volley.newRequestQueue(getActivity()).add(stringRequest);
    }
    private void latestNews() {
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
                        if (type.equals("latest")){

                            newsList.add(newsHelper);
                        } }

                    recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
                    newsAdapter adapter = new newsAdapter(newsList,getContext());
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
        Volley.newRequestQueue(getActivity()).add(stringRequest);
    }
    private void LatestVideo() {
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
                        String type=jsonObject.getString("type");
                        if (type.equals("latest")){

                            videolist.add(helper);
                        } }

                    recyclerViewvideo.setLayoutManager(new GridLayoutManager(getContext(),2));
                    videoAdapter adapter = new videoAdapter(videolist,getActivity());
                    recyclerViewvideo.setAdapter(adapter);


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


            NoInternetTxt.setVisibility(View.VISIBLE);
            NoInternetTxt1.setVisibility(View.VISIBLE);
            matchesHeading.setText("Connect to Internet");
            team1Name.setVisibility(View.GONE);
            team2Name.setVisibility(View.GONE);
            matchResult.setVisibility(View.GONE);
        }





    }



}