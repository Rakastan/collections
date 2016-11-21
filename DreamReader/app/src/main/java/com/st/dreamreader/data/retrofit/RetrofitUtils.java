package com.st.dreamreader.data.retrofit;

import com.st.dreamreader.constant.Constant;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by xudong.tang on 16/11/19.
 */

public class RetrofitUtils {

    private static Retrofit mRetrofit ;
    private static OkHttpClient mOkHttpClient;

    /**
     * Retrofit工具类
     * @return
     */
    public static Retrofit getRetrofit(){
        if(mRetrofit == null){
            if(mOkHttpClient == null){
                mOkHttpClient = OKHttp3Utils.getOKHttpClient();
            }

            mRetrofit = new Retrofit.Builder().baseUrl(Constant.API_SERVER+ "/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(mOkHttpClient).build();
        }

        return mRetrofit;
    }
}
