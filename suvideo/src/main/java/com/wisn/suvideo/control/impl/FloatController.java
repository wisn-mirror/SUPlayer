package com.wisn.suvideo.control.impl;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;

import com.wisn.suvideo.R;
import com.wisn.suvideo.SuVideoView;
import com.wisn.suvideo.control.GestureVideoController;
import com.wisn.suvideo.helper.L;
import com.wisn.suvideo.manager.PIPManager;

/**
 * Created by Wisn on 2019-07-31 14:46.
 */
public class FloatController  extends GestureVideoController implements View.OnClickListener {


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
            PIPManager.getInstance(getContext()).stopFloatWindow();
            PIPManager.getInstance(getContext()).reset();
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
    ViewGroup parent;
/*
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                setPressed(true);
                if (getParent() != null) {
                    parent = (ViewGroup) getParent();
                    getParent().requestDisallowInterceptTouchEvent(true);

                }
                lastX = rawX;
                lastY = rawY;
                changx=initx = (int) parent.getX();
                changy=inity = (int) parent.getY();
                parentHeight = parent.getHeight();
                parentWidth = parent.getWidth();
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = rawX - lastX;
                int dy = rawY - lastY;
                //这里修复一些华为手机无法触发点击事件
                int distance = (int) Math.sqrt(dx * dx + dy * dy);
                if (distance == 0) {
                    break;
                }
                float   x =changx= parent.getX() + dx;
                float   y =changy= parent.getY() + dy;
                //检测是否到达边缘 左上右下
                x = x < 0 ? 0 : x > parentWidth -parent. getWidth() ? parentWidth - parent.getWidth() : x;
                y = parent.getY() < 0 ? 0 : parent.getY() + parent.getHeight() > parentHeight ? parentHeight - parent.getHeight() : y;
                parent.setX(x);
                parent.setY(y);
                lastX = rawX;
                lastY = rawY;
                L.i("Log isDrag=" + isDrag + "getX=" + parent.getX() + ";getY=" +parent. getY() + ";parentWidth=" + parentWidth);
                break;
            case MotionEvent.ACTION_UP:
                if (!isNotDrag()) {
                    //恢复按压效果
                    setPressed(false);
                    //Log.i("getX="+getX()+"；screenWidthHalf="+screenWidthHalf);
                    if (rawX >= parentWidth / 2) {
                        //靠右吸附
                        animate().setInterpolator(new DecelerateInterpolator())
                                .setDuration(300)
                                .xBy(parentWidth - parent.getWidth() -parent. getX())
                                .start();
                    } else {
                        //靠左吸附
                        ObjectAnimator oa = ObjectAnimator.ofFloat(parent, "x", parent.getX(), 0);
                        oa.setInterpolator(new DecelerateInterpolator());
                        oa.setDuration(300);
                        oa.start();
                    }
                }
                break;
        }
        //如果是拖拽则消s耗事件，否则正常传递即可。
        return !isNotDrag() || super.onTouchEvent(event);
    }

    private boolean isNotDrag() {
        return !isDrag;
    }


    private boolean isDrag = true;
    private int parentHeight;
    private int parentWidth;

    private int lastX;
    private int lastY;

    private int initx;
    private int inity;

    private float changx;
    private float changy;*/
}
