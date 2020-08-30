package controllers;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import views.TabOne;
import views.TabThree;
import views.TabTwo;


public class PageAdapter extends FragmentPagerAdapter {

    private String[] tittle = {"TOP STORIES","MOST POPULAR","SCIENCE"};
    private int tabCount;

    PageAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tittle[position];
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new TabOne();
            case 1:
                return new TabTwo();
            case 2:
                return new TabThree();
            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}