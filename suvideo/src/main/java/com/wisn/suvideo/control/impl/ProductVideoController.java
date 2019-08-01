package com.wisn.suvideo.control.impl;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.wisn.suvideo.R;
import com.wisn.suvideo.SuVideoView;
import com.wisn.suvideo.control.GestureVideoController;
import com.wisn.suvideo.helper.L;
import com.wisn.suvideo.helper.PlayerUtils;

/**
 * Created by Wisn on 2019-07-24 15:17.
 */
public class ProductVideoController extends GestureVideoController implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    protected TextView mTotalTime, mCurrTime;
    protected ImageView mFullScreenButton;
    protected LinearLayout mBottomContainer;
    protected RelativeLayout mTopContainer;
    protected SeekBar mVideoProgress;
    protected ImageView mBackButton;
    private boolean mIsDragging;

    private ProgressBar mBottomProgress;
    private ImageView mStartPlayButton;
    private ProgressBar mLoadingProgress;
    private ImageView mThumb;
    private FrameLayout mCompleteContainer;
    private ImageView mStopFullscreen;
    private Animation mShowAnim = AnimationUtils.loadAnimation(getContext(), R.anim.dkplayer_anim_alpha_in);
    private Animation mHideAnim = AnimationUtils.loadAnimation(getContext(), R.anim.dkplayer_anim_alpha_out);
    private ImageView rePlayButton;
    private FrameLayout fl_newprogress;
    private ProgressBar newprogressBar;
    private ImageView iv_jindui;
    private TextView iv_newtime;
    private long lastPosition;
    private ImageView voice_enable;
    private int streamVolume;
    private boolean isEnableVoice = true;
    private boolean isInProduct = true;


    public ProductVideoController(@NonNull Context context) {
        this(context, null);
    }

    public ProductVideoController(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProductVideoController(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dkplayer_layout_product_controller;
    }

    @Override
    protected void initView() {
        super.initView();
        mFullScreenButton = mControllerView.findViewById(R.id.fullscreen);
        mBottomContainer = mControllerView.findViewById(R.id.bottom_container);
        mTopContainer = mControllerView.findViewById(R.id.top_container);
        mVideoProgress = mControllerView.findViewById(R.id.seekBar);
        mTotalTime = mControllerView.findViewById(R.id.total_time);
        voice_enable = mControllerView.findViewById(R.id.voice_enable);
        mCurrTime = mControllerView.findViewById(R.id.curr_time);
        mBackButton = mControllerView.findViewById(R.id.back);
        mThumb = mControllerView.findViewById(R.id.thumb);
        mStartPlayButton = mControllerView.findViewById(R.id.start_play);
        mLoadingProgress = mControllerView.findViewById(R.id.loading);
        mBottomProgress = mControllerView.findViewById(R.id.bottom_progress);
        rePlayButton = mControllerView.findViewById(R.id.iv_replay);
        mStopFullscreen = mControllerView.findViewById(R.id.stop_fullscreen);
        mCompleteContainer = mControllerView.findViewById(R.id.complete_container);
        fl_newprogress = mControllerView.findViewById(R.id.fl_newprogress);
        newprogressBar = mControllerView.findViewById(R.id.newprogressBar);
        iv_jindui = mControllerView.findViewById(R.id.iv_jindui);
        iv_newtime = mControllerView.findViewById(R.id.iv_newtime);

        mFullScreenButton.setOnClickListener(this);
        mVideoProgress.setOnSeekBarChangeListener(this);
        mBackButton.setOnClickListener(this);
        mThumb.setOnClickListener(this);
        rePlayButton.setOnClickListener(this);
        mCompleteContainer.setOnClickListener(this);
        mStopFullscreen.setOnClickListener(this);
        voice_enable.setOnClickListener(this);
        if (isInProduct) {
            mFullScreenButton.setImageResource(R.drawable.commoditydetails_icon_big);
        } else {
            mFullScreenButton.setImageResource(R.drawable.dkplayer_selector_full_screen_button);
        }
        setVoice(isEnableVoice);
    }

    private void setVoice(boolean isEnable) {
        if (isEnable) {
            streamVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
        } else {
            mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, (int) streamVolume, 0);
        }
        voice_enable.setSelected(!isEnable);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.fullscreen) {
            if (isInProduct) {
                if (ProductMediaDetais != null) ProductMediaDetais.jumpProductMediaDetais();
            } else {
                doStartStopFullScreen();
            }
        } else if (i == R.id.back||i == R.id.stop_fullscreen) {
            doStartStopFullScreen();
        } else if (i == R.id.iv_play || i == R.id.thumb) {
            doPauseResume();
        } else if (i == R.id.iv_replay || i == R.id.iv_refresh) {
            mMediaPlayer.replay(true);
        } else if (i == R.id.voice_enable) {
            setVoice(voice_enable.isSelected());
        }
    }

    public void setDefaultVoiceEnable(boolean isEnable) {
        this.isEnableVoice = isEnable;
    }

    public void setDefaultisInProduct(boolean isInProduct) {
        this.isInProduct = isInProduct;
    }

    public ProductMediaDetais ProductMediaDetais;

    public void setProductMediaDetais(ProductVideoController.ProductMediaDetais productMediaDetais) {
        ProductMediaDetais = productMediaDetais;
    }

    public  interface ProductMediaDetais {
        void jumpProductMediaDetais();
    }

    @Override
    public void setPlayerState(int playerState) {
        switch (playerState) {
            case SuVideoView.PLAYER_NORMAL:
                L.e("PLAYER_NORMAL");
                setLayoutParams(new LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
                mIsGestureEnabled = false;
                mFullScreenButton.setSelected(false);
                mBackButton.setVisibility(GONE);
                mTopContainer.setVisibility(GONE);
                mStopFullscreen.setVisibility(GONE);
                break;
            case SuVideoView.PLAYER_FULL_SCREEN:
                L.e("PLAYER_FULL_SCREEN");
                mIsGestureEnabled = true;
                mFullScreenButton.setSelected(true);
                mBackButton.setVisibility(VISIBLE);

                mStopFullscreen.setVisibility(VISIBLE);
                if (mShowing) {
                    mTopContainer.setVisibility(VISIBLE);
                }
                break;
        }
    }

    @Override
    public void setPlayState(int playState) {
        super.setPlayState(playState);
        switch (playState) {
            case SuVideoView.STATE_IDLE:
                L.e("STATE_IDLE");
                hide();
                mMediaPlayer.setLock(false);
                mBottomProgress.setProgress(0);
                mBottomProgress.setSecondaryProgress(0);
                mVideoProgress.setProgress(0);
                mVideoProgress.setSecondaryProgress(0);
                mCompleteContainer.setVisibility(GONE);
                mBottomProgress.setVisibility(GONE);
                mLoadingProgress.setVisibility(GONE);
                fl_newprogress.setVisibility(GONE);
                mStartPlayButton.setVisibility(VISIBLE);
                mThumb.setVisibility(VISIBLE);
                break;
            case SuVideoView.STATE_PLAYING:
                L.e("STATE_PLAYING");
                post(mShowProgress);
                mStartPlayButton.setSelected(true);
                mLoadingProgress.setVisibility(GONE);
                mCompleteContainer.setVisibility(GONE);
                mThumb.setVisibility(GONE);
                mStartPlayButton.setVisibility(GONE);
                fl_newprogress.setVisibility(GONE);
                break;
            case SuVideoView.STATE_PAUSED:
                L.e("STATE_PAUSED");
                mStartPlayButton.setSelected(false);
                mStartPlayButton.setVisibility(VISIBLE);
                break;
            case SuVideoView.STATE_PREPARING:
                L.e("STATE_PREPARING");
                mCompleteContainer.setVisibility(GONE);
                mStartPlayButton.setVisibility(GONE);
                mLoadingProgress.setVisibility(VISIBLE);
//                mThumb.setVisibility(VISIBLE);
                break;
            case SuVideoView.STATE_PREPARED:
                L.e("STATE_PREPARED");
                mBottomProgress.setVisibility(VISIBLE);
//                mLoadingProgress.setVisibility(GONE);
                mStartPlayButton.setVisibility(GONE);
                break;
            case SuVideoView.STATE_ERROR:
                L.e("STATE_ERROR");
                mStartPlayButton.setVisibility(GONE);
                mLoadingProgress.setVisibility(GONE);
                fl_newprogress.setVisibility(GONE);

                mThumb.setVisibility(GONE);
                mBottomProgress.setVisibility(GONE);
                mTopContainer.setVisibility(GONE);
                break;
            case SuVideoView.STATE_BUFFERING:
                L.e("STATE_BUFFERING");
                mStartPlayButton.setVisibility(GONE);
                mLoadingProgress.setVisibility(VISIBLE);
                mThumb.setVisibility(GONE);
                mStartPlayButton.setSelected(mMediaPlayer.isPlaying());
                break;
            case SuVideoView.STATE_BUFFERED:
                L.e("STATE_BUFFERED");
                mLoadingProgress.setVisibility(GONE);
                mStartPlayButton.setVisibility(GONE);
                mThumb.setVisibility(GONE);
                mStartPlayButton.setSelected(mMediaPlayer.isPlaying());
                break;
            case SuVideoView.STATE_PLAYBACK_COMPLETED:
                L.e("STATE_PLAYBACK_COMPLETED");
                hide();
                removeCallbacks(mShowProgress);
                mStartPlayButton.setVisibility(GONE);
                mThumb.setVisibility(VISIBLE);
                mCompleteContainer.setVisibility(VISIBLE);
                mStopFullscreen.setVisibility(mMediaPlayer.isFullScreen() ? VISIBLE : GONE);
                mBottomProgress.setVisibility(GONE);
                mBottomProgress.setProgress(0);
                mBottomProgress.setSecondaryProgress(0);
                mMediaPlayer.setLock(false);
                break;
        }
    }


    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        mIsDragging = true;
        removeCallbacks(mShowProgress);
        removeCallbacks(mFadeOut);
    }

    @Override
    protected void MotionEventUp() {
        super.MotionEventUp();
        fl_newprogress.setVisibility(GONE);
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        long duration = mMediaPlayer.getDuration();
        long newPosition = (duration * seekBar.getProgress()) / mVideoProgress.getMax();
        mMediaPlayer.seekTo((int) newPosition);
        mIsDragging = false;
        post(mShowProgress);
        show();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (!fromUser) {
            return;
        }

        long duration = mMediaPlayer.getDuration();
        long newPosition = (duration * progress) / mVideoProgress.getMax();
        String currentTime = stringForTime((int) newPosition);
        String durationStr = stringForTime((int) duration);
        iv_newtime.setText(currentTime + "/" + durationStr);
        newprogressBar.setProgress(progress);
        if (mCurrTime != null)
            mCurrTime.setText(currentTime);
        fl_newprogress.setVisibility(View.VISIBLE);
        iv_jindui.setSelected((lastPosition - newPosition) > 0);
        lastPosition = newPosition;
    }


    @Override
    public void hide() {
        if (mShowing) {
            fl_newprogress.setVisibility(View.GONE);
            if (mMediaPlayer.isFullScreen()) {
                hideAllViews();
            } else {
                mBottomContainer.setVisibility(GONE);
                mBottomContainer.startAnimation(mHideAnim);
            }
            mBottomProgress.setVisibility(VISIBLE);
            mBottomProgress.startAnimation(mShowAnim);
            mShowing = false;
        }
    }

    private void hideAllViews() {
        mTopContainer.setVisibility(GONE);
        mTopContainer.startAnimation(mHideAnim);
        mBottomContainer.setVisibility(GONE);
        mBottomContainer.startAnimation(mHideAnim);
        fl_newprogress.setVisibility(GONE);
    }

    private void show(int timeout) {
        if (!mShowing) {
            fl_newprogress.setVisibility(View.GONE);
            if (mMediaPlayer.isFullScreen()) {
                showAllViews();
            } else {
                mBottomContainer.setVisibility(VISIBLE);
                mBottomContainer.startAnimation(mShowAnim);
            }
            mBottomProgress.setVisibility(GONE);
            mBottomProgress.startAnimation(mHideAnim);
            mShowing = true;
        }
        removeCallbacks(mFadeOut);
        if (timeout != 0) {
            postDelayed(mFadeOut, timeout);
        }
    }

    private void showAllViews() {
        mBottomContainer.setVisibility(VISIBLE);
        mBottomContainer.startAnimation(mShowAnim);
        mTopContainer.setVisibility(VISIBLE);
        mTopContainer.startAnimation(mShowAnim);
    }

    @Override
    public void show() {
        show(mDefaultTimeout);
    }

    @Override
    protected int setProgress() {
        if (mMediaPlayer == null || mIsDragging) {
            return 0;
        }
        int position = (int) mMediaPlayer.getCurrentPosition();
        int duration = (int) mMediaPlayer.getDuration();
        if (mVideoProgress != null) {
            if (duration > 0) {
                mVideoProgress.setEnabled(true);
                int pos = (int) (position * 1.0 / duration * mVideoProgress.getMax());
                mVideoProgress.setProgress(pos);
                newprogressBar.setProgress(pos);
                mBottomProgress.setProgress(pos);
            } else {
                mVideoProgress.setEnabled(false);
            }
            int percent = mMediaPlayer.getBufferedPercentage();
            if (percent >= 95) { //解决缓冲进度不能100%问题
                mVideoProgress.setSecondaryProgress(mVideoProgress.getMax());
                mBottomProgress.setSecondaryProgress(mBottomProgress.getMax());
            } else {
                mVideoProgress.setSecondaryProgress(percent * 10);
                mBottomProgress.setSecondaryProgress(percent * 10);
            }
        }
        String currentTime = stringForTime(position);
        String durationStr = stringForTime(duration);
        iv_newtime.setText(currentTime + "/" + durationStr);
        if (mTotalTime != null)
            mTotalTime.setText(durationStr);
        if (mCurrTime != null)
            mCurrTime.setText(currentTime);

        return position;
    }

    @Override
    protected void slideToChangeVolume(float deltaY) {
    }

    @Override
    protected void slideToChangeBrightness(float deltaY) {
    }

    public void slideToChangePosition(float deltaX) {
        try {
            hide();
            mCenterView.setProVisibility(View.GONE);
            deltaX = -deltaX;
            int width = getMeasuredWidth();
            int duration = (int) mMediaPlayer.getDuration();
            if (duration < 0) return;
            int currentPosition = (int) mMediaPlayer.getCurrentPosition();
            int position = (int) (deltaX / width * 120000 + currentPosition);
            if (position > duration) position = duration;
            if (position < 0) position = 0;
            mPosition = position;
            int progress = position * 1000 / duration;
            newprogressBar.setProgress(progress);
            iv_newtime.setText(stringForTime(position) + "/" + stringForTime(duration));
            mTopContainer.setVisibility(View.VISIBLE);
            fl_newprogress.setVisibility(View.VISIBLE);
            iv_jindui.setSelected((lastPosition - mPosition) > 0);
            lastPosition = mPosition;
            mNeedSeek = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ImageView getThumb() {
        return mThumb;
    }

    @Override
    public boolean onBackPressed() {

        Activity activity = PlayerUtils.scanForActivity(getContext());
        if (activity == null) return super.onBackPressed();

        if (mMediaPlayer.isFullScreen()) {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            mMediaPlayer.stopFullScreen();
            return true;
        }
        return super.onBackPressed();
    }
}
