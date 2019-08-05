package com.suplayer.tv;

import android.os.Bundle;

import com.suplayer.R;
import com.suplayer.Constants;
import com.suplayer.bean.AliveBean;
import com.wisn.suvideo.SuVideoView;
import com.wisn.suvideo.base.BaseActivity;
import com.wisn.suvideo.control.impl.FullScreenController;
import com.wisn.suvideo.control.impl.FullScreenVideoView;
import com.wisn.suvideo.control.impl.RotateInFullscreenController;
import com.wisn.suvideo.manager.impl.ProgressManagerMemory;

public class AliveActivity extends BaseActivity {
    private FullScreenVideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alive);
        String url = (String) this.getIntent().getSerializableExtra(Constants.data);
        String name = (String) this.getIntent().getSerializableExtra(Constants.name);
        mVideoView = findViewById(R.id.player);
        FullScreenController controller = new FullScreenController(this);
        controller.setLive();
        controller.setTitle(name);
        mVideoView.setVideoController(controller);
        mVideoView.setUrl(url);
        mVideoView.setProgressManager(new ProgressManagerMemory());
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
