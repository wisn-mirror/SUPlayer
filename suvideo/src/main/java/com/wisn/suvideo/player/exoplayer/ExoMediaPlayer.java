package com.wisn.suvideo.player.exoplayer;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.view.Surface;
import android.view.SurfaceHolder;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ext.rtmp.RtmpDataSourceFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.exoplayer2.source.dash.DefaultDashChunkSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.source.smoothstreaming.DefaultSsChunkSource;
import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.upstream.cache.CacheDataSourceFactory;
import com.google.android.exoplayer2.upstream.cache.LeastRecentlyUsedCacheEvictor;
import com.google.android.exoplayer2.upstream.cache.SimpleCache;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.VideoListener;
import com.wisn.suvideo.R;
import com.wisn.suvideo.player.AbstractPlayer;

import java.io.File;
import java.util.Map;

/**
 * Created by Wisn on 2019-07-23 18:51.
 */
public class ExoMediaPlayer extends AbstractPlayer implements VideoListener, Player.EventListener {

    private Context mAppContext;
    private SimpleExoPlayer mInternalPlayer;
    private MediaSource mMediaSource;
    private String mDataSource;
    private Surface mSurface;
    private PlaybackParameters mSpeedPlaybackParameters;
    private int lastReportedPlaybackState;
    private boolean lastReportedPlayWhenReady;
    private boolean mIsPreparing;
    private boolean mIsBuffering;
    private DataSource.Factory mediaDataSourceFactory;
    private Map<String, String> mHeaders;
    private static final DefaultBandwidthMeter BANDWIDTH_METER = new DefaultBandwidthMeter();

    public ExoMediaPlayer(Context context) {
        mAppContext = context.getApplicationContext();
        lastReportedPlaybackState = Player.STATE_IDLE;
//        mediaDataSourceFactory = getDataSourceFactory(true);
        mediaDataSourceFactory = getCacheDataSourceFactory();
    }

    @Override
    public void initPlayer() {
        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveTrackSelection.Factory();
        DefaultRenderersFactory renderersFactory =
                new DefaultRenderersFactory(mAppContext, DefaultRenderersFactory.EXTENSION_RENDERER_MODE_PREFER);
        DefaultTrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);
        mInternalPlayer = ExoPlayerFactory.newSimpleInstance(mAppContext,renderersFactory, trackSelector);
        mInternalPlayer.addListener(this);
        mInternalPlayer.addVideoListener(this);
    }

    @Override
    public void setDataSource(String path, Map<String, String> headers) {
        mDataSource = path;
        mMediaSource = getMediaSource();
        mHeaders = headers;
    }

    @Override
    public void setDataSource(AssetFileDescriptor fd) {
        //no support
    }

    private MediaSource getMediaSource() {
        Uri contentUri = Uri.parse(mDataSource);
        if ("rtmp".equals(contentUri.getScheme())) {
            RtmpDataSourceFactory rtmpDataSourceFactory = new RtmpDataSourceFactory(null);
            return new ExtractorMediaSource.Factory(rtmpDataSourceFactory)
                    .createMediaSource(contentUri);
        }
        int contentType = Util.inferContentType(mDataSource);
        switch (contentType) {
            case C.TYPE_DASH:
                return new DashMediaSource.Factory(
                        new DefaultDashChunkSource.Factory(getDataSourceFactory(true)),
                        getDataSourceFactory(false))
                        .createMediaSource(contentUri);
            case C.TYPE_SS:
                return new SsMediaSource.Factory(
                        new DefaultSsChunkSource.Factory(getDataSourceFactory(true)),
                        getDataSourceFactory(false))
                        .createMediaSource(contentUri);
            case C.TYPE_HLS:
                return new HlsMediaSource.Factory( getDataSourceFactory(true))
                        .createMediaSource(contentUri);
            default:
            case C.TYPE_OTHER:
                return new ExtractorMediaSource.Factory(mediaDataSourceFactory)
                        .createMediaSource(contentUri);
        }
    }

    private DataSource.Factory getCacheDataSourceFactory() {
        DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(mAppContext,
                Util.getUserAgent(mAppContext, mAppContext.getString(R.string.app_name)));
//        File cacheFile = new File(mAppContext.getExternalCacheDir().getAbsolutePath(), "video");
//        // 本地最多保存512M, 按照LRU原则删除老数据
//        simpleCache = new SimpleCache(cacheFile, new LeastRecentlyUsedCacheEvictor(1024 * 1024 *     1024));
        CacheHelper.getSimpleCache(mAppContext.getExternalCacheDir().getAbsolutePath());
         return new CacheDataSourceFactory(CacheHelper.simpleCache,    dataSourceFactory);
    }

    /**
     * Returns a new DataSource factory.
     *
     * @param useBandwidthMeter Whether to set {@link #BANDWIDTH_METER} as a listener to the new
     *                          DataSource factory.
     * @return A new DataSource factory.
     */
    private DataSource.Factory getDataSourceFactory(boolean useBandwidthMeter) {
        return new DefaultDataSourceFactory(mAppContext, useBandwidthMeter ? null : BANDWIDTH_METER,
                getHttpDataSourceFactory(useBandwidthMeter));
    }

    /**
     * Returns a new HttpDataSource factory.
     *
     * @param useBandwidthMeter Whether to set {@link #BANDWIDTH_METER} as a listener to the new
     *                          DataSource factory.
     * @return A new HttpDataSource factory.
     */
    private DataSource.Factory getHttpDataSourceFactory(boolean useBandwidthMeter) {
        DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory(
                Util.getUserAgent(mAppContext, mAppContext.getApplicationInfo().name),
                useBandwidthMeter ? null : BANDWIDTH_METER,
                DefaultHttpDataSource.DEFAULT_CONNECT_TIMEOUT_MILLIS,
                DefaultHttpDataSource.DEFAULT_READ_TIMEOUT_MILLIS,
                true);
        if (mHeaders != null && mHeaders.size() > 0) {
            for (Map.Entry<String, String> header : mHeaders.entrySet()) {
                dataSourceFactory.getDefaultRequestProperties().set(header.getKey(), header.getValue());
            }
        }
        return dataSourceFactory;
    }

    @Override
    public void start() {
        if (mInternalPlayer == null)
            return;
        mInternalPlayer.setPlayWhenReady(true);
    }

    @Override
    public void pause() {
        if (mInternalPlayer == null)
            return;
        mInternalPlayer.setPlayWhenReady(false);
    }

    @Override
    public void stop() {
        if (mInternalPlayer == null)
            return;
        mInternalPlayer.stop();
    }

    @Override
    public void prepareAsync() {
        if (mMediaSource == null) return;
        if (mSpeedPlaybackParameters != null) {
            mInternalPlayer.setPlaybackParameters(mSpeedPlaybackParameters);
        }
        if (mSurface != null) {
            mInternalPlayer.setVideoSurface(mSurface);
        }
        mIsPreparing = true;
        mInternalPlayer.prepare(mMediaSource);
        mInternalPlayer.setPlayWhenReady(true);
    }

    @Override
    public void reset() {
        release();
        initPlayer();
    }

    @Override
    public boolean isPlaying() {
        if (mInternalPlayer == null)
            return false;
        int state = mInternalPlayer.getPlaybackState();
        switch (state) {
            case Player.STATE_BUFFERING:
            case Player.STATE_READY:
                return mInternalPlayer.getPlayWhenReady();
            case Player.STATE_IDLE:
            case Player.STATE_ENDED:
            default:
                return false;
        }
    }

    @Override
    public void seekTo(long time) {
        if (mInternalPlayer == null)
            return;
        mInternalPlayer.seekTo(time);
    }

    @Override
    public void release() {
        try {
            if (mInternalPlayer != null) {
                mInternalPlayer.release();
              if(CacheHelper.simpleCache!=null)  CacheHelper.simpleCache.release();
                mInternalPlayer.removeListener(this);
                mInternalPlayer.removeVideoListener(this);
                mInternalPlayer = null;
            }
            CacheHelper.simpleCache=null;
            mSurface = null;
            mDataSource = null;
            mHeaders = null;
            mIsPreparing = false;
            mIsBuffering = false;
            lastReportedPlaybackState = Player.STATE_IDLE;
            lastReportedPlayWhenReady = false;
            mSpeedPlaybackParameters = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public long getCurrentPosition() {
        if (mInternalPlayer == null)
            return 0;
        return mInternalPlayer.getCurrentPosition();
    }

    @Override
    public long getDuration() {
        if (mInternalPlayer == null)
            return 0;
        return mInternalPlayer.getDuration();
    }

    @Override
    public int getBufferedPercentage() {
        return mInternalPlayer == null ? 0 : mInternalPlayer.getBufferedPercentage();
    }

    @Override
    public void setSurface(Surface surface) {
        mSurface = surface;
        if (mInternalPlayer != null) {
            mInternalPlayer.setVideoSurface(surface);
        }
    }

    @Override
    public void setDisplay(SurfaceHolder holder) {
        if (holder == null)
            setSurface(null);
        else
            setSurface(holder.getSurface());
    }

    @Override
    public void setVolume(float leftVolume, float rightVolume) {
        if (mInternalPlayer != null)
            mInternalPlayer.setVolume((leftVolume + rightVolume) / 2);
    }

    @Override
    public void setLooping(boolean isLooping) {
        if (mInternalPlayer != null)
            mInternalPlayer.setRepeatMode(isLooping ? Player.REPEAT_MODE_ALL : Player.REPEAT_MODE_OFF);
    }

    @Override
    public void setEnableMediaCodec(boolean isEnable) {
        // no support
    }

    @Override
    public void setOptions() {
        // no support
    }

    @Override
    public void setSpeed(float speed) {
        PlaybackParameters playbackParameters = new PlaybackParameters(speed);
        mSpeedPlaybackParameters = playbackParameters;
        if (mInternalPlayer != null) {
            mInternalPlayer.setPlaybackParameters(playbackParameters);
        }
    }

    @Override
    public long getTcpSpeed() {
        // no support
        return 0;
    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        if (lastReportedPlayWhenReady != playWhenReady || lastReportedPlaybackState != playbackState) {
            switch (playbackState) {
                case Player.STATE_READY:
                    if (mIsPreparing) {
                        if (mPlayerEventListener != null) {
                            mPlayerEventListener.onPrepared();
                            mPlayerEventListener.onInfo(MEDIA_INFO_VIDEO_RENDERING_START, 0);
                        }
                        mIsPreparing = false;
                    } else if (mIsBuffering) {
                        if (mPlayerEventListener != null) {
                            mPlayerEventListener.onInfo(MEDIA_INFO_BUFFERING_END, mInternalPlayer.getBufferedPercentage());
                        }
                        mIsBuffering = false;
                    }
                    break;
                case Player.STATE_BUFFERING:
                    if (!mIsPreparing) {
                        if (mPlayerEventListener != null) {
                            mPlayerEventListener.onInfo(MEDIA_INFO_BUFFERING_START, mInternalPlayer.getBufferedPercentage());
                        }
                        mIsBuffering = true;
                    }
                    break;
                case Player.STATE_ENDED:
                    if (mPlayerEventListener != null) {
                        mPlayerEventListener.onCompletion();
                    }
                    break;
            }
        }
        lastReportedPlayWhenReady = playWhenReady;
        lastReportedPlaybackState = playbackState;
    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {
        if (mPlayerEventListener != null) {
            mPlayerEventListener.onError();
        }
    }

    @Override
    public void onVideoSizeChanged(int width, int height, int unappliedRotationDegrees, float pixelWidthHeightRatio) {
        if (mPlayerEventListener != null) {
            mPlayerEventListener.onVideoSizeChanged(width, height);
            if (unappliedRotationDegrees > 0) {
                mPlayerEventListener.onInfo(MEDIA_INFO_VIDEO_ROTATION_CHANGED, unappliedRotationDegrees);
            }
        }
    }
}
