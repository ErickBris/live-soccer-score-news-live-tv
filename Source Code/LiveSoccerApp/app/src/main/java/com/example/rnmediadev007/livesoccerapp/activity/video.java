package com.example.rnmediadev007.livesoccerapp.activity;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.rnmediadev007.livesoccerapp.App.AppController;
import com.example.rnmediadev007.livesoccerapp.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.ypyproductions.utils.ApplicationUtils;

import org.json.JSONException;
import org.json.JSONObject;

public class video extends AppCompatActivity {
    ProgressDialog pDialog,pDialog1;
    VideoView videoview;
    String video_url,vedio_key,url;
    private String Live_streamming,Live_key;
    public static final String ADMOB_ID_BANNER = "ca-app-pub-xxxxxxxxxxxxxxxx/xxxxxxxxxxx";
    public static final String ADMOB_ID_INTERTESTIAL = "ca-app-pub-xxxxxxxxxxxxxxxx/xxxxxxxxxxx";
    InterstitialAd mInterstitial;
    public String urlJsonArry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //   this.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_video);

        videoview = (VideoView) findViewById(R.id.VideoView);
        // Execute StreamVideo AsyncTask
        Live_streamming = getIntent().getExtras().getString("live_url");

        vedio_key = getIntent().getExtras().getString("key");
        if (!vedio_key.equals(null) && !vedio_key.isEmpty()&&vedio_key.equals("vedio_tag")) {
            video_url = getIntent().getExtras().getString("vediourl");
            makeJsonArrayRequest();


        }
        if (!vedio_key.equals(null) &&!vedio_key.isEmpty() && vedio_key.equals("live")) {

            live_Streaming(Live_streamming);


        }



setUpLayoutAdmob1();
    }


    public void live_Streaming(String url){

        pDialog1 = new ProgressDialog(video.this);
        // Set progressbar title
        pDialog1.setTitle("Soccer Video Streaming");
        // Set progressbar message
        pDialog1.setMessage("Buffering...");
        pDialog1.setIndeterminate(false);
        pDialog1.setCancelable(false);
        // Show progressbar j
        pDialog1.show();
        try {

            // Start the MediaController
            MediaController mediacontroller = new MediaController(
                    video.this);
            mediacontroller.setAnchorView(videoview);
            // Get the URL from String VideoURL

            Uri video = Uri.parse(url);
            videoview.setMediaController(mediacontroller);
            videoview.setVideoURI(video);


        }
        catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();

        }



        videoview.requestFocus();
        videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            // Close the progress bar and play the video
            public void onPrepared(MediaPlayer mp) {

                pDialog1.dismiss();
                videoview.start();
            }
        });


    }

    private void makeJsonArrayRequest() {
        urlJsonArry = "http://www.dummydomain.com/android_videos.php?countrySettings=&videoId="+video_url;

        String tag_string_req1 = "string_req";
        // Defined Array values to show in ListView
        // Create a progressbar
        pDialog = new ProgressDialog(video.this);
        // Set progressbar title
        pDialog.setTitle("Soccer Video Streaming");
        // Set progressbar message
        pDialog.setMessage("Buffering...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        // Show progressbar j
        pDialog.show();
        StringRequest strReq1 = new StringRequest(Request.Method.GET,
                urlJsonArry, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {
                    String tag_string_req1 = "string_req";


                    JSONObject jsonObject = new JSONObject(response);
                    url = jsonObject.getString("url");


                    try {
                        // Start the MediaController
                        MediaController mediacontroller = new MediaController(
                                video.this);
                        mediacontroller.setAnchorView(videoview);
                        // Get the URL from String VideoURL
                        Uri video = Uri.parse(url);
                        videoview.setMediaController(mediacontroller);
                        videoview.setVideoURI(video);

                    } catch (Exception e) {
                        Log.e("Error", e.getMessage());
                        e.printStackTrace();
                    }


                    videoview.requestFocus();
                    videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        // Close the progress bar and play the video
                        public void onPrepared(MediaPlayer mp) {
                            pDialog.dismiss();
                            videoview.start();
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(video.this,
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }

                pDialog.hide();

            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                String message = null;
                if (error instanceof NetworkError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                } else if (error instanceof ServerError) {
                    message = "The server could not be found. Please try again after some time!!";
                } else if (error instanceof AuthFailureError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                } else if (error instanceof ParseError) {
                    message = "Parsing error! Please try again after some time!!";
                } else if (error instanceof NoConnectionError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                } else if (error instanceof TimeoutError) {
                    message = "Connection TimeOut! Please check your internet connection.";
                }


//                Toast.makeText(getContext(), message + "income", Toast.LENGTH_LONG).show();

                pDialog.hide();
            }
        }) {

        };

        // Adding request to request queue
        AppController.getInstance().

                addToRequestQueue(strReq1, tag_string_req1);
    }

    private void setUpLayoutAdmob1() {

        if (ApplicationUtils.isOnline(this)) {
            boolean b = true;
            if (b) {
                mInterstitial = new InterstitialAd(this);
                mInterstitial.setAdUnitId(ADMOB_ID_INTERTESTIAL);
                mInterstitial.loadAd(new AdRequest.Builder().build());

                mInterstitial.setAdListener(new AdListener() {
                    @Override
                    public void onAdLoaded() {
                        // TODO Auto-generated method stub
                        super.onAdLoaded();
                        if (mInterstitial.isLoaded()) {
                            mInterstitial.show();
                        }
                    }
                });

            }
        }
    }


}