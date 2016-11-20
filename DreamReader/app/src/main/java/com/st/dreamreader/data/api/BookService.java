package com.st.dreamreader.data.api;

import com.st.dreamreader.mvp.entity.BookInfoDto;
import com.st.dreamreader.mvp.entity.BookInfoListDto;
import com.st.dreamreader.mvp.entity.BookTypeDto;
import com.st.dreamreader.mvp.entity.HomeDto;
import com.st.dreamreader.mvp.entity.HttpResult;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by xudong.tang on 16/11/19.
 */

public interface BookService {
    //获取首页详情
    @GET("api/getHomeInfo")
    Observable<HttpResult<HomeDto>> getHomeInfo();

    //获取书籍详情
    @GET("api/getBookInfo")
    Observable<HttpResult<BookInfoDto>> getBookInfo(@Query("id") int id);

    //获取类别列表
    @GET("api/getTypeConfigList")
    Observable<HttpResult<List<BookTypeDto>>> getTypeList();

    //根据类别获取书籍列表
    @GET("api/getTypeBooks")
    Observable<HttpResult<List<BookInfoListDto>>> getBookList(@Query("type")int type, @Query("pageIndex")int pageIndex);

    //根据关键词获取搜索书籍列表
    @GET("api/getSearchList")
    Observable<HttpResult<List<BookInfoListDto>>> getSearchList(@Query("key")String key);

    //获取热门搜索标签
    @GET("api/getSearchLable")
    Observable<HttpResult<List<String>>> getHotLable();
}