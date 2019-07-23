package com.wisn.suvideo.listener;

/**
 * Created by Wisn on 2019-07-23 15:57.
 */
public interface PlayerEventListener {

    void onError();

    void onCompletion();

    void onInfo(int what, int extra);

    void onPrepared();

    void onVideoSizeChanged(int width, int height);
}
