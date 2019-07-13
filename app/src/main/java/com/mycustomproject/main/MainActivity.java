package com.mycustomproject.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.TypedArray;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mycustomproject.EventBus.EventBusActivity;
import com.mycustomproject.HorizontalselectedView.SelectViewActivity;
import com.mycustomproject.Image.LoginActivity;
import com.mycustomproject.R;
import com.mycustomproject.Thread.AsyncTask01Activity;
import com.mycustomproject.ViewPager.ViewPagerActivity;
import com.mycustomproject.http.MainHttpTestActivity;
import com.mycustomproject.main.baseUtil.BaseActivity;
import com.mycustomproject.main.baseUtil.LogUtils;
import com.mycustomproject.listener.View1ClickListener;
import com.mycustomproject.recyclerView.ScrollPickerActivity;
import com.mycustomproject.service.ServiceMain01Activity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private MainAdapter adapter;
    private List<String> list;
    private TypedArray typedArray;
    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public void initBindingID() {
        recyclerView = findViewById(R.id.Recyclerview);
    }

    @Override
    public void initView() {
        list=new ArrayList<>();
        typedArray = getResources().obtainTypedArray(R.array.recycler_list);
        if (typedArray!=null){
            for (int i=0;i<typedArray.length();i++){
                list.add(typedArray.getString(i));
            }
        }
        LogUtils.i("-----------MainActivity-------------->"+typedArray.length());
        LogUtils.i("-----------MainActivity-------------->"+list.toString());

        @SuppressLint("WrongConstant") LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        //设置布局管理器
        recyclerView.setLayoutManager(manager);
        adapter=new MainAdapter(this,list);
        recyclerView.setAdapter(adapter);
        adapter.setlView1ClickListener(new View1ClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onCliick(int i) {
                Intent it=new Intent();
                if (list.get(i).equals(typedArray.getString(0))){
                    it.setClass(mContext,MainHttpTestActivity.class);
                    startActivity(it);
                }else if (list.get(i).equals(""+typedArray.getString(1))){
                    ToastLongShow(1+"");
                    it.setClass(mContext,ServiceMain01Activity.class);
                    startActivity(it);
                }else if (list.get(i).equals(""+typedArray.getString(2))){
                    ToastLongShow(2+"");
                }else if (list.get(i).equals(""+typedArray.getString(3))){
                    ToastLongShow(3+"");
                }else if (list.get(i).equals(""+typedArray.getString(4))){

                }else if (list.get(i).equals(""+typedArray.getString(5))){
                    it.setClass(mContext,AsyncTask01Activity.class);
                    startActivity(it);
                }else if (list.get(i).equals(""+typedArray.getString(6))){
                    it.setClass(mContext,SelectViewActivity.class);
                    startActivity(it);
                }else if (list.get(i).equals(""+typedArray.getString(7))){
                    it.setClass(mContext,ViewPagerActivity.class);
                    startActivity(it);
                }else if (list.get(i).equals(""+typedArray.getString(8))){
                    it.setClass(mContext,ScrollPickerActivity.class);
                    startActivity(it);
                }else if (list.get(i).equals(""+typedArray.getString(9))){
                    it.setClass(mContext,EventBusActivity.class);
                    startActivity(it);
                }else if (list.get(i).equals(""+typedArray.getString(10))){
                    it.setClass(mContext,LoginActivity.class);
                    startActivity(it);
                }else if (list.get(i).equals(""+typedArray.getString(11))){

                }else if (list.get(i).equals(""+typedArray.getString(12))){

                }else if (list.get(i).equals(""+typedArray.getString(13))){

                }else if (list.get(i).equals(""+typedArray.getString(14))){

                }else if (list.get(i).equals(""+typedArray.getString(15))){

                }else if (list.get(i).equals(""+typedArray.getString(16))){

                }else if (list.get(i).equals(""+typedArray.getString(17))){

                }else if (list.get(i).equals(""+typedArray.getString(18))){

                }else if (list.get(i).equals(""+typedArray.getString(20))){

                }else if (list.get(i).equals(""+typedArray.getString(21))){

                }else if (list.get(i).equals(""+typedArray.getString(22))){

                }else if (list.get(i).equals(""+typedArray.getString(23))){

                }else if (list.get(i).equals(""+typedArray.getString(24))){

                }else if (list.get(i).equals(""+typedArray.getString(25))){

                }else if (list.get(i).equals(""+typedArray.getString(26))){

                }else if (list.get(i).equals(""+typedArray.getString(27))){

                }else if (list.get(i).equals(""+typedArray.getString(28))){

                }else if (list.get(i).equals(""+typedArray.getString(29))){

                }
            }
        });

    }
}
