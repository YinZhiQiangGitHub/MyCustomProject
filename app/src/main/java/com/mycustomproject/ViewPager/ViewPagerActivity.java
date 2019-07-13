package com.mycustomproject.ViewPager;

import android.graphics.Color;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mycustomproject.R;
import com.mycustomproject.main.baseUtil.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends BaseActivity {

    private ViewPager viewPager;
    private List<TextView> list=new ArrayList<>();

    @Override
    public int getLayoutResId() {
        return R.layout.activity_view_pager;
    }

    @Override
    public void initBindingID() {
        viewPager =findViewById(R.id.viewPager);
    }

    @Override
    public void initView() {
        pager01();
        pager02();
    }






    private void pager02(){
        LoopViewPager viewpager =findViewById(R.id.loopViewpager);
        viewpager.setAdapter(new SamplePagerAdapter());
        viewpager.setBoundaryCaching(true);
        viewpager.setBoundaryLooping(true);
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int arg0) {
//               Log_i("----setOnPageChangeListener------------------------->"+arg0);
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                switch (arg0) {
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        Log_i("----setOnPageChangeListener------------------------->"+arg0);
                        break;
                    case ViewPager.SCROLL_STATE_IDLE:
                        Log_i("----setOnPageChangeListener------------------------->"+arg0);
                        break;
                    default:
                        break;
                }
            }
        });


    }

    public class SamplePagerAdapter extends PagerAdapter {
       @Override
        public int getCount() {
            return 10;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup view, int position, Object object) {
            view.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup view, int position) {
            TextView textView = new TextView(view.getContext());
            textView.setText((position + 1 )+ "");
            if (textView==getCurrentFocus()){
                textView.setTextColor(Color.RED);
            }
//                setCurrentItem
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(Color.WHITE);
            view.addView(textView);
            return textView;
        }
    }

    private void pager01(){
        for(int i=1;i<11;i++){
            TextView text=new TextView(this);
            text.setText(""+i);
            text.setTextColor(getResources().getColor(R.color.withe));
            list.add(text);
        }
        ViewPagerAdapter adapter1 = new ViewPagerAdapter(list);
        viewPager.setOffscreenPageLimit(5);
        viewPager.setAdapter(adapter1);

    }
}
