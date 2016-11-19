package com.st.dreamreader.myapplication;

import android.app.Activity;
import android.app.Application;

import com.st.dreamreader.MainActivity;
import com.xiaochao.lcrapiddeveloplibrary.Exception.core.Recovery;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义applica 用于程序初始化
 * Created by xudong.tang on 16/11/19.
 */

public class MyApplication extends Application {

    private static MyApplication myApplication;
    private List<Activity> activities = new ArrayList<Activity>();

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;

        Recovery.getInstance().recoverInBackground(true).debug(true).mainPage(MainActivity.class).recoverStack(true).init(this);
    }

    /**
     * @return
     */
    public static MyApplication getInstance() {
        return myApplication;
    }

    /**
     * 统一管理activity
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        if (activities != null) {
            activities.add(activity);
        }
    }

    /**
     * 关闭activity
     *
     * @param activity
     */
    public void finish(Activity activity) {
        if (activities != null) {
            activities.remove(activity);
            activity.finish();
        }
    }

    /**
     * 退出
     */
    public void exit() {
        for (Activity activity : activities) {
            activity.finish();
        }
        System.exit(0);
    }
}
