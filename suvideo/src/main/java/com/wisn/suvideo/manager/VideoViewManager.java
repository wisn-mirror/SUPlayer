package com.wisn.suvideo.manager;

import com.wisn.suvideo.SuVideoView;
import com.wisn.suvideo.VideoViewConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wisn on 2019-07-23 15:58.
 */
public class VideoViewManager {

    /**
     * 当前正在播放的VideoView
     */
    private List<SuVideoView> mVideoViews = new ArrayList<>();

    private boolean mPlayOnMobileNetwork;

    private VideoViewManager() {
        mPlayOnMobileNetwork = getConfig().mPlayOnMobileNetwork;
    }

    private static VideoViewManager sInstance;

    private static VideoViewConfig sConfig;

    public static void setConfig(VideoViewConfig config) {
        if (sConfig == null) {
            synchronized (VideoViewConfig.class) {
                if (sConfig == null) {
                    sConfig = config == null ? VideoViewConfig.newBuilder().build() : config;
                }
            }
        }
    }

    public static VideoViewConfig getConfig() {
        setConfig(null);
        return sConfig;
    }

    public boolean playOnMobileNetwork() {
        return mPlayOnMobileNetwork;
    }

    public void setPlayOnMobileNetwork(boolean playOnMobileNetwork) {
        mPlayOnMobileNetwork = playOnMobileNetwork;
    }

    public static VideoViewManager instance() {
        if (sInstance == null) {
            synchronized (VideoViewManager.class) {
                if (sInstance == null) {
                    sInstance = new VideoViewManager();
                }
            }
        }
        return sInstance;
    }


    public void addVideoView(SuVideoView videoView) {
        mVideoViews.add(videoView);
    }

    public void removeVideoView(SuVideoView videoView) {
        mVideoViews.remove(videoView);
    }

    public List<SuVideoView> getVideoViews() {
        return mVideoViews;
    }

    @Deprecated
    public void releaseVideoPlayer() {
        release();
    }

    public void pause() {
        for (int i = 0; i < mVideoViews.size(); i++) {
            SuVideoView vv = mVideoViews.get(i);
            if (vv != null) {
                vv.pause();
            }
        }
    }

    public void resume() {
        for (int i = 0; i < mVideoViews.size(); i++) {
            SuVideoView vv = mVideoViews.get(i);
            if (vv != null) {
                vv.resume();
            }
        }
    }

    public void release() {
        for (int i = 0; i < mVideoViews.size(); i++) {
            SuVideoView vv = mVideoViews.get(i);
            if (vv != null) {
                vv.release();
                i--;
            }
        }
    }

    public boolean onBackPressed() {
        for (int i = 0; i < mVideoViews.size(); i++) {
            SuVideoView vv = mVideoViews.get(i);
            if (vv != null) {
                boolean b = vv.onBackPressed();
                if (b) return true;
            }
        }
        return false;
    }
}
