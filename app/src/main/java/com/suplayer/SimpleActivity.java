package com.suplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.wisn.suvideo.SuVideoView;
import com.wisn.suvideo.control.impl.StandardVideoController;
import com.wisn.suvideo.helper.L;
import com.wisn.suvideo.listener.OnVideoViewStateChangeListener;
import com.wisn.suvideo.manager.impl.ProgressManagerMemory;

public class SimpleActivity extends AppCompatActivity {
    private SuVideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        mVideoView = findViewById(R.id.player);
        StandardVideoController controller = new StandardVideoController(this);
        mVideoView.setVideoController(controller);
//        mVideoView.setUrl(Url.VOD_URL);
        mVideoView.setProgressManager(new ProgressManagerMemory());
        mVideoView.setUrl(Url.local_resvideo[2]);
        mVideoView.start();
        mVideoView.addOnVideoViewStateChangeListener(mOnVideoViewStateChangeListener);

    }

    private OnVideoViewStateChangeListener mOnVideoViewStateChangeListener = new OnVideoViewStateChangeListener() {
        @Override
        public void onPlayerStateChanged(int playerState) {
            switch (playerState) {
                case SuVideoView.PLAYER_NORMAL://小屏
                    break;
                case SuVideoView.PLAYER_FULL_SCREEN://全屏
                    break;
            }
        }

        @Override
        public void onPlayStateChanged(int playState) {
            switch (playState) {
                case SuVideoView.STATE_IDLE:
                    break;
                case SuVideoView.STATE_PREPARING:
                    break;
                case SuVideoView.STATE_PREPARED:
                    //需在此时获取视频宽高
                    int[] videoSize = mVideoView.getVideoSize();
                    L.d("视频宽：" + videoSize[0]);
                    L.d("视频高：" + videoSize[1]);
                    break;
                case SuVideoView.STATE_PLAYING:
                    break;
                case SuVideoView.STATE_PAUSED:
                    break;
                case SuVideoView.STATE_BUFFERING:
                    break;
                case SuVideoView.STATE_BUFFERED:
                    break;
                case SuVideoView.STATE_PLAYBACK_COMPLETED:
                    break;
                case SuVideoView.STATE_ERROR:
                    break;
            }
        }
    };

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

    public void screenScaleDefault(View view) {
        mVideoView.setScreenScale(SuVideoView.SCREEN_SCALE_DEFAULT);
    }

    public void screenScale169(View view) {
        mVideoView.setScreenScale(SuVideoView.SCREEN_SCALE_16_9);
    }

    public void screenScale43(View view) {
        mVideoView.setScreenScale(SuVideoView.SCREEN_SCALE_4_3);
    }

    public void screenScaleOriginal(View view) {
        mVideoView.setScreenScale(SuVideoView.SCREEN_SCALE_ORIGINAL);
    }

    public void screenScaleMatch(View view) {
        mVideoView.setScreenScale(SuVideoView.SCREEN_SCALE_MATCH_PARENT);
    }

    public void screenScaleCenterCrop(View view) {
        mVideoView.setScreenScale(SuVideoView.SCREEN_SCALE_CENTER_CROP);
    }

    public void startTinyScreen(View view) {
        mVideoView.startTinyScreen();
    }

    public void stopTinyScreen(View view) {
        mVideoView.stopTinyScreen();
    }

    int i = 0;

    public void setMirrorRotate(View view) {
        mVideoView.setMirrorRotation(i % 2 == 0);
        i++;
    }

    public void setSpeed0_75(View view) {
        mVideoView.setSpeed(0.75f);
    }

    public void setSpeed0_5(View view) {
        mVideoView.setSpeed(0.5f);
    }

    public void setSpeed1_0(View view) {
        mVideoView.setSpeed(1.0f);
    }

    public void setSpeed1_5(View view) {
        mVideoView.setSpeed(1.5f);
    }

    public void setSpeed2_0(View view) {
        mVideoView.setSpeed(2.0f);
    }
}
