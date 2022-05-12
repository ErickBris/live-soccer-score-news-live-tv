package com.example.rnmediadev007.livesoccerapp.activity;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.rnmediadev007.livesoccerapp.adapters.PagerAdapter;

import com.example.rnmediadev007.livesoccerapp.R;
import com.example.rnmediadev007.livesoccerapp.fragments.FragmentDrawer;
import com.example.rnmediadev007.livesoccerapp.fragments.Live_matches_Fragment;
import com.example.rnmediadev007.livesoccerapp.fragments.News;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.ypyproductions.utils.ApplicationUtils;
import com.onesignal.OneSignal;

public class MainActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {
    public ViewPager viewPager;
    private static String TAG = MainActivity.class.getSimpleName();
    FloatingActionButton foting;
    private Toolbar mToolbar;
    private ActionBar actionBar;
    FrameLayout ly;
    ImageView draw_image;
    private FragmentDrawer drawerFragment;
    Runnable refresh;
    Live_matches_Fragment live_matches_fragment;
    AdView adView;
    News news;
    public static final String ADMOB_ID_BANNER = "ca-app-pub-xxxxxxxxxxxxxxxx/xxxxxxxxxxx";
    public static final String ADMOB_ID_INTERTESTIAL = "ca-app-pub-xxxxxxxxxxxxxxxx/xxxxxxxxxxx";
    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		OneSignal.startInit(this).init();
        setContentView(R.layout.activity_main);
        setUpLayoutAdmob();
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        ly = (FrameLayout) findViewById(R.id.container_body);
        ly.setVisibility(View.GONE);
        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Live Score"));
        tabLayout.addTab(tabLayout.newTab().setText("Live Tv"));
        tabLayout.addTab(tabLayout.newTab().setText("Recent"));
        tabLayout.addTab(tabLayout.newTab().setText("news"));
        getSupportActionBar().setElevation(0);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                viewPager.setVisibility(View.VISIBLE);
                ly.setVisibility(View.GONE);
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0) {

                    mToolbar.setTitle("Live Score");
                }
                if (tab.getPosition() == 1) {
                    mToolbar.setTitle("Live Tv");

                }
                if (tab.getPosition() == 2) {

                    mToolbar.setTitle("Recent");

                }
                if (tab.getPosition() == 3) {
                    mToolbar.setTitle("News");

                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        drawerFragment = (FragmentDrawer) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);
        displayView(0);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
        ly.setVisibility(View.GONE);
       // viewPager.setCurrentItem(position);
        viewPager.setVisibility(View.VISIBLE);

    }

    private void displayView(int position) {
        switch (position) {
            case 0:
                viewPager.setCurrentItem(0);
                break;
            case 1:

                viewPager.setCurrentItem(1);
                break;
            case 2:

                viewPager.setCurrentItem(2);
                break;
            case 3:

                viewPager.setCurrentItem(3);
                break;
            case 4:
                rateme();
                viewPager.setCurrentItem(0);
                break;
            case 5:
                share();
                viewPager.setCurrentItem(0);
                break;
            default:
                break;
        }

    }


    public void rateme() {

        Intent intent = new Intent(android.content.Intent.ACTION_VIEW);

        //Copy App URL from Google Play Store.
        intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=%1$s"));

        startActivity(intent);

        Log.i("Code2care ", "Cancel button Clicked!");
    }


    public void share() {

        String message = "https://play.google.com/store/apps/details?id=%1$s";
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(Intent.createChooser(share, "Share the link of Live Cricket"));
    }

    @Override
    public void onBackPressed() {
        onexit();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_Disclaimer) {
            showDdisclaimerdialog();

            return true;
        }
        if (id == R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUpLayoutAdmob() {
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.layout_ad);
        if (ApplicationUtils.isOnline(this)) {
            boolean b = true;
            if (b) {
                adView = new AdView(this);
                adView.setAdUnitId(ADMOB_ID_BANNER);
                adView.setAdSize(AdSize.SMART_BANNER);

                layout.addView(adView);
                AdRequest mAdRequest = new AdRequest.Builder().build();
                adView.loadAd(mAdRequest);


            } else {
                layout.setVisibility(View.GONE);
            }
        } else {
            layout.setVisibility(View.GONE);
        }
    }

    public void onexit() {
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(this, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
        localBuilder.setTitle("Confirmation");
        localBuilder
                .setMessage(getString(R.string.rating)).setNeutralButton("Rate",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(
                            DialogInterface paramAnonymousDialogInterface,
                            int paramAnonymousInt) {
                        MainActivity.this.ratee(MainActivity.this
                                .getApplicationContext()
                                .getPackageName());
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(
                            DialogInterface paramAnonymousDialogInterface,
                            int paramAnonymousInt) {

                        paramAnonymousDialogInterface.dismiss();
                        MainActivity.this.finish();

                    }
                });
        localBuilder.show();
    }

    public void ratee(String paramString) {
        try {
            Intent localIntent = new Intent("android.intent.action.VIEW");
            localIntent
                    .setData(Uri.parse("market://details?id=" + paramString));
            startActivity(localIntent);
            return;
        } catch (Exception localException) {

        }
    }

    private void showDdisclaimerdialog() {


        final AlertDialog alertDialog = new AlertDialog.Builder(this, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT)
                .setMessage(R.string.disclaimer)
                .setTitle("Disclaimer")
                .setNegativeButton("close", null)
                .show();


    }
}