package com.suplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.suplayer.douyin.DouYinActivity;
import com.suplayer.productdetail.ProdetailActivity;
import com.suplayer.productdetail.media.ProductMediaActivity;
import com.suplayer.xiaohongshu.HongshuActivity;
import com.wisn.suvideo.VideoViewConfig;
import com.wisn.suvideo.manager.VideoViewManager;
import com.wisn.suvideo.player.exoplayer.ExoMediaPlayerFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button videolist;
    private Button productDetail;
    private Button Simple;
    private Button douyin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videolist = findViewById(R.id.videolist);
        productDetail = findViewById(R.id.productDetail);
        Simple = findViewById(R.id.Simple);
        douyin = findViewById(R.id.douyin);
        videolist.setOnClickListener(this);
        productDetail.setOnClickListener(this);
        Simple.setOnClickListener(this);
        douyin.setOnClickListener(this);
        initconfig();
        startActivity(new Intent(this, ProdetailActivity.class));

    }

    private void initconfig() {
        VideoViewConfig build = VideoViewConfig.newBuilder().setPlayerFactory(ExoMediaPlayerFactory.create(this)).build();
        VideoViewManager.setConfig(build);
    }

    @Override
    public void onClick(View v) {
        if (v == Simple) {
            startActivity(new Intent(this, SimpleActivity.class));
        } else if (v == videolist) {
            startActivity(new Intent(this, SimpleActivity.class));
        } else if (v == productDetail) {
            startActivity(new Intent(this, ProdetailActivity.class));
        } else if (v == douyin) {
            startActivity(new Intent(this, DouYinActivity.class));
        }
    }
}
