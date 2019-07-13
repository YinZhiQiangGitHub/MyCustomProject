package com.mycustomproject.ViewPager;


import androidx.viewpager.widget.PagerAdapter;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {

    private List<TextView> viewlist = new ArrayList<TextView>();// 轮播广告列表

    public  ViewPagerAdapter(List<TextView> list){
        this.viewlist=list;
    }

    @Override
    public int getCount() {
        // 设置成最大，使用户看不到边界
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        // TODO Auto-generated method stub
        return arg0 == arg1;
    }

    @Override
    public float getPageWidth(int position) {
        return 0.2f;
    }


    @Override
    public void destroyItem( ViewGroup container, int position,  Object object) {
//        container.removeView(viewlist.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // 对ViewPager页号求模取出View列表中要显示的项
        position %= viewlist.size();
        TextView view = viewlist.get(position);
        view.setGravity(Gravity.CENTER);
        ViewParent vp = view.getParent();
        if (vp != null) {
            ViewGroup parent = (ViewGroup) vp;
            parent.removeView(view);
        }
        container.addView(view);
        return view;
    }

}
