package com.suplayer.productdetail.media;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowInsets;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.suplayer.R;
import com.wisn.suvideo.manager.VideoViewManager;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wisn on 2019-07-30 11:37.
 */
public class ProductMediaActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private MagicIndicator magicIndicator;
    private CommonNavigatorAdapter commonNavigatorAdapter;
    private VideoViewManager mVideoViewManager;

    private int dip_8;
    private int dip_6;
    private int dip_20;
    private int dip_4;
    private List<String> tabs = new ArrayList<>();
    private LinearLayout ll_bottom_lick_cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        dip_4 = UIUtil.dip2px(this, 2);
        dip_8 = UIUtil.dip2px(this, 8);
        dip_6 = UIUtil.dip2px(this, 6);
        dip_20 = UIUtil.dip2px(this, 16);
        setContentView(R.layout.activity_product_media);
        setStatusBarTransparent();
        magicIndicator = (MagicIndicator) findViewById(R.id.indicator);
        viewPager = findViewById(R.id.viewpager);
        ImageView media_iv_back = findViewById(R.id.media_iv_back);
        ll_bottom_lick_cart = findViewById(R.id.ll_bottom_lick_cart);
        media_iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductMediaActivity.this.finish();
            }
        });
        mVideoViewManager = VideoViewManager.instance();
        tabs.add("视频");
        tabs.add("图片");
        MedialVideoFragment videoFragment = new MedialVideoFragment();
        MedialImageFragment testFragment1 = new MedialImageFragment();
        CommonNavigator commonNavigator7 = new CommonNavigator(this);
        commonNavigatorAdapter = new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return tabs.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new SimplePagerTitleView(context);
                try {
                    simplePagerTitleView.setNormalColor(Color.parseColor("#666666"));
                    simplePagerTitleView.setTextSize(14);
                    simplePagerTitleView.setPadding(dip_8,
                            dip_6, dip_8, dip_6);
                    simplePagerTitleView.setSelectedColor(Color.parseColor("#FEFFFE"));
                    simplePagerTitleView.setText(tabs.get(index));
                    simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            viewPager.setCurrentItem(index);
                        }
                    });
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                }

                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                try {
                    indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                    indicator.setLineHeight(dip_4);
                    indicator.setLineWidth(dip_20);
                    indicator.setRoundRadius(UIUtil.dip2px(context, 2));
                    indicator.setStartInterpolator(new AccelerateInterpolator());
                    indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                    indicator.setColors(getResources().getColor(android.R.color.white));
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                }
                return indicator;
            }
        };
        commonNavigator7.setAdapter(commonNavigatorAdapter);
        commonNavigator7.setLeftPadding(UIUtil.dip2px(this, 10));
        magicIndicator.setNavigator(commonNavigator7);
        LinearLayout titleContainer = commonNavigator7.getTitleContainer(); // must after setNavigator
        titleContainer.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        titleContainer.setDividerDrawable(new ColorDrawable() {
            @Override
            public int getIntrinsicWidth() {
                return dip_8;
            }
        });
        ViewPagerHelper.bind(magicIndicator, viewPager);//getChildFragmentManager()
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                if(tabs.size()==1){
                    return testFragment1;
                }
                if (i == 0) {
                    return videoFragment;
                } else if (i == 1) {
                    return testFragment1;
                }
                return null;
            }

            @Override
            public int getCount() {
                return tabs.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                if (tabs != null && tabs.size() > position) return tabs.get(position);
                return "";
            }
        });
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (i != 0) {
                    mVideoViewManager.pause();
                    ll_bottom_lick_cart.setVisibility(View.GONE);
                } else {
                    mVideoViewManager.resume();
                    ll_bottom_lick_cart.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    /**
     * 把状态栏设成透明
     */
    private void setStatusBarTransparent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = ProductMediaActivity.this.getWindow().getDecorView();
            decorView.setOnApplyWindowInsetsListener((v, insets) -> {
                WindowInsets defaultInsets = v.onApplyWindowInsets(insets);
                return defaultInsets.replaceSystemWindowInsets(
                        defaultInsets.getSystemWindowInsetLeft(),
                        0,
                        defaultInsets.getSystemWindowInsetRight(),
                        defaultInsets.getSystemWindowInsetBottom());
            });
            ViewCompat.requestApplyInsets(decorView);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, android.R.color.transparent));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
//        mVideoViewManager.release();
    }

   /* @Override
    public void onBackPressed() {
//        if (!mVideoViewManager.onBackPressed()) {
//            super.onBackPressed();
//        }
    }*/
    @Override
    public void overridePendingTransition(int enterAnim, int exitAnim) {
        super.overridePendingTransition(0, 0);
    }

}
