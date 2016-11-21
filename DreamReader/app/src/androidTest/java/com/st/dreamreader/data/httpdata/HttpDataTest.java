package com.st.dreamreader.data.httpdata;

import android.test.AndroidTestCase;
import com.st.dreamreader.mvp.entity.BookInfoDto;
import com.st.dreamreader.mvp.entity.BookInfoListDto;
import com.st.dreamreader.mvp.entity.BookTypeDto;
import com.st.dreamreader.mvp.entity.HomeDto;
import java.util.List;

/**
 * Created by xudong.tang on 16/11/21.
 */

public class HttpDataTest<T> extends AndroidTestCase{
    HttpData mhttpData = null;
    private final static String TAG = "HttpDataTest";
    private static Object lock = new Object();

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mhttpData = HttpData.getInstance();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        mhttpData = null;
    }

    /**
     * 图书种类列表
     */
    public void testGetBookTypes(){
        doRequest(RequestType.BOOKTYPES);
    }

    /**
     * 获取首页配置
     */
    public void testGetHomeInfo(){
        doRequest(RequestType.HOMEINFO);
    }


    /**
     * 获取热门标签
     */
    public void testGetHotLabel(){
        doRequest(RequestType.SEARCHLABEL);
    }

    /**
     * 获取图书列表
     */
    public void testGetBookList(){
        doRequest(RequestType.BOOKLIST);
    }

    /**
     * 获取搜索结果
     */
    public void testGetSearchList(){
        doRequest(RequestType.SEARCHLIST);
    }

    /**
     * 获取图书信息
     */
    public void testGetBookInfo(){
        doRequest(RequestType.BOOKINFO);
    }

    private void doRequest(RequestType requestType){
        CustomObserver observer = null;
        synchronized (lock){
            switch (requestType){
                case BOOKTYPES:
                    observer = new CustomObserver<List<BookTypeDto>>(lock);
                    mhttpData.getBookTypes(observer);
                    break;
                case HOMEINFO:
                    observer = new CustomObserver<HomeDto>(lock);
                    mhttpData.getHomeInfo(false,observer);
                    break;
                case SEARCHLABEL:
                    observer = new CustomObserver<List<String>>(lock);
                    mhttpData.getSearchLable(observer);
                    break;
                case BOOKLIST:
                    observer = new CustomObserver<List<BookInfoListDto>>(lock);
                    mhttpData.getBookList(1,1,observer);
                    break;
                case SEARCHLIST:
                    observer = new CustomObserver<List<BookInfoListDto>>(lock);
                    mhttpData.getSearchList("",observer);
                    break;
                case BOOKINFO:
                    observer = new CustomObserver<BookInfoDto>(lock);
                    mhttpData.getBookInfo(0,observer);
                    break;
                default:
                    break;
            }

            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        assertTrue(observer.isSuccess());
    }

    enum RequestType{
        BOOKTYPES,HOMEINFO,SEARCHLABEL,BOOKLIST,SEARCHLIST,BOOKINFO
    }

}
