package com.example.rnmediadev007.livesoccerapp.activity;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.rnmediadev007.livesoccerapp.R;
import com.ypyproductions.dialog.utils.AlertDialogUtils;
import com.ypyproductions.task.IDBCallback;
import com.ypyproductions.utils.ApplicationUtils;

public class Splash extends AppCompatActivity  {
    public static final String TAG = Splash.class.getSimpleName();

    private ProgressBar mProgressBar;
    private boolean isPressBack;

    private Handler mHandler = new Handler();
    private TextView mTvCopyright;

    private TextView mTvVersion;
    private boolean isLoading;
    private TextView mTvAppName;
    private boolean isStartAnimation;
    private ImageView mImgLogo;
    protected boolean isShowingDialog;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setContentView(R.layout.activity_splash);
        this.mProgressBar = (ProgressBar) findViewById(R.id.progressBar1);
        mImgLogo = (ImageView) findViewById(R.id.img_logo);


        mProgressBar.setVisibility(View.INVISIBLE);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!ApplicationUtils.isOnline(this)){
            if(!isShowingDialog){
                isShowingDialog=true;
                showDialogTurnOnInternetConnection();
            }
        }
        else{
            if (!isLoading) {
                isLoading = true;

                startAnimationLogo(new IDBCallback() {
                    @Override
                    public void onAction() {
                        mProgressBar.setVisibility(View.VISIBLE);


                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mProgressBar.setVisibility(View.INVISIBLE);
                                Intent mIntent = new Intent(Splash.this, MainActivity.class);
                                startActivity(mIntent);
                                finish();
                            }
                        }, 3000);
                    }
                });
            }
        }

    }

    private void startAnimationLogo(final IDBCallback mCallback) {
        if (!isStartAnimation) {
            isStartAnimation = true;
            mProgressBar.setVisibility(View.INVISIBLE);

          /*  animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
            mImgLogo.setAnimation(animation);*/

            if (mCallback != null) {
                mCallback.onAction();

            } else {
                if (mCallback != null) {
                    mCallback.onAction();
                }
            }
        }}

    private void showDialogTurnOnInternetConnection() {
        Dialog mDialog = AlertDialogUtils.createFullDialog(this, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT, R.string.title_warning, R.string.title_settings, R.string.title_cancel,
                R.string.info_lose_internet, new AlertDialogUtils.IOnDialogListener() {
                    @Override
                    public void onClickButtonPositive() {
                        isShowingDialog = false;
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    }

                    @Override
                    public void onClickButtonNegative() {
                        isShowingDialog = false;
                        finish();
                    }
                });
        mDialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isPressBack) {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}

