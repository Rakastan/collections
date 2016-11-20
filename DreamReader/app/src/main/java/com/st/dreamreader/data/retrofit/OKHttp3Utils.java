package com.st.dreamreader.data.retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by xudong.tang on 16/11/19.
 */

public class OKHttp3Utils {
    private static OkHttpClient mOKHttpClient;

    public static OkHttpClient getOKHttpClient() {
        if (mOKHttpClient == null) {
            mOKHttpClient = new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).build();
        }
        return mOKHttpClient;
    }
}
