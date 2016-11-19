package com.st.dreamreader.myapplication;

import android.app.Activity;
import android.app.Application;

import com.st.dreamreader.MainActivity;
import com.xiaochao.lcrapiddeveloplibrary.Exception.core.Recovery;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义Application
 * 用于初始化数据及服务
 * Created by xudong.tang on 16/11/19.
 */

public class MyApplication extends Application {

    private static  MyApplication myApplication;
    private List<Activity> activities = new ArrayList<Activity>();

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;

        //异常记录
        Recovery.getInstance().debug(true).recoverInBackground(true).recoverStack(true).mainPage(MainActivity.class).init(this);
    }

    /**
     * 获取MyApplication实例
     * @return
     */
    public static MyApplication getInstance() {
        return myApplication;
    }

    /**
     * 管理activity
     * @param activity
     */
    public void addActivity(Activity activity) {
        if (activities != null)
            this.activities.add(activity);
    }

    /**
     * 关闭activity
     * @param activity
     */
    public void finishActivity(Activity activity){
        if(activities!=null){
            this.activities.remove(activity);
            activity.finish();
        }
    }

    /**
     * 程序退出
     */
    public void exit(){
        for(Activity activity:activities){
            activities.remove(activity);
            activity.finish();
        }

        System.exit(0);
    }
}
