package com.mycustomproject.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.mycustomproject.main.baseUtil.LogUtils;
import com.mycustomproject.okhttp3.DownloadUtil;
import com.mycustomproject.okhttp3.OnDownloadListener;

import java.io.File;
import java.util.Random;

public class ServiceTest02 extends Service {
    private String TAG="ServiceTest02";
    //通过binder实现调用者client与Service之间的通信
    private MyBinder binder = new MyBinder();
    private final Random generator = new Random();



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
        LogUtils.i(TAG+"------------Service被创建时调用------------>onStart");
    }

    /**
     * 必须实现的方法
     * @param intent
     * @return
     */
    @Override
    public IBinder onBind(Intent intent) {
        LogUtils.i(TAG+"-----------必须实现的方法------------->onBind");
        if (intent.getStringExtra("from").equals("ActivityA"))
        binder.setStringA("OK");
        return binder;
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
        return START_NOT_STICKY;

    }

    /**
     * 当停止Service时调用
     * @param intent
     * @return
     */
    @Override
    public boolean onUnbind(Intent intent) {
        LogUtils.i(TAG+"------------------------>onUnbind");
        return false;
    }

    /**
     * Service被销毁时调用
     */
    @Override
    public void onDestroy() {
        LogUtils.i(TAG+"----------Service被销毁时调用-------------->onUnbind");
        super.onDestroy();
    }



    /**
     * 通过binder实现调用者client与Service之间的通信
     */
    public  class MyBinder extends Binder{
        private String name="";
        public ServiceTest02 getService(){
            return ServiceTest02.this;
        }
        public void setStringA(String name){
            this.name=name;
        }
        public String getStringA(){
            return name;
        }

        /**
         *
         * @param URL
         * @param rootDie
         */
        public void loadGif(String[] URL,String rootDie){
            for(int i=0;i<URL.length;i++){
                final int finalI = i;
                DownloadUtil.get().downLoadGif(URL[i], rootDie, "gif_0"+i+".gif", new OnDownloadListener() {
                    @Override
                    public void onDownloadSuccess(File file) {
                        LogUtils.i(TAG+"------------"+ finalI +"------------->>成功下载，地址："+file.getPath());
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
        }


    }

    /**
     * getRandomNumber是Service暴露出去供client调用的公共方法
     * @return
     */
    public int getRandomNumber() {
        return generator.nextInt();
    }

}
