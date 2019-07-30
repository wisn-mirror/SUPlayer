package com.suplayer.productdetail;

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

/**
 * Created by Wisn on 2019-07-26 17:29.
 */
public class DetailFragment extends Fragment {
    private SuVideoView mVideoView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_detail, null);
        mVideoView = view.findViewById(R.id.player);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //播放raw
        mVideoView.setUrl(Url.VOD_URL);
        mVideoView.setVideoController(new ProductVideoController(getContext()));
        mVideoView.start();
    }
}
