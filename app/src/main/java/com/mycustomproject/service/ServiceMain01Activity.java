package com.mycustomproject.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Environment;
import android.os.IBinder;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

import com.mycustomproject.R;
import com.mycustomproject.main.baseUtil.BaseActivity;
import com.mycustomproject.main.baseUtil.LogUtils;
import com.mycustomproject.okhttp3.DownloadUtil;
import com.mycustomproject.okhttp3.OnDownloadListener;

import java.io.File;

public class ServiceMain01Activity extends BaseActivity implements View.OnClickListener {
    private Button button1,button2,button3,button4,button5;
    private Intent intent01,intent02;

    private ServiceTest02 service02=null;
    private boolean isBind = false;


    /* SD卡根目录 */
    private String rootDie;
    private String[] URL={"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561097762696&di=26a235d601b78c7beb6be6cfa3d99d89&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20171204%2F9e6f1e5e952f44e08eb56c08d0224f6a.gif",
            "http://img1.ph.126.net/JOeJn1GoZTSQw4iUMpINgA==/1283244418842816676.gif",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561097960406&di=26900c282d510ecf69952d26fd3f3edb&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20171019%2F2d2596c39b2543d6948548c68c93cbfa.gif",
            "http://img0.ph.126.net/brj8NGCGr4jhSruNKAJrqQ==/6630428949444357765.gif",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561098246949&di=66f78890588cb3108068503917127555&imgtype=0&src=http%3A%2F%2Fimg.mp.sohu.com%2Fupload%2F20170521%2F7f67013744fe4183905efdd0fb9af984_th.png"};

    @Override
    public int getLayoutResId() {
        return R.layout.activity_service_main01;
    }

    @Override
    public void initBindingID() {
        button1 =  findViewById(R.id.button1);
        button2 =  findViewById(R.id.button2);
        button3 =  findViewById(R.id.button3);
        button4 =  findViewById(R.id.button4);
        button5 =  findViewById(R.id.button5);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
    }

    @Override
    public void initView() {
        rootDie = Environment.getExternalStorageDirectory() + getApplicationContext().getFilesDir().getAbsolutePath() + "/gif/";

        //startService启动方式
        intent01 = new Intent(this,ServiceTest01.class);

        //bindService的启动方式
        intent02 = new Intent(this,ServiceTest02.class);
    }

    /**
     *  Activity 和 Service 之间的通信
     */
    public ServiceConnection  con=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            isBind=true;
           ServiceTest02.MyBinder myBinder= (ServiceTest02.MyBinder) service;
           service02 =myBinder.getService();
//            Log_i(TAG+"ActivityA - onServiceConnected");
//            int num = service02.getRandomNumber();
//            Log_i(TAG+"ActivityA - getRandomNumber = " + num);
//            ToastShortShow(TAG+"---->"+myBinder.getStringA());
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

        /**
         * onServiceDisconnected() 在连接正常关闭的情况下是不会被调用的.
         * 该方法只在Service 被破坏了或者被杀死的时候调用.
         * 例如, 系统资源不足, 要关闭一些Services, 刚好连接绑定的 Service 是被关闭者之一,  这个时候onServiceDisconnected() 就会被调用.
         * @param name
         */
        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBind=false;
            Log_i(TAG+"ActivityA - onServiceDisconnected");
            ToastShortShow(TAG+ "ActivityA - onServiceDisconnected");
        }
    };


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                intent01.putExtra("path", Environment.getExternalStorageDirectory() + getApplicationContext().getFilesDir().getAbsolutePath() + "/gif/");
                startService(intent01);
                break;
            case R.id.button2:
                stopService(intent01);
                break;
            case R.id.button3:
                intent02.putExtra("from", "ActivityA");
                bindService(intent02, con, BIND_AUTO_CREATE);
                break;
            case R.id.button4:
                if (isBind) {
                    isBind=false;
                    unbindService(con);
                }
                break;
            case R.id.button5:
               SharedPreferences mShared= getSharedPreferences("LED_gif", MODE_PRIVATE);
               LogUtils.i("成功下载 的地址------------------------------------->"+mShared.getString("LED_gif0",""));
//                finish();
                break;

        }
    }
}
