package com.wisn.suvideo.manager.impl;

import android.text.TextUtils;

import com.wisn.suvideo.manager.ProgressManager;

import java.util.LinkedHashMap;

/**
 * Created by Wisn on 2019-07-24 16:14.
 */
public class ProgressManagerMemory extends ProgressManager {

    private static LinkedHashMap<Integer, Long> progressMap = new LinkedHashMap<>();

    @Override
    public void saveProgress(String url, long progress) {
        if (TextUtils.isEmpty(url)) return;
        if (progress == 0) {
            clearSavedProgressByUrl(url);
            return;
        }
        progressMap.put(url.hashCode(), progress);
    }

    @Override
    public long getSavedProgress(String url) {
        return TextUtils.isEmpty(url) ? 0 : progressMap.containsKey(url.hashCode()) ? progressMap.get(url.hashCode()) : 0;
    }

    public void clearAllSavedProgress() {
        progressMap.clear();
    }

    public void clearSavedProgressByUrl(String url) {
        progressMap.remove(url.hashCode());
    }
}
