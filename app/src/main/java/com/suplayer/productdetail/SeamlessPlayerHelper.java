package com.suplayer.productdetail;

import android.content.Context;
import com.wisn.suvideo.SuVideoView;

/**
 * Created by Wisn on 2019-08-01 14:02.
 */
public class SeamlessPlayerHelper {
    private SuVideoView suVideoView;
    private static SeamlessPlayerHelper  instance;

    private SeamlessPlayerHelper(Context context) {
        suVideoView = new SuVideoView(context);
//        suVideoView.setId(R.id.video_player);
    }
    public static SeamlessPlayerHelper getInstance(Context context){
        if(instance==null){
            synchronized (SeamlessPlayerHelper.class){
                if(instance==null){
                    instance=new SeamlessPlayerHelper(context.getApplicationContext());
                }
            }
        }
        return instance;
    }
    public SuVideoView getSuVideoView(){
        return suVideoView;
    }
}
