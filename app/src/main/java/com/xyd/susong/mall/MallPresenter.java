package com.xyd.susong.mall;

import android.app.Activity;

import com.xyd.susong.api.ShopChartApi;
import com.xyd.susong.api.StoreApi;
import com.xyd.susong.base.BaseApi;
import com.xyd.susong.base.BaseModel;
import com.xyd.susong.base.BaseObserver;
import com.xyd.susong.base.EmptyModel;
import com.xyd.susong.base.RxSchedulers;
import com.xyd.susong.promptdialog.PromptDialog;
import com.xyd.susong.store.StoreModel;
import com.xyd.susong.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Zheng on 2017/12/27.
 */

public class MallPresenter implements MallContract.Presenter {
    private MallContract.View view;

    public MallPresenter(MallContract.View view){
        this.view=view;
    }
    @Override
    public void getData(final int page, int type) {
        BaseApi.getRetrofit()
                .create(StoreApi.class)
                .goodsList(page, type)
                .compose(RxSchedulers.<BaseModel<StoreModel>>compose())
                .subscribe(new BaseObserver<StoreModel>() {
                    @Override
                    protected void onHandleSuccess(StoreModel model, String msg, int code) {
                        if (page == 1)
                            view.refreshData(model);
                        else if (model.getData().size()>0)
                            view.loadMoreData(model,1);
                        else
                            view.loadMoreData(model,2);

                    }

                    @Override
                    protected void onHandleError(String msg) {
                        view.error(msg);
                    }
                });
    }

    //编辑购物车操作
    public void edit(String type,String g_id,String  num){
        BaseApi.getRetrofit().create(ShopChartApi.class)
                .edit(type,g_id,num)
                .compose(RxSchedulers.<BaseModel<EmptyModel>>compose())
                .subscribe(new BaseObserver<EmptyModel>() {
                    @Override
                    protected void onHandleSuccess(EmptyModel emptyModel, String msg, int code) {
                        ToastUtils.show(msg);
                        EventBus.getDefault().postSticky(new EmptyModel());
                    }

                    @Override
                    protected void onHandleError(String msg) {
                        ToastUtils.show(msg);
                    }
                });
    }

}
