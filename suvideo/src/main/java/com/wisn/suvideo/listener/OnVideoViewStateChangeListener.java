package com.wisn.suvideo.listener;

/**
 * Created by Wisn on 2019-07-23 15:57.
 */
public interface OnVideoViewStateChangeListener {
    void onPlayerStateChanged(int playerState);
    void onPlayStateChanged(int playState);
}
