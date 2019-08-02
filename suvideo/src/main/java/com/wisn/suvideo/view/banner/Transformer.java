package com.wisn.suvideo.view.banner;

import android.support.v4.view.ViewPager.PageTransformer;

import com.wisn.suvideo.view.banner.transformer.ScaleTransformer;


public class Transformer {

    public static Class<? extends PageTransformer> Scale = ScaleTransformer.class;

}
