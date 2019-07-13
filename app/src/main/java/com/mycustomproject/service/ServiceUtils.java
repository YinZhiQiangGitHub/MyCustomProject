package com.mycustomproject.service;

import android.app.ActivityManager;
import android.content.Context;

import java.util.List;

public class ServiceUtils {

    /**
     * 校验某个服务是否还活着
     * @param serviceName 传进来服务的名称
     */
    public static boolean isServiceRunning(Context context, String serviceName) {
        // 校验服务是否还活着
        ActivityManager am = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> infos = am.getRunningServices(100);
        for (ActivityManager.RunningServiceInfo info : infos) {
            String name = info.service.getClassName();
            if (name.equals(serviceName)) {
                return true;
            }
        }
        return false;
    }
}
