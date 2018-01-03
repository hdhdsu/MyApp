package com.xinguang.myapp.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.xinguang.myapp.R;

/**
 * 引导页的adapter
 */
public class GuideViewPagerAdapter extends PagerAdapter {



    public static final Integer[] drawes={
        R.drawable.slide1,
                R.drawable.slide2,
                R.drawable.slide3,
                R.drawable.slide4};



    @Override
    public int getCount() {
        return drawes == null ? 0 : drawes.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View inflate= LayoutInflater.from(container.getContext()).inflate(R.layout.guide_item,null);
        ImageView circle_1 = (ImageView) inflate.findViewById(R.id.circle_1);
        ImageView circle_2 = (ImageView) inflate.findViewById(R.id.circle_2);
        ImageView circle_3 = (ImageView) inflate.findViewById(R.id.circle_3);
        ImageView circle_4 = (ImageView) inflate.findViewById(R.id.circle_4);
        RelativeLayout mBackGround= (RelativeLayout) inflate.findViewById(R.id.rl_backgroud);
        View btn=  inflate.findViewById(R.id.btn_next);
        View skip=inflate.findViewById(R.id.skip);
        if (position==0){
            btn.setVisibility(View.GONE);
            skip.setVisibility(View.VISIBLE);
            circle_1.setBackground(container.getResources().getDrawable(R.drawable.circle_2));
            circle_2.setBackground(container.getResources().getDrawable(R.drawable.circle_1));
            circle_3.setBackground(container.getResources().getDrawable(R.drawable.circle_1));
            circle_4.setBackground(container.getResources().getDrawable(R.drawable.circle_1));
            mBackGround.setBackground(container.getResources().getDrawable(R.drawable.slide1));
            skip.setVisibility(View.VISIBLE);
        }else if (position==1){
            btn.setVisibility(View.GONE);
            skip.setVisibility(View.VISIBLE);
            circle_1.setBackground(container.getResources().getDrawable(R.drawable.circle_1));
            circle_2.setBackground(container.getResources().getDrawable(R.drawable.circle_2));
            circle_3.setBackground(container.getResources().getDrawable(R.drawable.circle_1));
            circle_4.setBackground(container.getResources().getDrawable(R.drawable.circle_1));
            mBackGround.setBackground(container.getResources().getDrawable(R.drawable.slide2));
        }else if(position==2){
            btn.setVisibility(View.GONE);
            skip.setVisibility(View.VISIBLE);
            circle_1.setBackground(container.getResources().getDrawable(R.drawable.circle_1));
            circle_2.setBackground(container.getResources().getDrawable(R.drawable.circle_1));
            circle_3.setBackground(container.getResources().getDrawable(R.drawable.circle_2));
            circle_4.setBackground(container.getResources().getDrawable(R.drawable.circle_1));
            mBackGround.setBackground(container.getResources().getDrawable(R.drawable.slide3));
        }else {
            btn.setVisibility(View.VISIBLE);
            skip.setVisibility(View.GONE);
            circle_1.setBackground(container.getResources().getDrawable(R.drawable.circle_1));
            circle_2.setBackground(container.getResources().getDrawable(R.drawable.circle_1));
            circle_3.setBackground(container.getResources().getDrawable(R.drawable.circle_1));
            circle_4.setBackground(container.getResources().getDrawable(R.drawable.circle_2));
            mBackGround.setBackground(container.getResources().getDrawable(R.drawable.slide4));
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mGuideClickLinstener!=null){
                    mGuideClickLinstener.click();
                }
            }
        });
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mGuideClickLinstener!=null){
                    mGuideClickLinstener.click();
                }
            }
        });
        container.addView(inflate);
        return inflate;
    }

    public GuideViewPagerAdapter(GuideClickLinstener mGuideClickLinstener) {
        this.mGuideClickLinstener = mGuideClickLinstener;
    }

    private GuideClickLinstener mGuideClickLinstener;
    public interface GuideClickLinstener{
        void click();
    }
    @Override
    public void destroyItem(ViewGroup view, int position, Object object) {
        view.removeView((View) object);
    }

}
