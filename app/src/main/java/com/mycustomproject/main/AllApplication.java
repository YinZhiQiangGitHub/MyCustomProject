package com.mycustomproject.main;

import android.app.Application;
import android.os.StrictMode;

public class AllApplication extends Application {
    private boolean DEV_MODE = true;

    @Override
    public void onCreate() {
        isStrictMode();
        super.onCreate();
    }


    /**
     * 严苛模式StrictMode
     * 使用StrictMode，系统检测出主线程违例的情况并做出相应的反应，最终帮助开发者优化和改善代码逻辑
     */
    public void isStrictMode(){
        if (DEV_MODE) {

            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectNetwork()
                    .penaltyLog()
                    .build());
//            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
//                    .detectCustomSlowCalls() //API等级11，使用StrictMode.noteSlowCode
//                    .detectDiskReads()
//                    .detectDiskWrites()
//                    .detectNetwork()   // or .detectAll() for all detectable problems
//                    .penaltyDialog() //弹出违规提示对话框
//                    .penaltyLog() //在Logcat 中打印违规异常信息
//                    .penaltyFlashScreen() //API等级11
//                    .build());
//            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
//                    .detectLeakedSqlLiteObjects()
//                    .detectLeakedClosableObjects() //API等级11
//                    .penaltyLog()
//                    .penaltyDeath()
//                    .build());
        }
    }
}
