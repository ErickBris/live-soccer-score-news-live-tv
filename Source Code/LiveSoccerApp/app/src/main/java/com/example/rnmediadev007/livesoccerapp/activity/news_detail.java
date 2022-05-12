package com.example.rnmediadev007.livesoccerapp.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rnmediadev007.livesoccerapp.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.squareup.picasso.Picasso;
import com.ypyproductions.utils.ApplicationUtils;

public class news_detail extends AppCompatActivity {


    String news_detail_img,news_detail_heading,news_deatil_desc;
    Toolbar mToolbar;
    public static final String ADMOB_ID_BANNER = "ca-app-pub-xxxxxxxxxxxxxxxx/xxxxxxxxxxx";
    public static final String ADMOB_ID_INTERTESTIAL = "ca-app-pub-xxxxxxxxxxxxxxxx/xxxxxxxxxxx";
    InterstitialAd mInterstitial;
    ImageView top_img;
    TextView heading,desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        news_detail_img = getIntent().getExtras().getString("img");
        news_detail_heading = getIntent().getExtras().getString("heading");
        news_deatil_desc = getIntent().getExtras().getString("desc");
       // Toast.makeText(news_detail.this, news_detail_heading+news_deatil_desc, Toast.LENGTH_SHORT).show();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
setUpLayoutAdmob1();
        top_img = (ImageView)findViewById(R.id.top_newsdeatil_img);
        heading = (TextView)findViewById(R.id.heading);
        desc = (TextView)findViewById(R.id.desc_news);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setTitle("News Details");

if(!news_detail_img.isEmpty()) {
    Picasso.with(this).load(news_detail_img).into(top_img);
}else{

}
        heading.setText(news_detail_heading);
        desc.setText(news_deatil_desc);



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

        if(getFragmentManager().getBackStackEntryCount() == 3) {
            moveTaskToBack(false);
        }
        else {
            super.onBackPressed();
        }
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
