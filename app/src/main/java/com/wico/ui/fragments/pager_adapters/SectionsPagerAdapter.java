package com.wico.ui.fragments.pager_adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.wico.ui.fragments.ChildrenPagesFragment;
import com.wico.ui.fragments.PageContentViewFragment;
import com.wico.ui.fragments.QuestionListFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private String pagePath;


    public SectionsPagerAdapter(FragmentManager fm, String pagePath) {
        super(fm);
        this.pagePath = pagePath;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return PageContentViewFragment.newInstance(pagePath);
            case 1: return ChildrenPagesFragment.newInstance();
            case 2: return QuestionListFragment.newInstance(pagePath);
            default: return null;
        }
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Page";
            case 1:
                return "Children";
            case 2:
                return "Questions";
        }
        return null;
    }
}
