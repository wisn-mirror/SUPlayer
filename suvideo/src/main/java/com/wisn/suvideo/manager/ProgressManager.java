package com.wisn.suvideo.manager;

/**
 * Created by Wisn on 2019-07-23 19:11.
 */
public abstract class ProgressManager {

    public abstract void saveProgress(String url, long progress);

    public abstract long getSavedProgress(String url);
}
