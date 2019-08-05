package com.wisn.suvideo.control.impl;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.wisn.suvideo.SuVideoView;
import com.wisn.suvideo.helper.PlayerUtils;

/**
 * Created by Wisn on 2019-08-05 11:32.
 */
public class FullScreenVideoView  extends SuVideoView {

    public FullScreenVideoView(@NonNull Context context) {
        super(context);
    }

    public FullScreenVideoView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FullScreenVideoView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 直接开始全屏播放
     */
    public void startFullScreenDirectly() {
        Activity activity = PlayerUtils.scanForActivity(getContext());
        if (activity == null) return;
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        startFullScreen();
    }

    @Override
    protected void startPlay() {
        startFullScreenDirectly();
        super.startPlay();
    }

    @Override
    protected void onOrientationPortrait(Activity activity) {

    }

    @Override
    public boolean onBackPressed() {
        return false;
    }
}
