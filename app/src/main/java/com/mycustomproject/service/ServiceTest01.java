package com.mycustomproject.service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.os.IBinder;

import com.mycustomproject.main.baseUtil.LogUtils;
import com.mycustomproject.okhttp3.DownloadUtil;
import com.mycustomproject.okhttp3.OnDownloadListener;

import java.io.File;

public class ServiceTest01 extends Service {
    private String TAG="ServiceTest01";
    /* SD卡根目录 */
    private String rootDie;
    private String[] URL={"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561097762696&di=26a235d601b78c7beb6be6cfa3d99d89&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20171204%2F9e6f1e5e952f44e08eb56c08d0224f6a.gif",
            "http://img1.ph.126.net/JOeJn1GoZTSQw4iUMpINgA==/1283244418842816676.gif",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561097960406&di=26900c282d510ecf69952d26fd3f3edb&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20171019%2F2d2596c39b2543d6948548c68c93cbfa.gif",
            "http://img0.ph.126.net/brj8NGCGr4jhSruNKAJrqQ==/6630428949444357765.gif",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561098246949&di=66f78890588cb3108068503917127555&imgtype=0&src=http%3A%2F%2Fimg.mp.sohu.com%2Fupload%2F20170521%2F7f67013744fe4183905efdd0fb9af984_th.png"};


    private    SharedPreferences mShared;
    /**
     * Service被创建时调用
     */
    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.i(TAG+"------------Service被创建时调用------------>onCreate");
    }


    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        LogUtils.i(TAG+"------------------------>onStart");
        mShared= getSharedPreferences("LED_gif", MODE_PRIVATE);
        /* 获取sdcard目录 */
//        rootDie = Environment.getExternalStorageDirectory() + getApplicationContext().getFilesDir().getAbsolutePath() + "/gif/";
    }

    /**
     * 必须实现的方法
     * @param intent
     * @return
     */
    @Override
    public IBinder onBind(Intent intent) {
        LogUtils.i(TAG+"-----------必须实现的方法------------->onBind");
        return null;
    }

    /**
     * Service被启动时调用
     * 当onCreate被创建后，再次调用Service时，只会执行onStartCommand方法（startService执行多少次，onStartCommand就会执行多少次）
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtils.i(TAG+"--------- Service被启动时调用--------------->onStartCommand");

        for(int i=0;i<URL.length;i++){
            final int finalI = i;
            DownloadUtil.get().downLoadGif(URL[i], intent.getStringExtra("path"), "gif_0"+i+".gif", new OnDownloadListener() {
                @SuppressLint("ApplySharedPref")
                @Override
                public void onDownloadSuccess(File file) {
                    LogUtils.i(TAG+"------------"+ finalI +"------------->>成功下载，地址："+file.getPath());
                    SharedPreferences.Editor editor=mShared.edit();
                    editor.putString("LED_gif"+finalI,""+file.getPath());
                    editor.commit();
                }

                @Override
                public void onDownloading(int progress) {
                    LogUtils.i(TAG+"------------"+ finalI +"------------->>下载进度："+progress);
                }

                @Override
                public void onDownloadFailed(Exception e) {
                    LogUtils.i(TAG+"------------"+ finalI +"------------->>下载异常："+e.getMessage());
                }
            });

        }



        return super.onStartCommand(intent, flags, startId);
    }


    /**
     * Service被销毁时调用
     */
    @Override
    public void onDestroy() {
        LogUtils.i(TAG+"----------Service被销毁时调用-------------->onUnbind");
        super.onDestroy();
    }

}
