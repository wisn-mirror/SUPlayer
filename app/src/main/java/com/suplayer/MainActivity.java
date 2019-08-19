package com.suplayer;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.suplayer.example.ProgressActivity;
import com.suplayer.example.douyin.DouYinActivity;
import com.suplayer.example.list.VideoListActivity;
import com.suplayer.example.xiaohongshu.HongshuActivity;
import com.suplayer.productdetail.ProdetailActivity;
import com.suplayer.tv.local.LocVideoListActivity;
import com.suplayer.tv.alive.TVAliveActivity;
import com.wisn.suvideo.VideoViewConfig;
import com.wisn.suvideo.base.BaseActivity;
import com.wisn.suvideo.manager.VideoViewManager;
import com.wisn.suvideo.player.exoplayer.ExoMediaPlayerFactory;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button videolist;
    private Button productDetail;
    private Button Simple;
    private Button douyin;
    private Button hongshu;
    private Button tv;
    private Button localvideo;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler = new Handler();
        startActivity(new Intent(this, ProgressActivity.class));
//        startActivity(new Intent(this, TVAliveActivity.class));
//        this.finish();
        setContentView(R.layout.activity_main);
        videolist = findViewById(R.id.videolist);
        productDetail = findViewById(R.id.productDetail);
        Simple = findViewById(R.id.Simple);
        douyin = findViewById(R.id.douyin);
        hongshu = findViewById(R.id.hongshu);
        localvideo = findViewById(R.id.localvideo);
        tv = findViewById(R.id.tv);
        videolist.setOnClickListener(this);
        productDetail.setOnClickListener(this);
        Simple.setOnClickListener(this);
        douyin.setOnClickListener(this);
        hongshu.setOnClickListener(this);
        tv.setOnClickListener(this);
        localvideo.setOnClickListener(this);
        initconfig();
    }

    private void initconfig() {
        VideoViewConfig build = VideoViewConfig.newBuilder().setPlayerFactory(ExoMediaPlayerFactory.create(this)).build();
        VideoViewManager.setConfig(build);
    }

    @Override
    public void onClick(View v) {
        if (v == Simple) {
            startActivity(new Intent(this, SimpleActivity.class));
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this,"test",Toast.LENGTH_SHORT).show();
                }
            },2000);
        } else if (v == videolist) {
            startActivity(new Intent(this, VideoListActivity.class));
            handler.removeCallbacksAndMessages(null);
        } else if (v == productDetail) {
            startActivity(new Intent(this, ProdetailActivity.class));
        } else if (v == douyin) {
            startActivity(new Intent(this, DouYinActivity.class));
        } else if (v == hongshu) {
            startActivity(new Intent(this, HongshuActivity.class));
        } else if (v == tv) {
            startActivity(new Intent(this, TVAliveActivity.class));
        } else if (v == localvideo) {
            startActivity(new Intent(this, LocVideoListActivity.class));
        }
    }
}
