package com.wisn.suvideo.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;

import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;

import com.wisn.suvideo.helper.L;
import com.wisn.suvideo.helper.PlayerUtils;

/**
 * Created by Wisn on 2019-07-31 18:34.
 */
public class FlowFrameLayout extends FrameLayout {


    private boolean isDrag = true;
    private int parentHeight;
    private int parentWidth;

    private int lastX;
    private int lastY;

    private int initx;
    private int inity;

    private float changx;
    private float changy;

    OnSuspendClickListener onSuspendClickListener;


    public FlowFrameLayout(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public FlowFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public FlowFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public void initView(Context context) {

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                setPressed(true);
                getParent().requestDisallowInterceptTouchEvent(true);
                lastX = rawX;
                lastY = rawY;
                changx=initx = (int) getX();
                changy=inity = (int) getY();
                ViewGroup parent;
                if (getParent() != null) {
                    parent = (ViewGroup) getParent();
                    parentHeight = parent.getHeight();
                    parentWidth = parent.getWidth();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = rawX - lastX;
                int dy = rawY - lastY;
                //这里修复一些华为手机无法触发点击事件
                int distance = (int) Math.sqrt(dx * dx + dy * dy);
                if (distance == 0) {
                    break;
                }
                float   x =changx= getX() + dx;
                float   y =changy= getY() + dy;
                //检测是否到达边缘 左上右下
                x = x < 0 ? 0 : x > parentWidth - getWidth() ? parentWidth - getWidth() : x;
                y = getY() < 0 ? 0 : getY() + getHeight() > parentHeight ? parentHeight - getHeight() : y;
                setX(x);
                setY(y);
                lastX = rawX;
                lastY = rawY;
                L.i("Log isDrag=" + isDrag + "getX=" + getX() + ";getY=" + getY() + ";parentWidth=" + parentWidth);
                break;
            case MotionEvent.ACTION_UP:
                if (!isNotDrag()) {
                    if (Math.abs(changx - initx) < 5 && Math.abs(changy - inity) < 5) {
                        if (onSuspendClickListener != null) {
                            onSuspendClickListener.performClick();
                        }
                    }
                    //恢复按压效果
                    setPressed(false);
                    //Log.i("getX="+getX()+"；screenWidthHalf="+screenWidthHalf);
                    if (rawX >= parentWidth / 2) {
                        //靠右吸附
                        animate().setInterpolator(new DecelerateInterpolator())
                                .setDuration(300)
                                .xBy(parentWidth - getWidth() - getX())
                                .start();
                    } else {
                        //靠左吸附
                        ObjectAnimator oa = ObjectAnimator.ofFloat(this, "x", getX(), 0);
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
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        return true;
    }

    private boolean isNotDrag() {
        return !isDrag;
    }

    public interface OnSuspendClickListener {

        void performClick();
    }


    public void setOnSuspendClickListener(OnSuspendClickListener onSuspendClickListener) {
        this.onSuspendClickListener = onSuspendClickListener;
    }
}
