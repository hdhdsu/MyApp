package com.xinguang.myapp.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xinguang.myapp.R;

/**
 * 自定义导航栏
 * */
public class TopGuideBar extends LinearLayout {

    private Context mContext;
    private TextView mTvTitle;
    private ImageButton mBtnBack;
    private ImageButton mBtnRight;
    private View mViewTitle;
    private View mView;
    private TextView mTvRightText;
    private View mViewDividerBottom;
    private boolean mRightTextMode;
    private ImageView iv_right_red_point;  //右上角一个小红点

    private boolean mAlphaMode;
    private int mResIdOri;
    private int mResIdCur;

    private CharSequence mTitle;

    public TopGuideBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(final Context context, AttributeSet attrs) {
        mContext = context;
        LayoutInflater inflater = LayoutInflater.from(context);
        mView = inflater.inflate(R.layout.top_guide_bar, this);
        mTvTitle = (TextView) mView.findViewById(R.id.tv_title);
        mBtnBack = (ImageButton) mView.findViewById(R.id.btn_back);
        mBtnRight = (ImageButton) mView.findViewById(R.id.btn_right);
        iv_right_red_point = (ImageView) mView.findViewById(R.id.iv_right_red_point);
        mViewTitle = mView.findViewById(R.id.ll_title);
        mTvRightText = (TextView) mView.findViewById(R.id.tv_right_text);
        mViewDividerBottom = findViewById(R.id.divider_bottom);
        mViewDividerBottom.setVisibility(GONE);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TopGuideBar);
        //标题
        mTitle = a.getText(R.styleable.TopGuideBar_title);
        if (mTitle != null) {
            mTvTitle.setText(mTitle);
        }
        //leftIcon
        boolean leftBtnVisible = a.getBoolean(R.styleable.TopGuideBar_leftButtonVisible, true);
        mBtnBack.setVisibility(leftBtnVisible ? View.VISIBLE : View.GONE);
        //rightIcon
        boolean rightBtnVisible = a.getBoolean(R.styleable.TopGuideBar_rightButtonVisible, true);
        mBtnRight.setVisibility(rightBtnVisible ? View.VISIBLE : View.GONE);

        int resId = a.getResourceId(R.styleable.TopGuideBar_rightButtonImage, 0);
        if (resId != 0) {
            mBtnRight.setImageResource(resId);
        }
        //rightText
        mRightTextMode = a.getBoolean(
                R.styleable.TopGuideBar_rightButtonTextMode, false);
        if (mRightTextMode) {
            mTvRightText.setVisibility(View.VISIBLE);
            mBtnRight.setVisibility(View.GONE);
            resId = a.getResourceId(R.styleable.TopGuideBar_rightButtonText, 0);
            if (resId != 0) {
                mTvRightText.setText(getResources().getString(resId));
            }
        }

        if (leftBtnVisible) {
            mBtnBack.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mContext instanceof Activity) {
                        ((Activity) mContext).finish();
                    }
                }
            });
        }
        //颜色
        boolean stewardMode = a.getBoolean(R.styleable.TopGuideBar_stewardMode, false);
        if (!stewardMode) {
            setBackGroundColor(R.color.main);
        } else {
            mTvTitle.setTextColor(getResources().getColor(R.color.normal_text));
        }
        //透明度
        mAlphaMode = a.getBoolean(R.styleable.TopGuideBar_alphaMode, false);
        if (mAlphaMode) {
            setAlphaMode();
        }
        //下拉选择样式
        boolean chooseMode = a.getBoolean(R.styleable.TopGuideBar_chooseMode, false);
        if (chooseMode) {
            mTvRightText.setTextSize(14);
//            mTvRightText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.img_drop_down_black, 0);
//            mTvRightText.setCompoundDrawablePadding(mContext.getResources().getDimensionPixelSize(R.dimen.drawable_padding));
        }
    }

    public CharSequence getmTitle() {
        return mTitle;
    }


    private void setAlphaMode() {
        setBackgroundResource(0);
        mBtnRight.setBackgroundResource(0);
        mBtnBack.setImageResource(R.drawable.btn_back_normal);
        mTvTitle.setText("");
        mViewDividerBottom.setVisibility(View.GONE);
    }

    public void setCurrentAlpha(float alpha) {
        if (!mAlphaMode) {
            return;
        }

        if (alpha > 0.5f) {
            setAlpha((alpha - 0.5f) * 2);
            mViewDividerBottom.setVisibility(View.GONE);
            mBtnBack.setImageResource(R.drawable.btn_back_press);
            mBtnBack.setAlpha(1.0f);
            mBtnRight.setImageResource(mResIdCur);
            mBtnRight.setAlpha(1.0f);
            if (mTitle != null) {
                mTvTitle.setText(mTitle);
            }
            mTvTitle.setAlpha(1.0f);
            setBackGroundColor(R.color.main);
        } else {
            setAlpha(1.0f);
            setBackgroundColor(Color.TRANSPARENT);

            setBackgroundResource(0);
            mViewDividerBottom.setVisibility(View.GONE);
            mBtnBack.setImageResource(R.drawable.btn_back_normal);
            mBtnBack.setAlpha(1 - alpha * 2);
            mBtnRight.setImageResource(mResIdOri);
            mBtnRight.setAlpha(1 - alpha * 2);
            mTvTitle.setAlpha(1 - alpha * 2);
            mTvTitle.setText("");
        }
    }

    /**
     * 设置TopGuideBar可见按钮
     * 注：此处有个bug，若right为true时，mBtnRight的点击事件可能被mBtnRightText遮掩，导致点击失效
     * 建议使用单独的方法设置
     *
     * @param left
     * @param right
     */
    public void setBtnVisibility(boolean left, boolean right) {
        mBtnBack.setVisibility(left ? View.VISIBLE : View.GONE);
        mBtnRight.setVisibility(right ? View.VISIBLE : View.GONE);
        mTvRightText.setVisibility(right ? View.VISIBLE : View.GONE);
    }

    public TextView getRightTextView() {
        return mTvRightText;
    }

    public void setRightRedPointShow(boolean isShow){
        iv_right_red_point.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    public void setBtnBackVisibility(boolean show) {
        mBtnBack.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    public void setBtnRightVisibility(int visibility) {
        mBtnRight.setVisibility(visibility);
    }

    public void setBtnRightTextVisibility(int visibility) {
        mTvRightText.setVisibility(visibility);
    }

    public void setDropDownMode(final OnClickListener listener) {
        // mIvDropDown.setVisibility(View.VISIBLE);
        mViewTitle.setOnClickListener(listener);
    }

    public void setTitle(CharSequence cs) {
        mTitle = cs;
        if (!mAlphaMode) {
            mTvTitle.setText(cs);
        }
    }

    public void setRightBtnImage(int resId) {
        mBtnRight.setImageResource(resId);
    }
    public void setRightTextModeFalse(){
        mRightTextMode = false;
    }

    public void setRightBtnImage(Drawable drawable) {
        mBtnRight.setImageDrawable(drawable);
    }

    public ImageButton getRightBtnImage() {
        return mBtnRight;
    }

    public void setRightBtnImageInAlphaMode(int resIdOri, int resIdCur) {
        if (!mAlphaMode) {
            return;
        }
        mResIdCur = resIdCur;
        mResIdOri = resIdOri;
    }

    public void setRightTextMode() {
        mRightTextMode = true;
        mTvRightText.setVisibility(View.VISIBLE);
        mBtnRight.setVisibility(View.GONE);
    }

    public void setRightBtnText(int resId) {
        mTvRightText.setVisibility(View.VISIBLE);
        mTvRightText.setText(resId);
    }

    public void setRightBtnText(CharSequence cs) {
        mTvRightText.setVisibility(View.VISIBLE);
        mTvRightText.setText(cs);
    }

    public void setBackBtnClickListener(OnClickListener l) {
        mBtnBack.setOnClickListener(l);
    }

    public void setBackBtnImage(int resId) {
        mBtnBack.setImageResource(resId);
    }

    public void setRightBtnClickListener(OnClickListener l) {
        if (mRightTextMode) {
            mTvRightText.setOnClickListener(l);
        } else {
            mBtnRight.setOnClickListener(l);
        }
    }

    public void setRightImageClickListener(OnClickListener l) {
        mBtnRight.setOnClickListener(l);
    }

    public void setRightBtnLongClickListener(OnLongClickListener l) {
        if (mRightTextMode) {
            mTvRightText.setOnLongClickListener(l);
        } else {
            mBtnRight.setOnLongClickListener(l);
        }
    }

    public TextView getBtnRightText() {
        mTvRightText.setVisibility(View.VISIBLE);
        return mTvRightText;
    }

    public void setTitleRightDrawable(int resIdRight) {
        Drawable right = getResources().getDrawable(resIdRight);
        mTvTitle.setCompoundDrawablesWithIntrinsicBounds(null, null, right, null);
    }

    public void setTitleRightDrawable(Bitmap bitmap) {
        BitmapDrawable drawableright = new BitmapDrawable(getResources(), bitmap);
        mTvTitle.setCompoundDrawablesWithIntrinsicBounds(null, null, drawableright, null);
    }

    public void setBottomLineVisibility(boolean visible) {
        mViewDividerBottom.setVisibility(visible ? View.GONE : View.GONE);
    }

    public void setBackGroundColor(int resColor) {
        setBackgroundColor(mContext.getResources().getColor(resColor));
    }
}
