package com.wisn.suvideo.control.impl;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.wisn.suvideo.R;
import com.wisn.suvideo.SuVideoView;
import com.wisn.suvideo.helper.PlayerUtils;

/**
 * Created by Wisn on 2019-08-05 11:33.
 */
public class FullScreenController  extends StandardVideoController {
    public FullScreenController(@NonNull Context context) {
        super(context);
    }

    public FullScreenController(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FullScreenController(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.lock) {
            doLockUnlock();
        } else if (i == R.id.iv_play || i == R.id.iv_replay) {
            doPauseResume();
        } else if (i == R.id.back) {
            PlayerUtils.scanForActivity(getContext()).finish();
        }
    }

    @Override
    public void setPlayerState(int playerState) {
        super.setPlayerState(playerState);
        if (playerState == SuVideoView.PLAYER_FULL_SCREEN) {
            mFullScreenButton.setVisibility(GONE);
            mBottomContainer.setPadding(0, 0, PlayerUtils.dp2px(getContext(), 10), 0);
        }
    }
}
