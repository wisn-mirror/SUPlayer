package com.wisn.suvideo.control.impl;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.wisn.suvideo.R;
import com.wisn.suvideo.SuVideoView;
import com.wisn.suvideo.control.BaseVideoController;

/**
 * Created by Wisn on 2019-07-31 14:46.
 */
public class FloatController  extends BaseVideoController implements View.OnClickListener {


    private ProgressBar proLoading;

    public FloatController(@NonNull Context context) {
        super(context);
    }

    public FloatController(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_float_controller;
    }

    @Override
    protected void initView() {
        super.initView();
        this.setOnClickListener(this);
        mControllerView.findViewById(R.id.btn_close).setOnClickListener(this);
        proLoading = mControllerView.findViewById(R.id.loading);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_close) {

        } else if (id == R.id.start_play) {
            doPauseResume();
        }
    }

    @Override
    public void setPlayState(int playState) {
        super.setPlayState(playState);
        switch (playState) {
            case SuVideoView.STATE_IDLE:
                proLoading.setVisibility(GONE);
                break;
            case SuVideoView.STATE_PLAYING:
                proLoading.setVisibility(GONE);
                hide();
                break;
            case SuVideoView.STATE_PAUSED:
                proLoading.setVisibility(GONE);
                show(0);
                break;
            case SuVideoView.STATE_PREPARING:
                proLoading.setVisibility(VISIBLE);
                break;
            case SuVideoView.STATE_PREPARED:
                proLoading.setVisibility(GONE);
                break;
            case SuVideoView.STATE_ERROR:
                proLoading.setVisibility(GONE);
                break;
            case SuVideoView.STATE_BUFFERING:
                proLoading.setVisibility(VISIBLE);
                break;
            case SuVideoView.STATE_BUFFERED:
                proLoading.setVisibility(GONE);
                break;
            case SuVideoView.STATE_PLAYBACK_COMPLETED:
                show(0);
                removeCallbacks(mShowProgress);
                break;
        }
    }


    @Override
    public void show() {
        show(mDefaultTimeout);
    }

    private void show(int timeout) {
        if (mCurrentPlayState == SuVideoView.STATE_BUFFERING) return;
        mShowing = true;
        removeCallbacks(mFadeOut);
        if (timeout != 0) {
            postDelayed(mFadeOut, timeout);
        }
    }


    @Override
    public void hide() {
        if (mCurrentPlayState == SuVideoView.STATE_BUFFERING) return;
        if (mShowing) {
            mShowing = false;
        }
    }
}
