package com.suplayer.productdetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.TableLayout;

import com.suplayer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wisn on 2019-07-26 17:26.
 */
public class ProdetailActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TableLayout tabMode;
    private List<String> tabs=new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        setContentView(R.layout.activity_prodetail);
        tabMode = findViewById(R.id.tabMode);
        viewPager = findViewById(R.id.viewpager);
        tabs.add("详情");
        tabs.add("测试");
        DetailFragment detailFragment = new DetailFragment();
        TestFragment testFragment = new TestFragment();
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                if (i == 0) {
                    return detailFragment;
                } else if (i == 1) {
                    return testFragment;
                }
                return null;
            }

            @Override
            public int getCount() {
                return 2;
            }
        });
    }
}
