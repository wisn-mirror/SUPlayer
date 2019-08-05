package com.suplayer.example.xiaohongshu;

import android.os.Bundle;

import com.suplayer.R;
import com.suplayer.Constants;
import com.wisn.suvideo.SuVideoView;
import com.wisn.suvideo.base.BaseActivity;
import com.wisn.suvideo.control.impl.StandardVideoController;
import com.wisn.suvideo.manager.impl.ProgressManagerMemory;

public class HongshuActivity extends BaseActivity {
    private SuVideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hongshu);
        mVideoView = findViewById(R.id.player);
        StandardVideoController controller = new StandardVideoController(this);
        mVideoView.setVideoController(controller);
//        mVideoView.setUrl(Constants.VOD_URL);
        mVideoView.setProgressManager(new ProgressManagerMemory());
        mVideoView.setUrl(Constants.local_resvideo[2]);
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


    @Override
    public void onBackPressed() {
        if (!mVideoView.onBackPressed()) {
            super.onBackPressed();
        }
    }
}
