package com.xinguang.myapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.xinguang.myapp.R;

/**
 * Created by 14912 on 2017/12/28.
 */

public class MainActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.bt_movies).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //进入一个新的页面并且进行网络加载
                Intent intent = new Intent(MainActivity.this,MovieActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.bt_gank).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //进入一个新的页面并且进行网络加载
                Intent intent = new Intent(MainActivity.this,GankActivity.class);
                startActivity(intent);
            }
        });
    }
}
