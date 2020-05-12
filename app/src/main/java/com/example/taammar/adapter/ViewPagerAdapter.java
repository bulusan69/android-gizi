package com.example.taammar.adapter;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.taammar.model.MappingGizi;
import com.example.taammar.view.ChartFragment;
import com.example.taammar.view.ProdukFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 2;
    private String[] PAGE_TITLE = new String[] {"Produk","Chart"};
    private Context context;
    private  MappingGizi mappingGizi;
    private final List<Fragment> mFragmentList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm, Context context, MappingGizi mappingGizi) {
        super(fm);
        this.context = context;
        this.mappingGizi = mappingGizi;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return ChartFragment.newInstance(mappingGizi);
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return ProdukFragment.newInstance();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return PAGE_TITLE[position];
    }
}
