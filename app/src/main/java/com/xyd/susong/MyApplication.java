package com.xyd.susong;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.common.QueuedWork;
import com.xyd.susong.base.PublicStaticData;
import com.xyd.susong.utils.MyCrashHandler;
import com.xyd.susong.utils.Utils;

/**
 * @author: zhaoxiaolei
 * @date: 2017/6/12
 * @time: 11:48
 * @description:
 */

public class MyApplication extends Application {
    public static  Context context;
    private static  MyApplication application;
    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        refWatcher=  LeakCanary.install(this);


        application=this;
        context=this;
        Utils.init(this);
        UMShareAPI.get(this);
        QueuedWork.isUseThreadPool = false;
        Config.DEBUG = true;
        PublicStaticData.sharedPreferences=getSharedPreferences("wine",MODE_PRIVATE);
        initShare();
        EMHelper.getInstance().init(this);
//        initLog();
    }

    private void initLog() {
        MyCrashHandler crashHandler=new MyCrashHandler();
        crashHandler.init(getApplicationContext());
    }

    private void initShare() {
        UMShareConfig config = new UMShareConfig();
        config.isNeedAuthOnGetUserInfo(true);
        config.isSinaAuthWithWebView();
        config.setSinaAuthType(UMShareConfig.AUTH_TYPE_SSO);
        UMShareAPI.get(this).setShareConfig(config);

        PlatformConfig.setWeixin("wxdb713d24bde112f2","5312576688a2a85b0d104586a0b6deb8");
        PlatformConfig.setQQZone("1106621738","Aq82uquPmRaCXBOE");//新的
        PlatformConfig.setSinaWeibo("3682336461","9627d602515aa7e9be6914da06166886","http://open.weibo.com/apps/1866127300/privilege/oauth");//新的
    }
    public static MyApplication getInstance(){
       return application;
    }
    public  static Context getContext(){
        return context;
    }

    public static RefWatcher getRefWatcher(Context context) {
        MyApplication application = (MyApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    private RefWatcher refWatcher;

}
