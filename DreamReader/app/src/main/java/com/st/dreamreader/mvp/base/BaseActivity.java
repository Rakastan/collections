package com.st.dreamreader.mvp.base;

import android.content.Context;
import android.content.pm.ActivityInfo;
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

    private Context mContext;
    private ConnectivityManager mConnectivityManager = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//锁定竖屏
        mContext = getApplicationContext();
        initView();
        ButterKnife.bind(this);
        initData();
    }

    private void initView() {
        loadViewLayout();
    }

    private void initData() {
        findViewById();
        setListener();
        processLogic();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.getInstance().finishActivity(this);
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
     * @param text
     */
    public void showToast(String text){
        Toast toast = Toast.makeText(mContext,text,Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

    public boolean checkNetworkState(){
        mConnectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        if(mConnectivityManager.getActiveNetworkInfo()!=null){
            return mConnectivityManager.getActiveNetworkInfo().isAvailable();
        }
        return false;
    }

}
