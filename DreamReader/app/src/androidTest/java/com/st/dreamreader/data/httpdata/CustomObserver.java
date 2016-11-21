package com.st.dreamreader.data.httpdata;

import android.util.Log;

import rx.Observer;

/**
 * Created by xudong.tang on 16/11/21.
 */

public class CustomObserver<T> implements Observer<T> {
    private boolean flag = false;
    private Object mLock = null;
    private static String TAG = "CustemObserver";

    public CustomObserver(Object lock){
        mLock = lock;
    }
    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        flag=false;
        Log.e(TAG,e.getMessage());
        synchronized (mLock){
            mLock.notifyAll();
        }
    }

    @Override
    public void onNext(T o) {
        Log.e(TAG,"request is success!");
        flag = true;
        synchronized (mLock){
            mLock.notifyAll();
        }
    }

    public boolean isSuccess(){
        return this.flag;
    }
}
