package com.sasteyguru.pslscheduleandlivescores;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class livescoreActivity extends AppCompatActivity {
    private WebView webView;
    private ProgressBar progressBar;
    String link;
    String url="http://quickhighlights.com/psl/liveScore.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livescore);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Live Scores");

        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for (int i=0;i<jsonArray.length();i++) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        link = jsonObject.getString("web_link");
                        webView = (WebView) findViewById(R.id.webview1);
                        progressBar=findViewById(R.id.pbar1);

                        webView.setVisibility(View.INVISIBLE);
                        WebSettings webSettings = webView.getSettings();
                        webSettings.setJavaScriptEnabled(true);
                        webView.setWebChromeClient(new WebChromeClient());
                        webView.setWebViewClient(new WebViewClient(){
                            @Override
                            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                                super.onPageStarted(view, url, favicon);
                            }

                            @Override
                            public void onPageFinished(WebView view, String url) {
                                super.onPageFinished(view, url);
                                progressBar.setVisibility(View.GONE);
                                webView.setVisibility(View.VISIBLE);
                            }
                        });
                        webView.loadUrl(link);

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
        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);






    }
    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {

            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
