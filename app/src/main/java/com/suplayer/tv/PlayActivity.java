package com.suplayer.tv;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.suplayer.R;
import com.suplayer.Constants;
import com.wisn.suvideo.SuVideoView;
import com.wisn.suvideo.base.BaseActivity;
import com.wisn.suvideo.control.impl.FullScreenVideoView;
import com.wisn.suvideo.control.impl.StandardVideoController;
import com.wisn.suvideo.manager.impl.ProgressManagerMemory;

public class PlayActivity extends BaseActivity {
    private SuVideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alive);
        String url = (String) this.getIntent().getSerializableExtra(Constants.data);
        String name = (String) this.getIntent().getSerializableExtra(Constants.name);
        boolean isalive = this.getIntent().getBooleanExtra(Constants.isalive, false);
        boolean isFull = this.getIntent().getBooleanExtra(Constants.isFull, false);
        boolean isLocal = this.getIntent().getBooleanExtra(Constants.isLocal, false);
        FrameLayout container = findViewById(R.id.container);
        StandardVideoController controller = new StandardVideoController(this);
        if (isalive) {
            controller.setLive();
        }
        controller.setTitle(name);
        if (isFull) {
            mVideoView = new FullScreenVideoView(this);
        } else {
            mVideoView = new SuVideoView(this);
        }
        mVideoView.setVideoController(controller);
        mVideoView.setUrl(url,isLocal);
        mVideoView.setProgressManager(new ProgressManagerMemory());
        mVideoView.start();
        container.addView(mVideoView);
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
