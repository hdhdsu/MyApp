package com.xinguang.myapp.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xinguang.myapp.R;
import com.xinguang.myapp.fragment.FragmentMain;
import com.xinguang.myapp.fragment.FragmentSecond;
import com.xinguang.myapp.fragment.FragmentThird;

/**
 * Created by 14912 on 2017/12/28.
 */

public class MainActivity extends BaseActivity implements View.OnClickListener {

    //底部三个菜单
    private LinearLayout mainPage, secondPage, thirdPage;

    //三个textview
    private TextView tvMain, tvSecond, tvThird;

    //三个imageview
    private ImageView ivMain, ivSecond, ivThird;

    //三个fragment
    private Fragment mainFragment, secondFragment, thirdFragment;

    private LinearLayout bottom;

    @Override
    protected int getPageLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        bottom = (LinearLayout) findViewById(R.id.bottom);
        // 底部菜单3个
        this.mainPage = (LinearLayout) findViewById(R.id.ll_home);
        this.secondPage = (LinearLayout) findViewById(R.id.ll_classify);
        this.thirdPage = (LinearLayout) findViewById(R.id.ll_car);

        // 底部菜单3个ImageView
        this.ivMain = (ImageView) findViewById(R.id.iv_home);
        this.ivSecond = (ImageView) findViewById(R.id.iv_classify);
        this.ivThird = (ImageView) findViewById(R.id.iv_car);

        // 底部菜单3个菜单标题
        this.tvMain = (TextView) findViewById(R.id.tv_home);
        this.tvSecond = (TextView) findViewById(R.id.tv_classify);
        this.tvThird = (TextView) findViewById(R.id.tv_car);

        initFragment(0);
    }

    @Override
    protected void initViewListener() {
        // 设置按钮监听
        mainPage.setOnClickListener(this);
        secondPage.setOnClickListener(this);
        thirdPage.setOnClickListener(this);
    }

    public void initFragment(int index) {
        // 由于是引用了V4包下的Fragment，所以这里的管理器要用getSupportFragmentManager获取
        FragmentManager fragmentManager = getSupportFragmentManager();
        // 开启事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 隐藏所有Fragment
        hideFragment(transaction);

        switch (index) {
            case 0:
                ivMain.setImageResource(R.drawable.icon_bottom_market_sel);
                if (mainFragment == null) {
                    mainFragment = new FragmentMain(this);
                    transaction.add(R.id.fl_content, mainFragment);
                } else {
                    transaction.show(mainFragment);
                }
                break;
            case 1:
                restartBotton();
                ivSecond.setImageResource(R.drawable.icon_bottom_classify_sel);
                if (secondFragment == null) {
                    secondFragment = new FragmentSecond(this);
                    transaction.add(R.id.fl_content, secondFragment);
                } else {
                    transaction.show(secondFragment);
                }
                break;
            case 2:
                restartBotton();
                ivThird.setImageResource(R.drawable.icon_bottom_car_sel);
                if (thirdFragment == null) {
                    thirdFragment = new FragmentThird(this);
                    transaction.add(R.id.fl_content, thirdFragment);
                } else {
                    transaction.show(thirdFragment);
                }
                break;
            default:
                break;
        }

        // 提交事务
        transaction.commit();
    }

    //隐藏Fragment
    private void hideFragment(FragmentTransaction transaction) {
        if (mainFragment != null) {
            transaction.hide(mainFragment);
        }
        if (secondFragment != null) {
            transaction.hide(secondFragment);
        }
        if (thirdFragment != null) {
            transaction.hide(thirdFragment);
        }
    }

    @Override
    public void onClick(View view) {
// 在每次点击后将所有的底部按钮(ImageView,TextView)颜色改为灰色，然后根据点击着色
        restartBotton();
        // ImageView和TetxView置为绿色，页面随之跳转
        switch (view.getId()) {
            case R.id.ll_home:
                initFragment(0);
                break;
            case R.id.ll_classify:
                initFragment(1);
                break;
            case R.id.ll_car:
                initFragment(2);
                break;
            default:
                break;
        }
    }

    private void restartBotton() {
        // ImageView置为灰色
        ivMain.setImageResource(R.drawable.icon_bottom_market_unsel);
        ivSecond.setImageResource(R.drawable.icon_bottom_classify_unsel);
        ivThird.setImageResource(R.drawable.icon_bottom_car_unsel);

    }

}
