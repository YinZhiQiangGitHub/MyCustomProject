package com.mycustomproject.main.baseUtil;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import android.view.Window;
import android.view.WindowManager;

public abstract class BaseActivity extends Activity {
    protected String TAG = getClass().getSimpleName();
    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayoutResId());
        //禁止自动弹出输入法框
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        mContext = this;
        /// 获取当前版本，如果大于6.0就动态申请读写权限
        if (Build.VERSION.SDK_INT >= 23) {
            verifyStoragePermissions();
        }
        initBindingID();
        initView();
    }

    /**
     * 获取 Activity 的布局 ID
     */
    public abstract int  getLayoutResId();


    /**
     * 写入绑定当前activity的控件id
     */
    public abstract void initBindingID();

    /**
     * Activity 的初始化方法
     */
    public abstract void initView();


    /**
     * 吐丝提示
     *
     * @param msg
     * @param time
     */
    public void ToastTimeShow(String msg, int time) {
        ToastUtils.showToast(this, msg, time);
    }

    public void ToastLongShow(String msg) {
        ToastUtils.showToastLong(this, msg);
    }

    public void ToastShortShow(String msg) {
        ToastUtils.showToastShort(this, msg);
    }

    /**
     * 获取本地资源文件数据
     * @param string
     * @return
     */
    public String getResourToString(int string){
        return this.getResources().getString(string);
    }

    /**
     * 打印日志 i
     * @param msg
     */
    public void Log_i(String msg){
        LogUtils.i(msg);
    }
    /**
     * 打印日志 e
     * @param msg
     */
    public void Log_e(String msg){
        LogUtils.e(msg);
    }
    /**
     * 打印日志 d
     * @param msg
     */
    public void Log_d(String msg){
        LogUtils.d(msg);
    }
    /**
     * 打印日志 i
     * @param msg
     */
    public void Log_w(String msg){
        LogUtils.w(msg);
    }

    /**
     * 打印日志 v
     * @param msg
     */
    public void Log_v(String msg){
        LogUtils.i(msg);
    }

    /**
     * 打印日志 wtf
     * @param msg
     */
    public void Log_wtf(String msg){
        LogUtils.i(msg);
    }


    /**
     * 6.0以上版本的动态权限的申请
     */
    private int REQUEST_EXTERNAL_STORAGE = 1;
    private  String[] PERMISSIONS_STORAGE = { "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.ACCESS_COARSE_LOCATION" };

    /**
     * 6.0以上版本的动态申请权限
     */
    public  void verifyStoragePermissions() {
        try {
            // 检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE");
            int permission1 = ActivityCompat.checkSelfPermission(this, "android.permission.ACCESS_COARSE_LOCATION");
            if (permission != PackageManager.PERMISSION_GRANTED || permission1 != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}