package com.suplayer.productdetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.suplayer.R;
import com.suplayer.Url;
import com.suplayer.productdetail.media.ProductMediaActivity;
import com.wisn.suvideo.SuVideoView;
import com.wisn.suvideo.control.impl.FloatController;
import com.wisn.suvideo.control.impl.ProductVideoController;
import com.wisn.suvideo.helper.L;
import com.wisn.suvideo.listener.OnVideoViewStateChangeListener;
import com.wisn.suvideo.manager.VideoViewManager;
import com.wisn.suvideo.view.LyfScrollView;
import com.wisn.suvideo.view.banner.Banner;
import com.wisn.suvideo.view.banner.BannerConfig;
import com.wisn.suvideo.view.banner.BannerViewHolder;
import com.wisn.suvideo.view.banner.Transformer;
import com.wisn.suvideo.view.banner2.BannerData;

import net.lucode.hackware.magicindicator.buildins.UIUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wisn on 2019-07-26 17:29.
 */
public class DetailFragment2 extends Fragment {
    private SuVideoView mVideoView;
    private LyfScrollView containerRoot;
    private ProductVideoController productVideoController;
    private FloatController mFloatController;
    private boolean isXiaopin;
    private FrameLayout viewContent;
    private boolean playing;
    private Banner banner_slider;
    private VideoViewManager mVideoViewManager;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail2, null);
        containerRoot = view.findViewById(R.id.container);
        banner_slider = view.findViewById(R.id.banner_slider);
        viewContent = view.findViewById(R.id.viewContent);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mVideoViewManager = VideoViewManager.instance();

        List<BannerData> bannerData = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            BannerData bannerData1 = new BannerData();
            if (i == 0) {
                bannerData1.type = -1;
            }
            bannerData1.url = Url.res[i];
            bannerData.add(bannerData1);
        }

        CustomViewHolder2 creator = new CustomViewHolder2();
        creator.setOnStartCallback(new CustomViewHolder2.OnStartCallback() {
            @Override
            public void startPlay(CustomViewHolder2.ViewHolder frameLayout) {
                dealVideo(frameLayout);
            }
        });
        banner_slider.setAutoPlay(false)
                .setDelayTime(5000)
                .setLoop(false)
                .setPages(bannerData, creator)
                .setCurrentPage(0)
                .setBannerStyle(BannerConfig.NOT_INDICATOR)
//                .setBannerAnimation(Transformer.Scale)
                .start();
        banner_slider.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (i != 0) {
                    mVideoViewManager.pause();
                } else {
                    mVideoViewManager.resume();
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    public static class CustomViewHolder2 implements BannerViewHolder<BannerData> {

        private OnStartCallback onStartCallback;
        private View view;

        public interface OnStartCallback {
            void startPlay(ViewHolder viewHolder);
        }

        public void setOnStartCallback(OnStartCallback onStartCallback) {
            this.onStartCallback = onStartCallback;
        }

        @Override
        public View createView(Context context) {
            view = LayoutInflater.from(context).inflate(R.layout.item_productdetail_media2, null);
            ViewHolder viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
            return view;
        }

        @Override
        public void onBind(Context context, int position, BannerData data) {
            ViewHolder tag = (ViewHolder) view.getTag();
            Glide.with(context)
                    .load(data.url)
                    .into(tag.iv_target);
            if (data.type == -1) {
                tag.start_play.setVisibility(View.VISIBLE);
                tag.start_play.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            if (onStartCallback != null) {
                                onStartCallback.startPlay(tag);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            } else {
                tag.start_play.setVisibility(View.GONE);
            }
        }

        public static class ViewHolder {
            public ImageView iv_target;
            public ImageView start_play;
            public FrameLayout fl_content;

            public ViewHolder(View view) {
                try {
                    iv_target = (ImageView) view.findViewById(R.id.iv_target);
                    fl_content = (FrameLayout) view.findViewById(R.id.fl_content);
                    start_play = (ImageView) view.findViewById(R.id.start_play);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void isPlay(boolean isPlay) {
                if (isPlay) {
                    iv_target.setVisibility(View.GONE);
                    fl_content.setVisibility(View.VISIBLE);
                    start_play.setVisibility(View.GONE);
                } else {
                    iv_target.setVisibility(View.VISIBLE);
                    fl_content.setVisibility(View.GONE);
                    start_play.setVisibility(View.VISIBLE);
                }
            }
        }


    }

    private void dealVideo(CustomViewHolder2.ViewHolder frameLayout) {

        OnVideoViewStateChangeListener mOnVideoViewStateChangeListener = new OnVideoViewStateChangeListener() {
            @Override
            public void onPlayerStateChanged(int playerState) {
                switch (playerState) {
                    case SuVideoView.PLAYER_NORMAL://小屏
                        break;
                    case SuVideoView.PLAYER_FULL_SCREEN://全屏
                        break;
                }
            }

            @Override
            public void onPlayStateChanged(int playState) {
                switch (playState) {
                    case SuVideoView.STATE_IDLE:
                        L.d("details STATE_IDLE：");

                        break;
                    case SuVideoView.STATE_PREPARING:
                        L.d("details STATE_PREPARING：");

                        break;
                    case SuVideoView.STATE_PREPARED:
                        L.d("details STATE_PREPARED：");

                        //需在此时获取视频宽高
                        int[] videoSize = mVideoView.getVideoSize();
                        L.d("details 视频宽：" + videoSize[0]);
                        L.d("details视频高：" + videoSize[1]);
                        frameLayout.isPlay(true);
                        break;
                    case SuVideoView.STATE_PLAYING:
                        L.d("details STATE_PLAYING：");
                        break;
                    case SuVideoView.STATE_PAUSED:
                        L.d("details STATE_PAUSED：");

                        break;
                    case SuVideoView.STATE_BUFFERING:
                        L.d("details STATE_BUFFERING：");

                        break;
                    case SuVideoView.STATE_BUFFERED:
                        L.d("details STATE_BUFFERED：");

                        break;
                    case SuVideoView.STATE_PLAYBACK_COMPLETED:
                        L.d("details STATE_PLAYBACK_COMPLETED：");
                        frameLayout.isPlay(false);
                        break;
                    case SuVideoView.STATE_ERROR:
                        L.d("details STATE_ERROR：");

                        break;
                }
            }
        };
        //播放raw
        mVideoView = new SuVideoView(getActivity());
        mVideoView.addOnVideoViewStateChangeListener(mOnVideoViewStateChangeListener);

        if (mVideoView.isTinyScreen()) mVideoView.stopTinyScreen();
        mVideoView.release();
        mVideoView.setUrl(Url.VOD_URL);
        productVideoController = new ProductVideoController(getContext());
        productVideoController.setDefaultVoiceEnable(false);
        productVideoController.setProductMediaDetais(new ProductVideoController.ProductMediaDetais() {
            @Override
            public void jumpProductMediaDetais() {
                playing = mVideoView.isPlaying();
                getActivity().startActivity(new Intent(getActivity(), ProductMediaActivity.class));
                getActivity().overridePendingTransition(0, 0);
            }
        });
        mVideoView.setVideoController(productVideoController);
        mVideoView.start();
        frameLayout.fl_content.addView(mVideoView);
        mFloatController = new FloatController(getContext());
        int dip130 = UIUtil.dip2px(getContext(), 300);
        containerRoot.setScrollListener(new LyfScrollView.ScrollViewListener() {

            @Override
            public void onScrollChanged(LyfScrollView scrollView, int x, int y, int oldx, int oldy) {
                L.e("onScrollChanged x:" + x + " y:" + y + " oldx:" + oldx + " oldy:" + oldy);
                if (y > dip130) {
                    if (isXiaopin) return;
                    isXiaopin = true;
                    viewContent.setVisibility(View.VISIBLE);
                    mVideoView.startTinyScreen(true, viewContent);
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mFloatController.setPlayState(mVideoView.getCurrentPlayState());
                            mFloatController.setPlayerState(mVideoView.getCurrentPlayerState());
                            mVideoView.setVideoController(mFloatController);
                        }
                    }, 20);

                } else {
                    if (isXiaopin) {
                        isXiaopin = false;
                        viewContent.setVisibility(View.GONE);
                        mVideoView.stopTinyScreen(viewContent);
                        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                productVideoController.setPlayState(mVideoView.getCurrentPlayState());
                                productVideoController.setPlayerState(mVideoView.getCurrentPlayerState());
                                mVideoView.setVideoController(productVideoController);
                                try {
                                    ((ViewGroup) mVideoView.getParent()).removeView(mVideoView);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                frameLayout.fl_content.addView(mVideoView);
                            }
                        }, 20);

                    }
                }
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
       /* if (mVideoView != null && !mVideoView.isPlaying()) {
            if (playing) {
//                productVideoController.setPlayState(currentPlayState);
//                productVideoController.setPlayerState(currentPlayState);
//                mVideoView.setVideoController(productVideoController);
                mVideoView.start();
            }
        }*/
    }

    @Override
    public void onPause() {
        super.onPause();
       /* if (mVideoView != null) {
            currentPlayState = mVideoView.getCurrentPlayState();
            currentPlayerState = mVideoView.getCurrentPlayerState();
            mVideoView.pause();
        }*/

    }

    @Override
    public void onDestroyView() {
        if (mVideoView != null) mVideoView.release();
        super.onDestroyView();
    }

}
