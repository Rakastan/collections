package com.st.dreamreader.mvp.base;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.st.dreamreader.myapplication.MyApplication;

import butterknife.ButterKnife;

/**
 * Created by xudong.tang on 16/11/19.
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener{
    
    private ConnectivityManager mConnectivityManager;
    private Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        ButterKnife.bind(this);
        initData();
        MyApplication.getInstance().addActivity(this);
    }

    private void initData() {
        findViewById();
        setListener();
        processLogic();
    }

    private void initView() {
        loadViewLayout();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.getInstance().finish(this);
    }

    /**
     * 加载页面layout
     */
    protected abstract void loadViewLayout();

    /**
     * 加载页面元素
     */
    protected abstract void findViewById();

    /**
     * 设置各种事件的监听器
     */
    protected abstract void setListener();

    /**
     * 业务逻辑处理，主要与后端交互
     */
    protected abstract void processLogic();


    /**
     * Activity.this
     */
    protected abstract Context getActivityContext();

    /**
     * 弹出Toast
     *
     * @param text
     */
    public void showToast(String text) {
        Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * 检测网络状态
     * @return
     */
    public boolean checkNetworkState() {
        boolean flag = false;
        //得到网络连接信息
        mConnectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        //去进行判断网络是否连接
        if (mConnectivityManager.getActiveNetworkInfo() != null) {
            flag = mConnectivityManager.getActiveNetworkInfo().isAvailable();
        }
        return flag;
    }

    @Override
    public void onClick(View v) {
        
    }
}
