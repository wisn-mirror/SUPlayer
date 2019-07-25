package com.suplayer.douyin;

import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewParent;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.suplayer.R;
import com.suplayer.Url;
import com.wisn.suvideo.SuVideoView;
import com.wisn.suvideo.control.impl.DouYinController;
import com.wisn.suvideo.listener.OnViewPagerListener;
import com.wisn.suvideo.manager.ViewPagerLayoutManager;

import java.util.List;

public class DouYinActivity extends AppCompatActivity {
    private SuVideoView mVideoView;
    private RecyclerView mRecyclerView;
    private ViewPagerLayoutManager viewPagerLayoutManager;
    private List<VideoBean> mVideoList;
    private DouYinController douYinController;
    private int mCurrentPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
           /* actionBar.setTitle(R.string.str_tiktok);
            actionBar.setDisplayHomeAsUpEnabled(true);*/
            actionBar.hide();
        }

        setContentView(R.layout.activity_douyin);
        setStatusBarTransparent();
        mVideoView = new SuVideoView(this);
        mVideoView.setLooping(true);
        douYinController = new DouYinController(this);
        mVideoView.setVideoController(douYinController);
        mRecyclerView = findViewById(R.id.rv);
        viewPagerLayoutManager = new ViewPagerLayoutManager(this, OrientationHelper.VERTICAL);
        mRecyclerView.setLayoutManager(viewPagerLayoutManager);
        viewPagerLayoutManager.setOnViewPagerListener(new OnViewPagerListener() {
            @Override
            public void onInitComplete() {
                //自动播放第一条
                startPlay(0);
            }

            @Override
            public void onPageRelease(boolean isNext, int position) {
                if (mCurrentPosition == position) {
                    mVideoView.release();
                }
            }

            @Override
            public void onPageSelected(int position, boolean isBottom) {
                if (mCurrentPosition == position) return;
                startPlay(position);
                mCurrentPosition = position;

            }
        });
        mVideoList = Url.getVideoBean();
        DouyinAdapter douyinAdapter = new DouyinAdapter(mVideoList, this);
        mRecyclerView.setAdapter(douyinAdapter);
    }

    private void startPlay(int position) {

        View itemView = mRecyclerView.getChildAt(0);
        FrameLayout frameLayout = itemView.findViewById(R.id.container);
        Glide.with(this)
                .load(mVideoList.get(position).getThumb())
                .into(douYinController.getThumb());
        ViewParent parent = mVideoView.getParent();
        if (parent instanceof FrameLayout) {
            ((FrameLayout) parent).removeView(mVideoView);
        }
        frameLayout.addView(mVideoView);
        mVideoView.setUrl(mVideoList.get(position).getUrl());
        mVideoView.setScreenScale(SuVideoView.SCREEN_SCALE_CENTER_CROP);
        mVideoView.start();
    }


    @Override
    protected void onPause() {
        super.onPause();
        mVideoView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mVideoView.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mVideoView.release();
    }

    /**
     * 把状态栏设成透明
     */
    private void setStatusBarTransparent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = DouYinActivity.this.getWindow().getDecorView();
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

}
