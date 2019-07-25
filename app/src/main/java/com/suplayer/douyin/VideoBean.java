package com.suplayer.douyin;

/**
 * Created by Wisn on 2019-07-25 17:44.
 */
public class VideoBean {
    private String title;
    private String url;
    private String thumb;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public VideoBean(String title, String thumb, String url) {
        this.title = title;
        this.url = url;
        this.thumb = thumb;

    }
}
