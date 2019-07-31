package com.suplayer.productdetail;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ScrollView;

import com.suplayer.R;
import com.suplayer.Url;
import com.wisn.suvideo.SuVideoView;
import com.wisn.suvideo.control.impl.FloatController;
import com.wisn.suvideo.control.impl.ProductVideoController;
import com.wisn.suvideo.helper.L;
import com.wisn.suvideo.manager.PIPManager;
import com.wisn.suvideo.view.LyfScrollView;
import com.yanzhenjie.permission.AndPermission;

import net.lucode.hackware.magicindicator.buildins.UIUtil;

/**
 * Created by Wisn on 2019-07-26 17:29.
 */
public class DetailFragment extends Fragment {
    private SuVideoView mVideoView;
    private LyfScrollView containerRoot;
    private FrameLayout fl_content;
    private ProductVideoController productVideoController;
    private FloatController mFloatController;
    private  boolean isXiaopin;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        containerRoot= (LyfScrollView) inflater.inflate(R.layout.fragment_detail, null);
        fl_content = containerRoot.findViewById(R.id.fl_content);
        return containerRoot;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

      /*  mVideoView =new  SuVideoView(getContext());
        mVideoView.setUrl(Url.VOD_URL);
        productVideoController = new ProductVideoController(getContext());
        mVideoView.setVideoController(productVideoController);
        mVideoView.start();
        fl_content.addView(mVideoView);
        containerRoot.setScrollListener(new LyfScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(LyfScrollView scrollView, int x, int y, int oldx, int oldy) {
                if (y > mVideoView.getHeight()) {
                    mVideoView.startTinyScreen();
                } else {
                    mVideoView.stopTinyScreen();
                }
            }
        });*/
        //播放raw
        mVideoView =new  SuVideoView(getActivity());
        if (mVideoView.isTinyScreen()) mVideoView.stopTinyScreen();
        mVideoView.release();
        mVideoView.setUrl(Url.VOD_URL);
        productVideoController = new ProductVideoController(getContext());
        mVideoView.setVideoController(productVideoController);
        mVideoView.start();
        fl_content.addView(mVideoView);
        mFloatController = new FloatController(getContext());
       int dip130=  UIUtil.dip2px(getContext(),300);

        containerRoot.setScrollListener(new LyfScrollView.ScrollViewListener() {

            @Override
            public void onScrollChanged(LyfScrollView scrollView, int x, int y, int oldx, int oldy) {
                if (y >dip130) {
                    if (isXiaopin) return;
                    isXiaopin=true;
                    mVideoView.startTinyScreen();
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mFloatController.setPlayState(mVideoView.getCurrentPlayState());
                            mFloatController.setPlayerState(mVideoView.getCurrentPlayerState());
                            mVideoView.setVideoController(mFloatController);
                        }
                    },100);

                } else {
                    if (isXiaopin) {
                        isXiaopin=false;
                        mVideoView.stopTinyScreen();
                        /*if (mVideoView.getParent() instanceof ViewGroup) {
                            ((ViewGroup) mVideoView.getParent()).removeView(mVideoView);
                            fl_content.addView(mVideoView);
                        }*/
                        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                productVideoController.setPlayState(mVideoView.getCurrentPlayState());
                                productVideoController.setPlayerState(mVideoView.getCurrentPlayerState());
                                mVideoView.setVideoController(productVideoController);
//                                fl_content.removeAllViews();
                                ((ViewGroup) mVideoView.getParent()).removeView(mVideoView);
                                fl_content.addView(mVideoView);
                            }
                        },100);

                    }

                }
            }
        });
    }

}
