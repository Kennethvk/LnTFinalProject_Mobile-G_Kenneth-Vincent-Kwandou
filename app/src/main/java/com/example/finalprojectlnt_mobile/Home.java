package com.example.finalprojectlnt_mobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class Home extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FragmentAdapter fragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        tabLayout.setupWithViewPager(viewPager);
        setViewPager(viewPager);

    }

    private void setViewPager(ViewPager viewPager){
        if (fragmentAdapter == null){
            fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
            fragmentAdapter.addFragment(new CounterFragment(), "Counter");
            fragmentAdapter.addFragment(new AreaFragment(), "Area");
            fragmentAdapter.addFragment(new VolumeFragment(), "Volume");
            viewPager.setAdapter(fragmentAdapter);
        }
    }
}