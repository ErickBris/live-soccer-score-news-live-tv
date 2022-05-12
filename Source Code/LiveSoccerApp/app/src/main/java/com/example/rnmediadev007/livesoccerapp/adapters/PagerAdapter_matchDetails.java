package com.example.rnmediadev007.livesoccerapp.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.rnmediadev007.livesoccerapp.fragments.Commentary;
import com.example.rnmediadev007.livesoccerapp.fragments.LineUp;
import com.example.rnmediadev007.livesoccerapp.fragments.MatchInfo;
import com.example.rnmediadev007.livesoccerapp.fragments.statistics;

public class PagerAdapter_matchDetails extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter_matchDetails(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                MatchInfo tab1 = new MatchInfo();
                return tab1;
            case 1:
                LineUp tab2 = new LineUp();
                return tab2;
            case 2:
                statistics tab3 = new statistics();
                return tab3;
            case 3:
                Commentary tab4 = new Commentary();
                return tab4;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}