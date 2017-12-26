package com.xyd.susong.base;



import com.xyd.susong.MyApplication;
import com.xyd.susong.cookie.PersistentCookieStore;
import com.xyd.susong.utils.LogUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author: zhaoxiaolei
 * @date: 2017/4/17
 * @time: 14:51
 * @description:
 */

public class BaseApi {
    private static Retrofit sRetrofit ;
    private static OkHttpClient sOkHttpClient ;
    private static BaseApi network;

    public static Retrofit getRetrofit() {
        if (network == null || sRetrofit == null || sOkHttpClient == null) {
            network = new BaseApi();
        }
        return sRetrofit;
    }

    public BaseApi() {
        initOkHttp();
    }

    private static void initOkHttp() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        File cacheFile = new File("cacheFile");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                HttpUrl originalHttpUrl = original.url();

                HttpUrl url = originalHttpUrl.newBuilder()
                        .addQueryParameter("apikey", PublicStaticData.sharedPreferences.getString(PublicStaticData.apikey,""))
                        .build();

                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder()
                        .url(url);

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };
//        builder.cookieJar(new CookiesManager());
        builder.cache(cache).addInterceptor(interceptor);
        //设置超时
        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);

        //设置重连
        builder.retryOnConnectionFailure(true);
        sOkHttpClient = builder.build();
//        http://hj.jiangliping.com/index/
        LogUtil.e("cookieJar",sOkHttpClient.cookieJar().toString());
        sRetrofit = new Retrofit.Builder()
//                .baseUrl("http://app.george-wine.com/index/")
                .baseUrl("http://shop.cinyida.com/index/")
                .client(sOkHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * 自动管理Cookies
     */
    private static class CookiesManager implements CookieJar {
        private final PersistentCookieStore cookieStore = new PersistentCookieStore(MyApplication.getContext());

        @Override
        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
            if (cookies != null && cookies.size() > 0) {
                for (Cookie item : cookies) {
                    cookieStore.add(url, item);
                }
            }
        }

        @Override
        public List<Cookie> loadForRequest(HttpUrl url) {
            List<Cookie> cookies = cookieStore.get(url);
            return cookies;





        }
    }
}