package controllers;



import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import views.TabOne;
import views.TabThree;
import views.TabTwo;


public class PageAdapter extends FragmentPagerAdapter {

    String tittle [] = {"TOP STORIES","MOST POPULAR","SEARCH"};
    int tabCount;

    public PageAdapter( FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tittle[position];
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                TabOne tab1 = new TabOne();
                return tab1;
            case 1:
                TabTwo tab2 = new TabTwo();
                return tab2;
            case 2:
                TabThree tab3 = new TabThree();
                return tab3;
            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}