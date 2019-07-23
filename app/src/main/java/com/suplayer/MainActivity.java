package com.suplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button videolist;
    private Button productDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videolist = findViewById(R.id.videolist);
        productDetail = findViewById(R.id.productDetail);
        videolist.setOnClickListener(this);
        productDetail.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
