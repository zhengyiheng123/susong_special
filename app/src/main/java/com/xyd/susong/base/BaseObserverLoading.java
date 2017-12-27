package com.xyd.susong.base;

import android.app.Activity;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.xyd.susong.promptdialog.PromptDialog;
import com.xyd.susong.utils.LogUtil;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.adapter.rxjava2.HttpException;

/**
 * @date: 2017/4/17
 * @time: 17:28
 * @description:
 */

public abstract class BaseObserverLoading<T> implements Observer<BaseModel<T>> {
    private int errorCode=0;//0弹出吐司，1，显示网络连接错误界面
    private static final String TAG = "BaseObserver";
    private Context mContext;
    private final PromptDialog dialog;

    protected BaseObserverLoading(Activity activity) {
        //this.mContext = context.getApplicationContext();
        dialog = new PromptDialog(activity);
    }

    @Override
    public void onNext(BaseModel<T> value) {
        LogUtil.e(value.getCode()+"");
        T t = value.getData();
        Gson gson=new Gson();
        if (value.getCode()==1) {
            LogUtil.e("success",gson.toJson(t));
            onHandleSuccess(t,value.getMessage(),1);

        }else if(value.getCode()==2){
//            Intent intent=new Intent(MyApplication.getCtx(),LoginActivity.class);
//              intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            MyApplication.context.startActivity(intent);
//            ToastUtils.show("登录状态已过期，您需要重新登录");
        } else {
            LogUtil.e("error",gson.toJson(t));
            LogUtil.e("error",value.getMessage());
            onHandleError(0,value.getMessage());
        }
    }

    @Override
    public void onError(Throwable e) {
        dialog.showError("加载失败");
        LogUtil.e(e.toString());
        if (e instanceof SocketTimeoutException) {
            errorCode =1;
            onHandleError(errorCode,"网络中断，请检查您的网络状态");
        } else if (e instanceof ConnectException) {
            onHandleError(1,"连接异常");
        } else if (e instanceof UnknownHostException) {
            onHandleError(1,"找不到主机");
        } else if (e instanceof JsonSyntaxException) {
            onHandleError(0,"数据解析异常");
        } else if (e instanceof JSONException) {
            onHandleError(0,"数据解析异常");
        } else if (e instanceof JsonParseException) {
            onHandleError(0,"数据解析异常");
        } else if (e instanceof HttpException) {
            onHandleError(0,"网页异常");
        } else if (e instanceof retrofit2.HttpException) {
            onHandleError(0,"网页异常");
        } else {
            onHandleError(0,"未知错误");
        }
    }

    @Override
    public void onComplete() {
//        Log.d(TAG, "onComplete");
        dialog.showSuccess("加载成功");
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    protected abstract void onHandleSuccess(T t,String msg,int code);

    protected abstract void onHandleError(int errorCode,String msg) ;
}