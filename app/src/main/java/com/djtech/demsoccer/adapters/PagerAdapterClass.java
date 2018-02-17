package com.djtech.demsoccer.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.djtech.demsoccer.fragments.CompetitionFragment;
import com.djtech.demsoccer.fragments.FixturesFragment;
import com.djtech.demsoccer.fragments.NewsFragment;
import com.djtech.demsoccer.fragments.TeamInfoFragment;

/**
 * Created by ILENWABOR DAVID on 17/02/2018.
 */

public class PagerAdapterClass extends FragmentStatePagerAdapter {
    private int numberOfTabs;
    private FragmentManager fm;
    private NewsFragment newsFrag;
    private CompetitionFragment compFrag;
    private TeamInfoFragment teamFrag;
    private FixturesFragment fixFrag;

    public PagerAdapterClass(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
        this.fm = fm;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new NewsFragment();
            case 1:
                return new CompetitionFragment();
            case 2:
                return new FixturesFragment();
            case 3:
                return new TeamInfoFragment();

        }
        return new NewsFragment();
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
