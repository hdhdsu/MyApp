package com.xinguang.myapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xinguang.myapp.R;

public class NetActivity extends BaseActivity implements View.OnClickListener{

    private Button mBtMoview;
    private Button mBtGirl;

    @Override
    protected int getPageLayoutID() {
        return R.layout.activity_net;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mBtMoview = findViewById(R.id.movie);
        mBtGirl = findViewById(R.id.girl);
    }

    @Override
    protected void initViewListener() {
        mBtMoview.setOnClickListener(this);
        mBtGirl.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.movie:
                startActivity(MovieActivity.class);
                break;
            case R.id.girl:
                startActivity(GankActivity.class);
                break;
        }
    }
}
