package com.wisn.suvideo.player.exoplayer;

import android.content.Context;

import com.wisn.suvideo.player.AbstractPlayer;
import com.wisn.suvideo.player.PlayerFactory;

/**
 * Created by Wisn on 2019-07-23 16:19.
 */
public class ExoMediaPlayerFactory extends PlayerFactory {

    private Context mContext;

    public ExoMediaPlayerFactory(Context context) {
        mContext = context.getApplicationContext();
    }

    public static ExoMediaPlayerFactory create(Context context) {
        return new ExoMediaPlayerFactory(context);
    }

    @Override
    public AbstractPlayer createPlayer() {
        return new ExoMediaPlayer(mContext);
    }
}
