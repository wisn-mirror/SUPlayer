package com.suplayer.productdetail.media;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.github.chrisbanes.photoview.PhotoView;
import com.suplayer.R;
import com.suplayer.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wisn on 2019-07-30 14:29.
 */
public class MedialImageFragment extends Fragment {

    private ViewPager viewpager;
    private TextView tv_index;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.productdetail_fragment_image, null);
        viewpager = view.findViewById(R.id.viewpager);
        tv_index = view.findViewById(R.id.tv_index);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<String> urls = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            urls.add(Constants.res[i]);
        }
        viewpager.setAdapter(new SamplePagerAdapter(urls));
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                tv_index.setText((i + 1) + "/" + urls.size());
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });

    }

    class SamplePagerAdapter extends PagerAdapter {

        private List<String> sDrawables;

        public SamplePagerAdapter(List<String> urls) {
            // TODO Auto-generated constructor stub
            sDrawables = urls;
        }

        @Override
        public int getCount() {
            return sDrawables.size();
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            PhotoView photoView = new PhotoView(container.getContext());
            // photoView.setImageResource(sDrawables[position]);

           /* photoView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {

                @Override
                public void onPhotoTap(View view, float x, float y) {
                    // TODO Auto-generated method stub
//                    MedialImageFragment.this.finish();
                }
            });*/
            String sdr = sDrawables.get(position);
            RequestOptions options = new RequestOptions()
                    .centerCrop()
//                    .placeholder(R.drawable.lib_replactimage)
//                    .error(R.drawable.lib_replactimage)
                    .override(400, 400)
//                    .fallback(R.drawable.lib_replactimage)
                    .diskCacheStrategy(DiskCacheStrategy.ALL);
//            GlideUtil.load(ViewPagerActivity.this,options,sdr,0,0,photoView);
            Glide.with(getContext())
                    .load(sdr)
                    .apply(options).into(photoView);
            // Now just add PhotoView to ViewPager and return it
            container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

            return photoView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

    }
}
