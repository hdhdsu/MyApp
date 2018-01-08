package com.xinguang.myapp.activity;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xinguang.myapp.R;

public class PermissionActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected int getPageLayoutID() {
        return R.layout.activity_permission;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        Button mBtPhoto = findViewById(R.id.photo);
        mBtPhoto.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.photo:
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 101);
                break;
        }
    }
}
