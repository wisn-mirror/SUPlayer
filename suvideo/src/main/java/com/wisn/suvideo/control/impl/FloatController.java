package com.wisn.suvideo.control.impl;

import android.content.Context;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.wisn.suvideo.R;
import com.wisn.suvideo.SuVideoView;
import com.wisn.suvideo.control.BaseVideoController;
import com.wisn.suvideo.helper.L;

/**
 * Created by Wisn on 2019-07-31 14:46.
 */
public class FloatController  extends BaseVideoController implements View.OnClickListener {


    private ProgressBar proLoading;
    private ImageView btn_close;

    public FloatController(@NonNull Context context) {
        super(context);
    }

    public FloatController(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.suplayer_layout_float_controller;
    }

    @Override
    protected void initView() {
        super.initView();
        btn_close = mControllerView.findViewById(R.id.btn_close);
        btn_close.setOnClickListener(this);
        proLoading = mControllerView.findViewById(R.id.loading);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_close) {
            L.e("click-----");
        } else if (id == R.id.start_play) {
            doPauseResume();
        }
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercept= super.onInterceptTouchEvent(ev);
        return intercept;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        boolean result= super.onTouchEvent(event);
        float x = event.getRawX(); // 获取相对于屏幕左上角的 x 坐标值
        float y = event.getRawY(); // 获取相对于屏幕左上角的 y 坐标值
        // View view;
        RectF rect = calcViewScreenLocation(btn_close);
        boolean isInViewRect = rect.contains(x, y);
        return isInViewRect;
    }
    /**
     * 计算指定的 View 在屏幕中的坐标。
     */
    public static RectF calcViewScreenLocation(View view) {
        int[] location = new int[2];
        // 获取控件在屏幕中的位置，返回的数组分别为控件左顶点的 x、y 的值
        view.getLocationOnScreen(location);
        return new RectF(location[0], location[1], location[0] + view.getWidth(),
                location[1] + view.getHeight());
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
