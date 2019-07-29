package com.suplayer.xiaohongshu;

import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowInsets;

import com.suplayer.R;
import com.suplayer.Url;
import com.wisn.suvideo.SuVideoView;
import com.wisn.suvideo.control.impl.StandardVideoController;
import com.wisn.suvideo.manager.impl.ProgressManagerMemory;

public class HongshuActivity extends AppCompatActivity {
    private SuVideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
           /* actionBar.setTitle(R.string.str_tiktok);
            actionBar.setDisplayHomeAsUpEnabled(true);*/
            actionBar.hide();
        }
        setContentView(R.layout.activity_hongshu);
        setStatusBarTransparent();
        mVideoView = findViewById(R.id.player);
        StandardVideoController controller = new StandardVideoController(this);
        mVideoView.setVideoController(controller);
//        mVideoView.setUrl(Url.VOD_URL);
        mVideoView.setProgressManager(new ProgressManagerMemory());
        mVideoView.setUrl(Url.local_resvideo[2]);
        mVideoView.start();
    }
    /**
     * 把状态栏设成透明
     */
    private void setStatusBarTransparent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = HongshuActivity.this.getWindow().getDecorView();
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


    @Override
    public void onBackPressed() {
        if (!mVideoView.onBackPressed()) {
            super.onBackPressed();
        }
    }
}
