package com.suplayer.tv.local;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.suplayer.Constants;
import com.suplayer.R;
import com.suplayer.tv.PlayActivity;
import com.suplayer.tv.local.model.MediaInfo;

import java.util.ArrayList;

/**
 * Created by Wisn on 2019-07-25 17:17.
 */
public class LocVideoAdapter extends RecyclerView.Adapter<LocVideoAdapter.VideoHolder> {

    private ArrayList<MediaInfo> videos;
    private Context context;

    public LocVideoAdapter(ArrayList<MediaInfo> videos, Context context) {
        this.videos = videos;
        this.context = context;
    }

    @Override
    public VideoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_video, parent, false);
        return new VideoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final VideoHolder holder, int position) {
        MediaInfo videoBean = videos.get(position);
        holder.tv_name.setText(videoBean.getName());
        holder.tv_des.setText(videoBean.getPath());
        holder.tv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PlayActivity.class);
                intent.putExtra(Constants.data, videoBean.getPath());
                intent.putExtra(Constants.name, videoBean.getName());
                intent.putExtra(Constants.isalive, false);
                intent.putExtra(Constants.isFull, false);
                intent.putExtra(Constants.isLocal, true);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public class VideoHolder extends RecyclerView.ViewHolder {
        private TextView tv_name;
        private TextView tv_des;

        VideoHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_des = itemView.findViewById(R.id.tv_des);
        }
    }
}
