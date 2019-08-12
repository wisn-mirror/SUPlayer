package com.suplayer.example.list;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.suplayer.Constants;
import com.suplayer.R;
import com.suplayer.example.douyin.DouyinAdapter;
import com.suplayer.example.douyin.VideoBean;

import java.util.List;

/**
 * Created by Wisn on 2019-08-12 11:28.
 */
public class VideoListActivity extends Activity {

    private RecyclerView recycleview;
    private List<VideoBean> mVideoList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videolist);
        recycleview = findViewById(R.id.recycleview);
        mVideoList = Constants.getVideoBean();
        VideoListAdapter douyinAdapter = new VideoListAdapter(mVideoList, this);
        recycleview.setAdapter(douyinAdapter);

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_list:
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recycleview.setLayoutManager(linearLayoutManager);
                break;
            case R.id.tv_lei:
                GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
                recycleview.setLayoutManager(gridLayoutManager);
                break;
            case R.id.tv_liu:
                StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL) {
                    @Override
                    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
                        try {
                            super.onLayoutChildren(recycler, state);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                recycleview.setLayoutManager(staggeredGridLayoutManager);
                break;
        }
    }


}
