package com.xinguang.myapp.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xinguang.myapp.R;
import com.xinguang.myapp.widget.TopGuideBar;

/**
 * Created by 14912 on 2018/1/4.
 */

@SuppressLint("ValidFragment")
public class FragmentMain extends Fragment {
    private Context mContext;
    private TopGuideBar topGuideBar;
    private View view;

    public FragmentMain(Context mContext) {
        this.mContext = mContext;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main,container,false);
        topGuideBar = (TopGuideBar) view.findViewById(R.id.top_guide_bar);
        topGuideBar.setTitle("首页");
        return view;
    }
}
