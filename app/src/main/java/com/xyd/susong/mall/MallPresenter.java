package com.xyd.susong.mall;

import android.app.Activity;

import com.xyd.susong.api.StoreApi;
import com.xyd.susong.base.BaseApi;
import com.xyd.susong.base.BaseModel;
import com.xyd.susong.base.BaseObserver;
import com.xyd.susong.base.RxSchedulers;
import com.xyd.susong.promptdialog.PromptDialog;
import com.xyd.susong.store.StoreModel;

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
}
