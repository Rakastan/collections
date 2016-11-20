package com.st.dreamreader.data.httpdata;

import com.st.dreamreader.data.api.BookService;
import com.st.dreamreader.data.api.CacheProviders;
import com.st.dreamreader.data.retrofit.RetrofitUtils;
import com.st.dreamreader.util.FileUtil;

import java.io.File;

import io.rx_cache.internal.RxCache;

/**
 * Created by xudong.tang on 16/11/19.
 */

public class HttpData extends RetrofitUtils {

    private File cacheDirectory = FileUtil.getcacheDirectory();
    private CacheProviders cacheProviders = new RxCache.Builder().persistence(cacheDirectory).using(CacheProviders.class);
    protected final BookService bookService = getRetrofit().create(BookService.class);

    /**
     * 访问HttpData 方法时创建
     */
    private static class SingletonHolder {
        private static final HttpData INSTANCE = new HttpData();
    }

    public static HttpData getInstance(){
        return SingletonHolder.INSTANCE;
    }

}
