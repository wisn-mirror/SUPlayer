package com.suplayer.productdetail.media;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;

import com.suplayer.Constants;
import com.suplayer.R;
import com.suplayer.productdetail.SeamlessPlayerHelper;
import com.wisn.suvideo.SuVideoView;
import com.wisn.suvideo.control.impl.ProductVideoController;
import com.wisn.suvideo.helper.L;
import com.wisn.suvideo.listener.OnVideoViewStateChangeListener;
import com.wisn.suvideo.manager.impl.ProgressManagerMemory;

/**
 * Created by Wisn on 2019-07-30 14:29.
 */
public class MedialVideoFragment extends Fragment {

    private FrameLayout fl_container;
    private SuVideoView suVideoView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        fl_container = (FrameLayout) inflater.inflate(R.layout.fragment_video, null);
        return fl_container;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SuVideoView suVideoView1 = SeamlessPlayerHelper.getInstance(getActivity()).getSuVideoView();
        suVideoView = new SuVideoView(getContext());
        //播放raw
        suVideoView.setUrl(Constants.VOD_URL);
        suVideoView.setProgressManager(new ProgressManagerMemory());
        ProductVideoController productVideoController = new ProductVideoController(getContext());
        productVideoController.setPlayState(suVideoView1.getCurrentPlayState());
        productVideoController.setPlayerState(suVideoView1.getCurrentPlayerState());
        productVideoController.setDefaultVoiceMute(true);
        productVideoController.setDefaultisInProduct(false);
        suVideoView.setVideoController(productVideoController);
        suVideoView.seekTo(suVideoView1.getCurrentPosition());
        OnVideoViewStateChangeListener mOnVideoViewStateChangeListener = new OnVideoViewStateChangeListener() {
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
                        L.d("details STATE_IDLE：");

                        break;
                    case SuVideoView.STATE_PREPARING:
                        L.d("details STATE_PREPARING：");

                        break;
                    case SuVideoView.STATE_PREPARED:
                        L.d("details STATE_PREPARED：");

                        //需在此时获取视频宽高

                        break;
                    case SuVideoView.STATE_PLAYING:
                        L.d("details STATE_PLAYING：");
                        suVideoView.seekTo(suVideoView1.getCurrentPosition());

                        break;
                    case SuVideoView.STATE_PAUSED:
                        L.d("details STATE_PAUSED：");

                        break;
                    case SuVideoView.STATE_BUFFERING:
                        L.d("details STATE_BUFFERING：");

                        break;
                    case SuVideoView.STATE_BUFFERED:
                        L.d("details STATE_BUFFERED：");

                        break;
                    case SuVideoView.STATE_PLAYBACK_COMPLETED:
                        L.d("details STATE_PLAYBACK_COMPLETED：");
                        break;
                    case SuVideoView.STATE_ERROR:
                        L.d("details STATE_ERROR：");

                        break;
                }
            }
        };
        suVideoView.addOnVideoViewStateChangeListener(mOnVideoViewStateChangeListener);

        suVideoView.start();
        fl_container.removeAllViews();
        removePlayerFormParent();
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity= Gravity.CENTER;
        //将播放器视图添加到当前FrameLayout中即退出了全屏
        fl_container.addView(suVideoView,params);
    }

    /**
     * 将播放器从父控件中移除
     */
    private void removePlayerFormParent() {
        ViewParent parent = suVideoView.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(suVideoView);
        }
    }

    @Override
    public void onDestroyView() {
        SeamlessPlayerHelper.getInstance(getActivity()).getSuVideoView().seekTo(suVideoView.getCurrentPosition());

        removePlayerFormParent();

        super.onDestroyView();
    }
}
