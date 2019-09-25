package com.suplayer;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.suplayer.example.douyin.DouYinActivity;
import com.suplayer.example.list.VideoListActivity;
import com.suplayer.example.xiaohongshu.HongshuActivity;
import com.suplayer.productdetail.ProdetailActivity;
import com.suplayer.tv.PlayActivity;
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
    private Button zhibo;
    private Button zhibo2;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler = new Handler();
//        startActivity(new Intent(this, ProdetailActivity.class));
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
        zhibo = findViewById(R.id.zhibo);
        zhibo2 = findViewById(R.id.zhibo2);
        videolist.setOnClickListener(this);
        productDetail.setOnClickListener(this);
        Simple.setOnClickListener(this);
        douyin.setOnClickListener(this);
        hongshu.setOnClickListener(this);
        tv.setOnClickListener(this);
        localvideo.setOnClickListener(this);
        zhibo.setOnClickListener(this);
        zhibo2.setOnClickListener(this);
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
        } else if (v == zhibo) {
           /* {"港好莱电影轮播 1", "http://aldirect.hls.huya.com/huyalive/29169025-2686219962-11537226886652362752-2710080226-10057-A-0-1_1200.m3u8"},
            {"港好莱电影轮播 2 ", "http://js.hls.huya.com/huyalive/30765679-2478268764-10644083292078342144-2847699106-10057-A-0-1_1200.m3u8"},
            {"港好莱电影轮播 3", "http://aldirect.hls.huya.com/huyalive/30765679-2504742278-10757786168918540288-3049003128-10057-A-0-1_1200.m3u8"},
*/
            Intent intent = new Intent(this, PlayActivity.class);
            intent.putExtra(Constants.data, "http://aldirect.hls.huya.com/huyalive/29169025-2686219962-11537226886652362752-2710080226-10057-A-0-1_1200.m3u8");
            intent.putExtra(Constants.name, "港好莱电影轮播 1");
            intent.putExtra(Constants.isalive, true);
            intent.putExtra(Constants.isFull, true);
            this.startActivity(intent);
        } else if (v == zhibo2) {
           /* {"港好莱电影轮播 1", "http://aldirect.hls.huya.com/huyalive/29169025-2686219962-11537226886652362752-2710080226-10057-A-0-1_1200.m3u8"},
            {"港好莱电影轮播 2 ", "http://js.hls.huya.com/huyalive/30765679-2478268764-10644083292078342144-2847699106-10057-A-0-1_1200.m3u8"},
            {"港好莱电影轮播 3", "http://aldirect.hls.huya.com/huyalive/30765679-2504742278-10757786168918540288-3049003128-10057-A-0-1_1200.m3u8"},
*/
            Intent intent = new Intent(this, PlayActivity.class);
            intent.putExtra(Constants.data, "http://aldirect.hls.huya.com/huyalive/30765679-2504742278-10757786168918540288-3049003128-10057-A-0-1_1200.m3u8");
            intent.putExtra(Constants.name, "港好莱电影轮播 2");
            intent.putExtra(Constants.isalive, true);
            intent.putExtra(Constants.isFull, true);
            this.startActivity(intent);
        }
    }
}
