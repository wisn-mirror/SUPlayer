package com.suplayer.tv;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.suplayer.Constants;
import com.suplayer.R;
import com.suplayer.bean.AliveBean;

import java.util.List;

/**
 * Created by Wisn on 2019-07-25 17:17.
 */
public class TVAliveAdapter extends RecyclerView.Adapter<TVAliveAdapter.VideoHolder> {

    private List<AliveBean> videos;
    private Context context;

    public TVAliveAdapter(List<AliveBean> videos, Context context) {
        this.videos = videos;
        this.context = context;
    }

    @Override
    public VideoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_tv, parent, false);
        return new VideoHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final VideoHolder holder, int position) {
        AliveBean videoBean = videos.get(position);
        holder.tv_name.setText(videoBean.name);
        holder.tv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AliveActivity.class);
                intent.putExtra(Constants.data, videoBean.url);
                intent.putExtra(Constants.name, videoBean.name);
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

        VideoHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
        }
    }
}
