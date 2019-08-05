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
import com.wisn.suvideo.SuVideoView;
import com.wisn.suvideo.control.impl.ProductVideoController;
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
//        suVideoView = SeamlessPlayerHelper.getInstance(getActivity()).getSuVideoView();
        suVideoView = new SuVideoView(getContext());;
        //播放raw
        suVideoView.setUrl(Constants.VOD_URL);
        suVideoView.setProgressManager(new ProgressManagerMemory());
        ProductVideoController productVideoController = new ProductVideoController(getContext());
        productVideoController.setDefaultVoiceEnable(false);
        productVideoController.setDefaultisInProduct(false);
        suVideoView.setVideoController(productVideoController);
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
        removePlayerFormParent();
        super.onDestroyView();
    }
}
