package com.wisn.suvideo.view.banner2;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wisn on 2019-08-02 14:34.
 */
public class Banner extends FrameLayout {

    private AutoScrollViewPager vp_scroll;
    private PagerAdapter adapter;
    MyHandler handler;
    private List<ImageView> allImageView;
    private Context mContext;
    public List<BannerData> data;

    public Banner(Context context) {
        super(context);
        mContext=context;

    }

    public Banner(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext=context;

    }

    public Banner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext=context;
    }

    public void setData(List<BannerData> data) {
        this.data = data;
        initView(mContext);
    }

    protected void initView(Context context) {
        this.mContext = context;
        if (data == null) ;
        handler = new MyHandler(this);
        vp_scroll = new AutoScrollViewPager(context);
//        GlideApp.lo
        allImageView = new ArrayList<>();
        //最后一个添加到第一个
        ImageView imageViewone = new ImageView(mContext);
        imageViewone.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        Glide.with(this)
                .load(data.get(data.size() - 1))
                .into(imageViewone);
        allImageView.add(imageViewone);
        for (int i = 0; i < 4; i++) {
            ImageView imageView = new ImageView(mContext);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            Glide.with(this)
                    .load(data.get(i).url)
                    .into(imageView);
            allImageView.add(imageView);
        }
        //第一个添加到最后一个
        ImageView imageViewlast = new ImageView(mContext);
        imageViewlast.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        Glide.with(this)
                .load(data.get(0))
                .into(imageViewlast);
        allImageView.add(imageViewlast);
        int widthPixels = getScreenWidth();
        adapter = new PagerAdapter() {

            @Override
            public int getCount() {
                return allImageView.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
                return view == o;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView((View) object);
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
//                return super.instantiateItem(container, position);
                ImageView imageView = allImageView.get(position);
                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                if (getCount() == 1) {
                    params.width = (int) widthPixels;
                } else {
                    if (position == 0) {
                        //第一个 //若超过一个活动，单个item屏幕90%比例
                        params.width = (int) (widthPixels / 10 * 8.8);
//                        mMarginLeft = px_margin_dp;
//                        mMarginRight = px_margin_dp_helf;

                    } else if (position == getCount() - 1) {
                        //最后一个
                        params.width = (int) (widthPixels / 10 * 8.8);
//                        mMarginLeft = px_margin_dp_helf;
//                        mMarginRight = px_margin_dp;
                    } else {
                        //中间的
                        params.width = (int) (widthPixels / 10 * 8);
//                        mMarginLeft = px_margin_dp_helf;
//                        mMarginRight = px_margin_dp_helf;

                    }
                }
                imageView.setLayoutParams(params);
                container.addView(imageView);
                return imageView;
            }

        };
        vp_scroll.setAdapter(adapter);
        //修改滑动速度
        final MyScroller myScroller = new MyScroller(mContext);
        myScroller.setmDuration(2000);
        myScroller.acttchToViewPager(vp_scroll);
        vp_scroll.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(final int position) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (position == allImageView.size() - 1) {
                            vp_scroll.setCurrentItem(1, false);//不要动画
                        } else if (position == 0) {
                            vp_scroll.setCurrentItem(allImageView.size() - 2, false);//不要动画
                        }
                    }
                }, myScroller.getmDuration());
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        vp_scroll.setCurrentItem(1, false);
        handler.sendEmptyMessageDelayed(100, 2000);
        LayoutParams params = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        this.removeAllViews();
        //将播放器视图添加到当前FrameLayout中即退出了全屏
        this.addView(vp_scroll, params);
    }

    private int getScreenWidth() {
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();// 创建了一张白纸
        windowManager.getDefaultDisplay().getMetrics(outMetrics);// 给白纸设置宽高
        return outMetrics.widthPixels;
    }

    public void nextPage() {
        if (!vp_scroll.isTouch) {
            int index = (vp_scroll.getCurrentItem() + 1) % allImageView.size();
            vp_scroll.setCurrentItem(index);
        }

    }

    static class MyHandler extends Handler {
        WeakReference<Banner> imageSlideAcitivityWeakReference;

        public MyHandler(Banner banner) {
            imageSlideAcitivityWeakReference = new WeakReference<Banner>(banner);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Banner imageSlideAcitivity = imageSlideAcitivityWeakReference.get();
            if (imageSlideAcitivity != null) {
                if (msg.what == 100) {
                    imageSlideAcitivity.nextPage();
                    this.sendEmptyMessageDelayed(100, 2000);
                }

            }
        }
    }

}
