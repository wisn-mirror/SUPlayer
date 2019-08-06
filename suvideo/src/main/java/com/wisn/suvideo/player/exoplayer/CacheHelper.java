package com.wisn.suvideo.player.exoplayer;


import com.google.android.exoplayer2.upstream.cache.LeastRecentlyUsedCacheEvictor;
import com.google.android.exoplayer2.upstream.cache.SimpleCache;

import java.io.File;

/**
 * Created by Wisn on 2019-08-05 18:33.
 */
public class CacheHelper {

    public static SimpleCache simpleCache;

    public static SimpleCache getSimpleCache(String roorDir){
        if(simpleCache==null) {
            synchronized (CacheHelper.class){
                if (simpleCache == null) {
                    File cacheFile = new File(roorDir, "video");
                    // 本地最多保存512M, 按照LRU原则删除老数据
                    simpleCache = new SimpleCache(cacheFile, new LeastRecentlyUsedCacheEvictor(1024 * 1024 * 1024));
                }
            }
        }
        return simpleCache;

    }

}
