package com.xinguang.myapp.widget;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinguang.myapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 底部弹窗，用法，先构造函数，然后showPayMenus展示
 * Created by lenghuo on 2017/7/25 11:45
 * Modified by xxx
 */

public class BottomPop implements View.OnClickListener, AdapterView.OnItemClickListener {
    private Context mContext;//上下文
    private String mTitle;//标题
    private String mSelect;//选中
    private View mView;
    private View mOutSide;
    private ImageView mIv_cancel;
    private TextView mTitle1;
    private ListView mList;
    private View mItemLayout;
    private Button mBt_cancel;
    private RelativeLayout mRl_title;
    private PopupWindow mPopupWindow;
    List<String> mData = new ArrayList<>();
    private CallBack mCallBack;

    /**
     * 构造方法
     * @param context 上下文
     * @param title 第一行标题，如果没有标题传null
     * @param select 初始化选中，如果没有传null
     * @param data 集合数据，目前只有text类型
     * @param callback 点击回调，监听第几项的回调，然后做操作
     */
    public BottomPop(Context context, String title, String select, List<String> data, CallBack callback) {
        mCallBack = callback;
        mContext = context;
        mTitle = title;
        mSelect = select;
        mData = data;
        initView();
    }
    public BottomPop(Context context, String title, String select, String[] data, CallBack callback) {
        mCallBack = callback;
        mContext = context;
        mTitle = title;
        mSelect = select;
        for (int i = 0; i < data.length; i++) {
            mData.add(data[i]);
        }
        initView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pop_outside_layout:
                dismiss();
                break;
            case R.id.img_cancel:
                dismiss();
                break;
            case R.id.btn_cancel:
                dismiss();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mCallBack.click(position);
        dismiss();
    }

    private void initView() {

        mView = LayoutInflater.from(mContext).inflate(R.layout.visit_reason, null);
        mItemLayout = mView.findViewById(R.id.pop_item_layout);
        mOutSide = mView.findViewById(R.id.pop_outside_layout);
        mIv_cancel = (ImageView) mView.findViewById(R.id.img_cancel);
        mTitle1 = (TextView) mView.findViewById(R.id.pop_title);
        mList = (ListView) mView.findViewById(R.id.pop_list);
        mBt_cancel = (Button) mView.findViewById(R.id.btn_cancel);
        mBt_cancel.setOnClickListener(this);
        mRl_title = (RelativeLayout) mView.findViewById(R.id.rl_title);
        mOutSide.setOnClickListener(this);
        mIv_cancel.setOnClickListener(this);

        if (TextUtils.isEmpty(mTitle)) {
            mRl_title.setVisibility(View.GONE);
        }else {
            mRl_title.setVisibility(View.VISIBLE);
            mTitle1.setText(mTitle);
        }
        PopAdapter adapter = new PopAdapter(mData);
        mList.setAdapter(adapter);

        mList.setOnItemClickListener(this);

    }

    public interface CallBack{
        public void click(int position);
    }

    /**
     * 底部弹窗展示
     */
    public void showPayMenus(View attachView) {
        if (mPopupWindow == null) {
            mPopupWindow = new PopupWindow(mView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT,
                    true);
            mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
            //            mPopupWindow.setAnimationStyle(R.style.vs_popupwindow_anim_style);
            mPopupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        }
        mItemLayout.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.push_bottom_in));
        //        mPopupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
        mPopupWindow.showAtLocation(attachView, Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 0);
    }

    //消失动画
    public void dismiss(){
        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.push_bottom_out);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mPopupWindow.dismiss();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mItemLayout.startAnimation(animation);
        //        mPayPopWindow.dismiss();
    }

    class PopAdapter extends BaseAdapter {
        private LayoutInflater inflater;
        private List<String> datas = new ArrayList<>();

        public PopAdapter(List<String> datas){
            this.datas = datas;
            this.inflater = LayoutInflater.from(mContext);
        }

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int i) {
            return datas.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            View rootView = null;
            rootView = inflater.inflate(R.layout.item_pop, null);
            TextView tv = (TextView) rootView.findViewById(R.id.tv);
            String item = (String) getItem(i);
            if(datas.get(i).equals(mSelect)){
                tv.setTextColor(mContext.getResources().getColor(R.color.pop_selected));
            }
            tv.setText(datas.get(i));

            return rootView;
        }
    }




}
