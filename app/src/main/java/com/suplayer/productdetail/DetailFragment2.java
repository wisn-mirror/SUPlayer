package com.suplayer.productdetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.suplayer.Constants;
import com.suplayer.R;
import com.suplayer.productdetail.media.ProductMediaActivity;
import com.wisn.suvideo.SuVideoView;
import com.wisn.suvideo.base.BaseFragment;
import com.wisn.suvideo.control.impl.FloatController;
import com.wisn.suvideo.control.impl.ProductVideoController;
import com.wisn.suvideo.helper.L;
import com.wisn.suvideo.listener.OnVideoViewStateChangeListener;
import com.wisn.suvideo.manager.VideoViewManager;
import com.wisn.suvideo.view.LyfScrollView;
import com.wisn.suvideo.view.banner.Banner;
import com.wisn.suvideo.view.banner.BannerConfig;
import com.wisn.suvideo.view.banner.BannerViewHolder;
import com.wisn.suvideo.view.banner2.BannerData;

import net.lucode.hackware.magicindicator.buildins.UIUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wisn on 2019-07-26 17:29.
 */
public class DetailFragment2 extends BaseFragment {
    private SuVideoView mVideoView;
    private LyfScrollView containerRoot;
    private ProductVideoController productVideoController;
    private FloatController mFloatController;
    private boolean isXiaopin;
    private FrameLayout viewContent;
    private boolean playing;
    private Banner banner_slider;
    private VideoViewManager mVideoViewManager;
    public static final String tag = "DetailFragment2";
    private FrameLayout videoContent;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(tag, "onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(tag, "onCreate");

    }


    @Override
    protected void initView(View view) {
        containerRoot = view.findViewById(R.id.container);
        banner_slider = view.findViewById(R.id.banner_slider);
        viewContent = view.findViewById(R.id.viewContent);

        mVideoViewManager = VideoViewManager.instance();
        List<BannerData> bannerData = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            BannerData bannerData1 = new BannerData();
            if (i == 0) {
                bannerData1.type = -1;
            }
            bannerData1.url = Constants.res[i];
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

    @Override
    public int bindLayout() {
        return R.layout.fragment_detail2;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(tag, "onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(tag, "onViewCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(tag, "onStart");
         if (videoContent != null) {
//             mVideoView = SeamlessPlayerHelper.getInstance(getActivity()).getSuVideoView();
////             removePlayerFormParent();
//////             videoContent.removeAllViews();
////             videoContent.addView(mVideoView);
//             productVideoController.setPlayState(mVideoView.getCurrentPlayState());
//             productVideoController.setPlayerState(mVideoView.getCurrentPlayerState());
//             mVideoView.setVideoController(productVideoController);
//             try {
////                                    ((ViewGroup) mVideoView.getParent()).removeView(mVideoView);
//                 removePlayerFormParent();
//             } catch (Exception e) {
//                 e.printStackTrace();
//             }
//             videoContent.addView(mVideoView);
            /*if (isXiaopin) {
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
                viewContent.setVisibility(View.GONE);
                mVideoView.stopTinyScreen(viewContent);
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        productVideoController.setPlayState(mVideoView.getCurrentPlayState());
                        productVideoController.setPlayerState(mVideoView.getCurrentPlayerState());
                        mVideoView.setVideoController(productVideoController);
                        try {
//                                    ((ViewGroup) mVideoView.getParent()).removeView(mVideoView);
                            removePlayerFormParent();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        videoContent.addView(mVideoView);
                    }
                }, 20);

            }*/
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(tag, "onResume");
        if (needResume) {
            mVideoViewManager.resume();
        }
    }

    public boolean needResume = false;

    @Override
    public void onPause() {
        super.onPause();
        Log.d(tag, "onPause");
        if (mVideoView != null && mVideoView.isPlaying()) {
            mVideoViewManager.pause();
            needResume = true;
        } else {
            needResume = false;
        }

       /* if (mVideoView != null) {
            currentPlayState = mVideoView.getCurrentPlayState();
            currentPlayerState = mVideoView.getCurrentPlayerState();
            mVideoView.pause();
        }*/
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(tag, "onStop");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(tag, "onDestroyView");

    }

    @Override
    public void onDestroy() {
        if (mVideoView != null) mVideoView.release();
        super.onDestroy();
        Log.d(tag, "onDestroy");

    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(tag, "onDetach");

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
        mVideoView = SeamlessPlayerHelper.getInstance(getActivity()).getSuVideoView();
//        mVideoView = new SuVideoView(getActivity());
        removePlayerFormParent();
        mVideoView.addOnVideoViewStateChangeListener(mOnVideoViewStateChangeListener);

        if (mVideoView.isTinyScreen()) mVideoView.stopTinyScreen();
//        mVideoView.release();
        mVideoView.setUrl(Constants.VOD_URL);
        productVideoController = new ProductVideoController(getContext());
        productVideoController.setDefaultVoiceEnable(false);
        productVideoController.setProductMediaDetais(new ProductVideoController.ProductMediaDetais() {
            @Override
            public void jumpProductMediaDetais() {
                playing = mVideoView.isPlaying();
                getActivity().startActivity(new Intent(getActivity(), ProductMediaActivity.class));
                getActivity().overridePendingTransition(0, 0);
                videoContent = frameLayout.fl_content;
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
//                                    ((ViewGroup) mVideoView.getParent()).removeView(mVideoView);
                                    removePlayerFormParent();
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

    /**
     * 将播放器从父控件中移除
     */
    private void removePlayerFormParent() {
        ViewParent parent = mVideoView.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(mVideoView);
        }
    }

}
