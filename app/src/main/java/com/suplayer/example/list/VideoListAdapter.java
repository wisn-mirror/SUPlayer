package com.suplayer.example.list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.suplayer.R;
import com.suplayer.example.douyin.DouyinAdapter;
import com.suplayer.example.douyin.VideoBean;

import java.util.List;

/**
 * Created by Wisn on 2019-08-12 11:40.
 */
public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.VideoHolder> {
    private List<VideoBean> videos;
    private Context context;

    public VideoListAdapter(List<VideoBean> videos, Context context) {
        this.videos = videos;
        this.context = context;
    }

    @NonNull
    @Override
    public VideoHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_videolist, viewGroup, false);
        return new VideoListAdapter.VideoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoHolder holder, int position) {
        VideoBean videoBean = videos.get(position);
        Glide.with(context)
                .load(videoBean.getThumb())
                .into(holder.thumb);
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public class VideoHolder extends RecyclerView.ViewHolder {
        private ImageView thumb;

        VideoHolder(View itemView) {
            super(itemView);
            thumb = itemView.findViewById(R.id.thumb);
        }
    }
}
