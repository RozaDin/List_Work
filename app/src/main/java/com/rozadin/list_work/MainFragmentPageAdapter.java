package com.rozadin.list_work;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MainFragmentPageAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 2;
    private Context context;

    public MainFragmentPageAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0)
            return PageFragment.newInstance(position + 1);
        else
            return PageFragment.newInstance(position + 1);
        //TODO переделать на фрагмент дней недели
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return this.context.getResources().getStringArray(R.array.table_page_name)[position];
    }
}
