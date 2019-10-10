package com.suplayer.tv.local;

import android.Manifest;
import android.app.ActivityManager;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.suplayer.Constants;
import com.suplayer.R;
import com.suplayer.bean.AliveBean;
import com.suplayer.tv.alive.TVAliveAdapter;
import com.suplayer.tv.local.model.Folder;
import com.suplayer.tv.local.model.MediaInfo;
import com.suplayer.tv.local.model.MediaInfoModel;
import com.wisn.suvideo.base.BaseActivity;

import java.util.ArrayList;

public class LocVideoListActivity extends BaseActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            setTaskDescription(new ActivityManager.TaskDescription("本地视频aa", BitmapFactory.decodeResource(getResources(),R.drawable.suplayer_action_play_arrow)));
        }
        setContentView(R.layout.activity_loc_video_list);
        recyclerView = findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE
            }, 1);
        } else {
            loadData();
        }
    }

    private void loadData() {
        MediaInfoModel.getAllVideoInfos(this, new MediaInfoModel.DataCallback() {

            @Override
            public void onSuccessFolder(ArrayList<Folder> folders) {

            }

            @Override
            public void onSuccessMediaInfo(ArrayList<MediaInfo> mediainfo) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        LocVideoAdapter tvAliveAdapter = new LocVideoAdapter(mediainfo, LocVideoListActivity.this);
                        recyclerView.setAdapter(tvAliveAdapter);
                    }
                });
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (int i = 0; i < permissions.length; i++) {
            String permission = permissions[i];
            if (Manifest.permission.READ_EXTERNAL_STORAGE.equals(permission)) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    loadData();
                }
            }
        }
    }
}
