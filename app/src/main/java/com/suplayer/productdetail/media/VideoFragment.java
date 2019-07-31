package com.suplayer.productdetail.media;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.suplayer.R;
import com.suplayer.Url;
import com.wisn.suvideo.SuVideoView;
import com.wisn.suvideo.control.impl.ProductVideoController;
import com.wisn.suvideo.manager.impl.ProgressManagerMemory;

/**
 * Created by Wisn on 2019-07-30 14:29.
 */
public class VideoFragment extends Fragment {
    private SuVideoView mVideoView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_video, null);
        mVideoView = view.findViewById(R.id.player);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //播放raw
        mVideoView.setUrl(Url.VOD_URL);
        mVideoView.setProgressManager(new ProgressManagerMemory());
        ProductVideoController productVideoController = new ProductVideoController(getContext());
        productVideoController.setDefaultVoiceEnable(false);
        mVideoView.setVideoController(productVideoController);
        mVideoView.start();
    }

}
