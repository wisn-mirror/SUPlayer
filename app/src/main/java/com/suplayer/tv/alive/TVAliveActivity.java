package com.suplayer.tv.alive;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.suplayer.Constants;
import com.suplayer.R;
import com.suplayer.bean.AliveBean;
import com.wisn.suvideo.base.BaseActivity;

import java.util.List;

/**
 * Created by Wisn on 2019-08-02 18:03.
 */
public class TVAliveActivity extends BaseActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvalive);
        recyclerView = findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<AliveBean> testBean = Constants.getTestBean();
        TVAliveAdapter tvAliveAdapter = new TVAliveAdapter(testBean, this);
        recyclerView.setAdapter(tvAliveAdapter);
    }
}
