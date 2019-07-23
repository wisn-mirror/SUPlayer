package com.wisn.suvideo.player.mediaplayer;

import android.content.Context;

import com.wisn.suvideo.player.AbstractPlayer;
import com.wisn.suvideo.player.PlayerFactory;

/**
 * Created by Wisn on 2019-07-23 16:18.
 */
public class AndroidMediaPlayerFactory  extends PlayerFactory {

    private Context mContext;

    public AndroidMediaPlayerFactory(Context context) {
        mContext = context.getApplicationContext();
    }

    public static AndroidMediaPlayerFactory create(Context context) {
        return new AndroidMediaPlayerFactory(context);
    }

    @Override
    public AbstractPlayer createPlayer() {
        return new AndroidMediaPlayer(mContext);
    }
}
