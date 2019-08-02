package com.suplayer.productdetail;

import android.content.Intent;
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

import com.suplayer.R;
import com.suplayer.Url;
import com.suplayer.productdetail.media.ProductMediaActivity;
import com.wisn.suvideo.SuVideoView;
import com.wisn.suvideo.control.impl.FloatController;
import com.wisn.suvideo.control.impl.ProductVideoController;
import com.wisn.suvideo.helper.L;
import com.wisn.suvideo.view.LyfScrollView;
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
    private FrameLayout viewContent;
    private boolean playing;
    private int currentPlayState;
    private int currentPlayerState;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_detail, null);
        containerRoot = view.findViewById(R.id.container);
        fl_content = view.findViewById(R.id.fl_content);
        viewContent = view.findViewById(R.id.viewContent);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //播放raw
        mVideoView =new  SuVideoView(getActivity());
        if (mVideoView.isTinyScreen()) mVideoView.stopTinyScreen();
        mVideoView.release();
        mVideoView.setUrl(Url.VOD_URL);
        productVideoController = new ProductVideoController(getContext());
        productVideoController.setProductMediaDetais(new ProductVideoController.ProductMediaDetais() {
            @Override
            public void jumpProductMediaDetais() {
                playing = mVideoView.isPlaying();
                getActivity().startActivity(new Intent(getActivity(),ProductMediaActivity.class));
                getActivity().overridePendingTransition(0,0);
            }
        });
        mVideoView.setVideoController(productVideoController);
        mVideoView.start();
        fl_content.addView(mVideoView);
        mFloatController = new FloatController(getContext());
        int dip130=  UIUtil.dip2px(getContext(),300);
        containerRoot.setScrollListener(new LyfScrollView.ScrollViewListener() {

            @Override
            public void onScrollChanged(LyfScrollView scrollView, int x, int y, int oldx, int oldy) {
                L.e("onScrollChanged x:"+x+" y:"+y+" oldx:"+oldx+" oldy:"+oldy);
                if (y >dip130) {
                    if (isXiaopin) return;
                    isXiaopin=true;
                    viewContent.setVisibility(View.VISIBLE);
                    mVideoView.startTinyScreen(true,viewContent);
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mFloatController.setPlayState(mVideoView.getCurrentPlayState());
                            mFloatController.setPlayerState(mVideoView.getCurrentPlayerState());
                            mVideoView.setVideoController(mFloatController);
                        }
                    },20);

                } else {
                    if (isXiaopin) {
                        isXiaopin=false;
                        viewContent.setVisibility(View.GONE);
                        mVideoView.stopTinyScreen(viewContent);
                        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                productVideoController.setPlayState(mVideoView.getCurrentPlayState());
                                productVideoController.setPlayerState(mVideoView.getCurrentPlayerState());
                                mVideoView.setVideoController(productVideoController);
                                try {
                                    ((ViewGroup) mVideoView.getParent()).removeView(mVideoView);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                fl_content.addView(mVideoView);
                            }
                        },20);

                    }
                }
            }
        });
    }
/*
    private void addTopView(boolean isinit) {
        if(fl_content.getChildCount()==0){
            mVideoView = SeamlessPlayerHelper.getInstance(getActivity()).getSuVideoView();
            if (mVideoView.isTinyScreen()) mVideoView.stopTinyScreen();
            mVideoView.release();
            mVideoView.setProgressManager(new ProgressManagerMemory());
            mVideoView.setVideoController(productVideoController);
            if(isinit){
                mVideoView.setUrl(Url.VOD_URL);
                mVideoView.start();
            }
            fl_content.removeAllViews();
            ViewParent parent = mVideoView.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(mVideoView);
            }
            fl_content.addView(mVideoView);
        }
    }*/

    @Override
    public void onResume() {
        super.onResume();
        if(!mVideoView.isPlaying()){
            if(playing){
//                productVideoController.setPlayState(currentPlayState);
//                productVideoController.setPlayerState(currentPlayState);
//                mVideoView.setVideoController(productVideoController);
                mVideoView.start();
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        currentPlayState = mVideoView.getCurrentPlayState();
        currentPlayerState = mVideoView.getCurrentPlayerState();
        mVideoView.pause();
    }

    @Override
    public void onDestroyView() {
        if(mVideoView!=null) mVideoView.release();
        super.onDestroyView();
    }

}
