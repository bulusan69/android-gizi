package com.example.taammar.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import com.example.taammar.R;
import com.example.taammar.adapter.ViewPagerAdapter;
import com.example.taammar.model.MappingGizi;
import com.google.android.material.tabs.TabLayout;

public class ProdukChartActivity extends AppCompatActivity {

    FragmentPagerAdapter adapterViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produk_chart);
        ViewPager vpPager = (ViewPager) findViewById(R.id.vp_Pager);
        MappingGizi mappingGizi =  (MappingGizi) getIntent().getSerializableExtra("mappinggizi");
        adapterViewPager = new ViewPagerAdapter(getSupportFragmentManager(),ProdukChartActivity.this,mappingGizi);
        vpPager.setAdapter(adapterViewPager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(vpPager);

        //Log.e("cek value",mappingGizi.getVitA());
    }
}
