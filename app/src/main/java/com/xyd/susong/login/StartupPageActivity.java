package com.xyd.susong.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;


import com.hyphenate.chat.ChatClient;
import com.hyphenate.helpdesk.callback.Callback;
import com.xyd.susong.MyApplication;
import com.xyd.susong.R;
import com.xyd.susong.api.MineApi;
import com.xyd.susong.base.BaseApi;
import com.xyd.susong.base.BaseModel;
import com.xyd.susong.base.BaseObserver;
import com.xyd.susong.base.EmptyModel;
import com.xyd.susong.base.PublicStaticData;
import com.xyd.susong.base.RxSchedulers;
import com.xyd.susong.main.MainActivity;
import com.xyd.susong.permissions.PermissionsManager;
import com.xyd.susong.personinformation.InfromationModel;
import com.xyd.susong.test.TestService;

import butterknife.Bind;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author: zhaoxiaolei
 * @date: 2017/8/2
 * @time: 17:13
 * @description:
 */

public class StartupPageActivity extends Activity {
    @Bind(R.id.start_vp)
    ViewPager startVp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up);
        initView();
//        test();
    }

    private void test(){
        BaseApi.getRetrofit().create(TestService.class)
                .test2().compose(RxSchedulers.<BaseModel<EmptyModel>>compose())
                .subscribe(new BaseObserver<EmptyModel>() {
                    @Override
                    protected void onHandleSuccess(EmptyModel emptyModel, String msg, int code) {

                    }

                    @Override
                    protected void onHandleError(String msg) {

                    }
                });
    }
    protected void initView() {
        BaseApi.getRetrofit()
                .create(MineApi.class)
                .information()
                .compose(RxSchedulers.<BaseModel<InfromationModel>>compose())
                .subscribe(new Observer<BaseModel<InfromationModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseModel<InfromationModel> infromationModelBaseModel) {
                        if (infromationModelBaseModel.getCode() == 1){
                            InfromationModel model=infromationModelBaseModel.getData();
                            PublicStaticData.sharedPreferences.edit().putInt("id", Integer.valueOf(model.getUserid())).commit();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    startActivity(new Intent(StartupPageActivity.this, MainActivity.class));
                                    finish();

                                }
                            }, 2000);
                        }else if (infromationModelBaseModel.getCode() == 2){
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent=new Intent(MyApplication.context,LoginActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    MyApplication.context.startActivity(intent);
                                }
                            }, 2000);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                startActivity(new Intent(StartupPageActivity.this, LoginActivity.class));
                                finish();

                            }
                        }, 2000);
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionsManager.getInstance().notifyPermissionsChange(permissions,grantResults);
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    private void login(final String uname, final String upwd) {


        // login huanxin server
        ChatClient.getInstance().login(uname, upwd, new Callback() {
            @Override
            public void onSuccess() {
                Log.e("StartupPage", "demo login success!");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                startActivity(new Intent(StartupPageActivity.this, MainActivity.class));
                                finish();

                            }
                        }, 2000);
                    }
                });

            }

            @Override
            public void onError(int code, String error) {
                Log.e("", "login fail,code:" + code + ",error:" + error);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                startActivity(new Intent(StartupPageActivity.this, LoginActivity.class));
                                finish();

                            }
                        }, 2000);
                    }
                });

            }

            @Override
            public void onProgress(int progress, String status) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                startActivity(new Intent(StartupPageActivity.this, LoginActivity.class));
                                finish();

                            }
                        }, 2000);
                    }
                });

            }
        });
    }

}
