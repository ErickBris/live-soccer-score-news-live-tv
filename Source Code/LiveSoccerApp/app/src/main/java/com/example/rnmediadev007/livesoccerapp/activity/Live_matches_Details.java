package com.example.rnmediadev007.livesoccerapp.activity;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import com.example.rnmediadev007.livesoccerapp.adapters.PagerAdapter_matchDetails;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.squareup.picasso.Picasso;
import com.ypyproductions.utils.ApplicationUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Live_matches_Details extends AppCompatActivity {
    public static String match_id,home_id,away_id,venue_name,countery;
    public TextView home_team_name, teams_score, away_team_name, counteryName,minutes;
    public TextView date_txt;
    public ImageView img_datapath_away, img_datapath_home;
    public static final String ADMOB_ID_BANNER = "ca-app-pub-xxxxxxxxxxxxxxxx/xxxxxxxxxxx";
    public static final String ADMOB_ID_INTERTESTIAL = "ca-app-pub-xxxxxxxxxxxxxxxx/xxxxxxxxxxx";
    InterstitialAd mInterstitial;
    Toolbar mToolbar;
    ViewPager viewPager;
    RelativeLayout hidden_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_matches__details);
        match_id = getIntent().getExtras().getString("id");
        home_id = getIntent().getExtras().getString("home_team_id");
        away_id = getIntent().getExtras().getString("away_team_id");
        venue_name = getIntent().getExtras().getString("name");
        countery = getIntent().getExtras().getString("countery");

setUpLayoutAdmob1();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setTitle("Match Summery");
        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        hidden_layout = (RelativeLayout) findViewById(R.id.hidden_layout);
        img_datapath_away = (ImageView) findViewById(R.id.away_team_img);
        img_datapath_home = (ImageView) findViewById(R.id.home_team_img);
        home_team_name = (TextView) findViewById(R.id.home_team_name);
        counteryName = (TextView) findViewById(R.id.countryname);
        away_team_name = (TextView) findViewById(R.id.away_team_name);
        teams_score = (TextView) findViewById(R.id.score);
        minutes = (TextView) findViewById(R.id.minutes);
        Typeface face = Typeface.createFromAsset(this.getAssets(), "fonts/big_noodle_titling.ttf");
        teams_score.setTypeface(face);
        minutes.setTypeface(face);
        tabLayout.addTab(tabLayout.newTab().setText("Match Info"));
        tabLayout.addTab(tabLayout.newTab().setText("Line Up"));
        tabLayout.addTab(tabLayout.newTab().setText("Statistics"));
        tabLayout.addTab(tabLayout.newTab().setText("Commentary"));
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter_matchDetails adapter = new PagerAdapter_matchDetails(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0) {
                    mToolbar.setTitle("Match Info");
                } if (tab.getPosition() == 1) {
                    mToolbar.setTitle("Line Up");

                } if (tab.getPosition() == 2) {
                    mToolbar.setTitle("Statistics");

                } if (tab.getPosition() == 3) {
                    mToolbar.setTitle("Commentary");

                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        // Toast.makeText(Live_matches_Details.this, match_id, Toast.LENGTH_SHORT).show();

        makeJsonArrayRequest();
    }

    private void makeJsonArrayRequest() {
        String urlJsonArry = "http://www.phoneappsking.com/soccer/match.php?match=" + match_id;
        String tag_string_req1 = "string_req";
        // Defined Array values to show in ListView
        final ProgressDialog pDialog = new ProgressDialog(Live_matches_Details.this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        StringRequest strReq1 = new StringRequest(Request.Method.GET,
                urlJsonArry, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                hidden_layout.setVisibility(View.VISIBLE);

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray stories = jsonObject.getJSONArray("data");
                    for (int i = 0; i < stories.length(); i++) {
                        JSONObject jsonObject1 = stories.getJSONObject(i);
                        String data_id = jsonObject1.getString("id");
                        String data_home_team_name = jsonObject1.getString("home_team_name");
                        String data_country_name = jsonObject1.getString("country_name");
                        home_team_name.setText(data_home_team_name);
                        counteryName.setText(data_country_name);
                        String data_country_flag = jsonObject1.getString("country_flag");
                        String data_away_team_name = jsonObject1.getString("away_team_name");
                        away_team_name.setText(data_away_team_name);
                        String data_home_score = jsonObject1.getString("home_score");
                        String data_away_score = jsonObject1.getString("away_score");
                        teams_score.setText(data_home_score + " - " + data_away_score);
                        String data_starting_date = jsonObject1.getString("starting_date");
                        String data_home_team_logo = jsonObject1.getString("home_team_logo");
                        if (!data_home_team_logo.isEmpty() && !data_home_team_logo.equals(null)) {
                            Picasso.with(Live_matches_Details.this).load(data_home_team_logo).placeholder(R.drawable.flag_icon).into(img_datapath_home);
                        } else {
                            Picasso.with(Live_matches_Details.this).load(R.drawable.flag_icon).placeholder(R.drawable.flag_icon).into(img_datapath_home);
                        }
                        String data_ht_score = jsonObject1.getString("ht_score");
                        String data_ft_score = jsonObject1.getString("ft_score");
                        String data_home_team_id = jsonObject1.getString("home_team_id");
                        String data_away_team_id = jsonObject1.getString("away_team_id");
                        String data_away_team_logo = jsonObject1.getString("away_team_logo");
                        if (!data_away_team_logo.isEmpty() && !data_away_team_logo.equals(null)) {
                            Picasso.with(Live_matches_Details.this).load(data_away_team_logo).placeholder(R.drawable.flag_icon).into(img_datapath_away);
                        } else {
                            Picasso.with(Live_matches_Details.this).load(R.drawable.flag_icon).placeholder(R.drawable.flag_icon).into(img_datapath_away);

                        }
                        String data_home_score_penalities = jsonObject1.getString("home_score_penalties");
                        String data_away_score_penalties = jsonObject1.getString("away_score_penalties");
                        String data_spectators = jsonObject1.getString("spectators");
                        String data_starting_time = jsonObject1.getString("starting_time");
                        String data_status = jsonObject1.getString("status");
                        String data_minute = jsonObject1.getString("minute");
                        if(data_minute!=null&&!data_minute.isEmpty()){
                        minutes.setText(data_minute+"'");
                        }
                        String data_extra_minute = jsonObject1.getString("extra_minute");
                        String data_compitition_id = jsonObject1.getString("competition_id");
                        String data_competition_name = jsonObject1.getString("competition_name");
                        String data_venue_id = jsonObject1.getString("venue_id");
                        //String data_venue_name = jsonObject1.getString("venue_name");
                        //String data_venue_city = jsonObject1.getString("venue_city");
                        String data_season_id = jsonObject1.getString("season_id");
                        String data_season_name = jsonObject1.getString("season_name");
                        String data_round_id = jsonObject1.getString("stage_id");
                        String data_aggregate = jsonObject1.getString("aggregate");
                        String data_placeholder = jsonObject1.getString("placeholder");
                        String data_country_id = jsonObject1.getString("country_id");
                        //   Toast.makeText(Live_matches.this, data_starting_date+"mdmd", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(Live_matches_Details.this,
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


                //    Toast.makeText(getContext(), message + "income", Toast.LENGTH_LONG).show();

                pDialog.hide();
            }
        }) {

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq1, tag_string_req1);
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

    @Override
    public void onBackPressed() {

        if (getFragmentManager().getBackStackEntryCount() == 0) {
            moveTaskToBack(false);
        } if(getFragmentManager().getBackStackEntryCount() == 1){
            moveTaskToBack(false);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
