package com.xinguang.myapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.xinguang.myapp.R;

public class GlideActivity extends BaseActivity {
    String url = "http://img.blog.csdn.net/20160507110203928";



    @Override
    protected int getPageLayoutID() {
        return R.layout.activity_glide;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        ImageView img1 = findViewById(R.id.img1);
        ImageView img2 = findViewById(R.id.img2);
        Button bt = findViewById(R.id.bt);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GlideActivity.this,"1111",Toast.LENGTH_LONG).show();
                Glide.with(GlideActivity.this).load(url).into(img1);
                Glide.with(GlideActivity.this).load(url).into(img2);


            }
        });
    }

}
