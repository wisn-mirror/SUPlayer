package com.suplayer.example;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.suplayer.R;

public class ProgressActivity extends AppCompatActivity {

    private ImageView id_location,iv_target;
    String url = "https://static4.laiyifen.com/files/cms/image/1562902766789_9685.png";
boolean isvideo=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        id_location = findViewById(R.id.id_location);
        iv_target = findViewById(R.id.iv_target);
        Glide.with(this)
                .load(url)
                .into(id_location);

        id_location.postDelayed(new Runnable() {
            @Override
            public void run() {
                final int[] location = new int[2];
                id_location.getLocationOnScreen(location);
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) iv_target.getLayoutParams();
                layoutParams.width=getScreenWidth(ProgressActivity.this);
                layoutParams.height= (int) (getScreenWidth(ProgressActivity.this)*0.48f);
                iv_target.setY((location[1]- calcStatusBarHeight(ProgressActivity.this))-dp2px(20,ProgressActivity.this));
                iv_target.setLayoutParams(layoutParams);
            }
        },100);
//        iv_target.setX(x);
        initSmallView(this.findViewById(R.id.content));
        setSelect(isvideo);
        iv_target.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isvideo=!isvideo;
                setSelect(isvideo);
            }
        });
    }

    public static int calcStatusBarHeight(Context context) {
        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height").get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }
    public static int dp2px(float dpValue,Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, context.getResources().getDisplayMetrics());
    }

    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    LinearLayout ll_small;
    LinearLayout ll_small_video;
    ImageView iv_small_video  ;
    TextView tv_small_video;
    TextView tv_small_image;
    private void initSmallView(View view){
         ll_small= view.findViewById(R.id.ll_small);
         ll_small_video= view.findViewById(R.id.ll_small_video);
         iv_small_video= view.findViewById(R.id.iv_small_video);
         tv_small_video= view.findViewById(R.id.tv_small_video);
         tv_small_image= view.findViewById(R.id.tv_small_image);
        ll_small_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelect(true);
            }
        });
        tv_small_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelect(false);
            }
        });
    }
    private void setSelect(boolean isVideo){
        this.isvideo=isVideo;
        ll_small_video.setSelected(isVideo);
        tv_small_image.setSelected(!isVideo);
        iv_small_video.setSelected(!isVideo);
        tv_small_video.setTextColor(isVideo?getResources().getColor(R.color.white):getResources().getColor(R.color.black));
        tv_small_image.setTextColor(isVideo?getResources().getColor(R.color.black):getResources().getColor(R.color.white));
    }


}
