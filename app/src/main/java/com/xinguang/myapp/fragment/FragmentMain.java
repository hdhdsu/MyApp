package com.xinguang.myapp.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.xinguang.myapp.R;
import com.xinguang.myapp.activity.GoogleSignInActivity;
import com.xinguang.myapp.activity.NetActivity;
import com.xinguang.myapp.activity.PermissionActivity;
import com.xinguang.myapp.widget.BottomPop;
import com.xinguang.myapp.widget.TopGuideBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 14912 on 2018/1/4.
 */

@SuppressLint("ValidFragment")
public class FragmentMain extends Fragment implements View.OnClickListener {
    private Context mContext;
    private TopGuideBar topGuideBar;
    private View view;

    public FragmentMain(Context mContext) {
        this.mContext = mContext;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);
        topGuideBar = (TopGuideBar) view.findViewById(R.id.top_guide_bar);
        topGuideBar.setTitle("首页");

        Button mBtNet = view.findViewById(R.id.study1);
        Button mBtpermission = view.findViewById(R.id.study2);
        Button mBtGoogleSignIn = view.findViewById(R.id.study3);
        Button mBtBottomPop = view.findViewById(R.id.study4);
        mBtBottomPop.setOnClickListener(this);
        mBtNet.setOnClickListener(this);
        mBtpermission.setOnClickListener(this);
        mBtGoogleSignIn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.study1:
                //入坑retrofit
                Intent intent1 = new Intent(mContext, NetActivity.class);
                startActivity(intent1);
                break;
            case R.id.study2:
                //android权限管理组件
                Intent intent2 = new Intent(mContext, PermissionActivity.class);
                startActivity(intent2);
                break;
            case R.id.study3:
                //集成谷歌登陆
                Intent intent3 = new Intent(mContext, GoogleSignInActivity.class);
                startActivity(intent3);
                break;
            case R.id.study4:
                List<String> arry = new ArrayList<>();
                arry.add("11111111");
                arry.add("2222222");
                arry.add("33333333");
                arry.add("444444444");
                arry.add("55555555");
                arry.add("66666666");

                BottomPop pop = new BottomPop(mContext,"title","222",arry, new BottomPop.CallBack(){

                    @Override
                    public void click(int position) {

                    }
                });
                pop.showPayMenus(view);
                break;
        }
    }
}
